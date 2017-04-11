package com.lxt.starter.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.lxt.starter.druid.properties.DruidProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * Druid自动配置
 *
 * @author lixuetao
 * @version 1.0
 */
@Configuration
@ConditionalOnClass({DruidDataSource.class, StatViewServlet.class, WebStatFilter.class})
@ConditionalOnMissingBean(DataSource.class)
@EnableConfigurationProperties(DruidProperties.class)
public class DruidAutoConfiguration {

    private final static Logger logger = LoggerFactory.getLogger(DruidAutoConfiguration.class);

    @Autowired
    private DruidProperties properties;

    /**
     * 实例化DataSource，并交由Spring托管
     * @return DruidDataSource
     * @throws SQLException
     */
    @Bean(initMethod = "init", destroyMethod = "close")
    @ConditionalOnMissingBean
    public DruidDataSource dataSource() throws SQLException{
        logger.info("----初始化数据源----");

        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(properties.getDriverClassName());
        druidDataSource.setUrl(properties.getUrl());
        druidDataSource.setUsername(properties.getUsername());
        druidDataSource.setPassword(properties.getPassword());
        druidDataSource.setInitialSize(properties.getInitialSize());
        druidDataSource.setMinIdle(properties.getMinIdel());
        druidDataSource.setMaxActive(properties.getMaxActive());
        druidDataSource.setMaxWait(properties.getMaxWait());
        druidDataSource.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(properties.getValidationQuery());
        druidDataSource.setValidationQueryTimeout(properties.getValidationQueryTimeout());
        druidDataSource.setTestWhileIdle(properties.isTestWhileIdle());
        druidDataSource.setTestOnBorrow(properties.isTestOnBorrow());
        druidDataSource.setTestOnReturn(properties.isTestOnReturn());
        druidDataSource.setPoolPreparedStatements(properties.isPoolPreparedStatements());
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(properties.getMaxPoolPreparedStatementPerConnectionSize());
        druidDataSource.setFilters(properties.getFilters());
        druidDataSource.setUseGlobalDataSourceStat(properties.getUseGlobalDataSourceStat());
        druidDataSource.setProxyFilters(Arrays.asList(properties.getProxyFilters()));

        return druidDataSource;
    }

    /**
     * 实例化 ViewStatServlet，注册到Web环境中
     * @return ServletRegistrationBean
     */
    @Bean
    public ServletRegistrationBean druidServlet(){
        logger.info("----初始化ViewStatServlet----");

        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), properties.getServlet().getUrl());
        bean.addInitParameter("loginUsername", properties.getServlet().getUsername());
        bean.addInitParameter("loginPassword", properties.getServlet().getPassword());
        bean.addInitParameter("resetEnable", properties.getServlet().getResetEnable());
        if (null != properties.getServlet().getAllow()) {
            bean.addInitParameter("allow", properties.getServlet().getAllow());
        }
        if (null != properties.getServlet().getDeny()) {
            bean.addInitParameter("deny", properties.getServlet().getDeny());
        }

        return bean;
    }

    /**
     * 实例化 WebStatFilter，注册到Web环境中
     * @return FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean druidFilter(){
        logger.info("----初始化WebStatFilter----");

        FilterRegistrationBean bean = new FilterRegistrationBean(new WebStatFilter());
        bean.addUrlPatterns(properties.getFilter().getUrl());
        bean.addInitParameter("exclusions", properties.getFilter().getExclusions());
        bean.addInitParameter("sessionStatMaxCount", properties.getFilter().getSessionStatMaxCount());
        bean.addInitParameter("sessionStatEnable", properties.getFilter().getSessionStatEnable());
        if (null != properties.getFilter().getPrincipalSessionName()) {
            bean.addInitParameter("principalSessionName", properties.getFilter().getPrincipalSessionName());
        }
        if (null != properties.getFilter().getPrincipalCookieName()) {
            bean.addInitParameter("principalCookieName", properties.getFilter().getPrincipalCookieName());
        }
        bean.addInitParameter("profileEnable", properties.getFilter().getProfileEnable());

        return bean;
    }


}
