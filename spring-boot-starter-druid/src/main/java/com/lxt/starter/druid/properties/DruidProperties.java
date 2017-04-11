package com.lxt.starter.druid.properties;

import com.alibaba.druid.filter.Filter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Druid默认配置
 *
 * @author zer0
 * @version 1.0
 */
@ConfigurationProperties(prefix = DruidProperties.PREFIX)
@EnableConfigurationProperties({ServletProperties.class, FilterProperties.class})
public class DruidProperties {

    public static final String PREFIX = "druid";

    private String url = "jdbc:mysql://localhost:3306/test";
    private String username = "root";
    private String password = "root";
    private String driverClassName = "com.mysql.jdbc.Driver";
    //初始化时建立物理连接的个数。初始化发生在显示调用init方法或第一次getConnection时
    private Integer initialSize = 10;
    //最小连接池数量
    private Integer minIdel = 10;
    //最大连接池数量
    private Integer maxActive = 50;
    //获取连接的最大等待时间(毫秒)
    private Integer maxWait = 60000;
    //打开PSCache，并且指定每个连接上PSCache的大小
    private boolean poolPreparedStatements = false;
    /*
     * 要启用PSCache，必须配置大于0，当大于0时，
     * poolPreparedStatements自动触发修改为true。
     * 在Druid中，不会存在Oracle下PSCache占用内存过多的问题，
     * 可以把这个数值配置大一些，比如说100
     */
    private Integer maxPoolPreparedStatementPerConnectionSize = -1;
    //检测连接是否有效的sql
    private String validationQuery = "SELECT 'x'";
    //检测连接是否有效的超时时间(秒)
    private Integer validationQueryTimeout = 10000;
    //申请连接时执行validationQuery检测连接是否有效
    private boolean testOnBorrow = false;
    //归还连接时执行validationQuery检测连接是否有效
    private boolean testOnReturn = false;
    /*
     * 申请连接的时候检测，如果空闲时间大于
     * timeBetweenEvictionRunsMillis，
     * 执行validationQuery检测连接是否有效
     */
    private boolean testWhileIdle = true;
    /*
     * 有两个含义：
     * 1) Destroy线程会检测连接的间隔时间，如果连接空闲时间
     *    大于等于minEvictableIdleTimeMillis则关闭物理连接。
     * 2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明
     * 默认一分钟
     */
    private Long timeBetweenEvictionRunsMillis = 60000L;
    //连接保持空闲而不被驱逐的最长时间
    private Long minEvictableIdleTimeMillis = 300000L;
    //物理连接初始化的时候执行的sql
    private String connectionInitSqls;
    /*
     *  属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：
     *  监控统计用的filter:stat
     *  日志用的filter:log4j
     *  防御sql注入的filter:wall
     *  注意:使用多个用逗号分隔(stat,wall,log4j)
     */
    private String filters = "stat,wall";
    /*
     * 类型是List，如果同时配置了filters和proxyFilters，
     * 是组合关系，并非替换关系
     */
    private Filter[] proxyFilters;
    //合并多个DruidDataSource的监控数据(true:合并, false:不合并)
    private Boolean useGlobalDataSourceStat = true;

    private ServletProperties servlet;
    private FilterProperties filter;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public Integer getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(Integer initialSize) {
        this.initialSize = initialSize;
    }

    public Integer getMinIdel() {
        return minIdel;
    }

    public void setMinIdel(Integer minIdel) {
        this.minIdel = minIdel;
    }

    public Integer getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }

    public Integer getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(Integer maxWait) {
        this.maxWait = maxWait;
    }

    public boolean isPoolPreparedStatements() {
        return poolPreparedStatements;
    }

    public void setPoolPreparedStatements(boolean poolPreparedStatements) {
        this.poolPreparedStatements = poolPreparedStatements;
    }

    public Integer getMaxPoolPreparedStatementPerConnectionSize() {
        return maxPoolPreparedStatementPerConnectionSize;
    }

    public void setMaxPoolPreparedStatementPerConnectionSize(Integer maxPoolPreparedStatementPerConnectionSize) {
        this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public Integer getValidationQueryTimeout() {
        return validationQueryTimeout;
    }

    public void setValidationQueryTimeout(Integer validationQueryTimeout) {
        this.validationQueryTimeout = validationQueryTimeout;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public boolean isTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public Long getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(Long timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public Long getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(Long minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public String getConnectionInitSqls() {
        return connectionInitSqls;
    }

    public void setConnectionInitSqls(String connectionInitSqls) {
        this.connectionInitSqls = connectionInitSqls;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public Filter[] getProxyFilters() {
        return proxyFilters;
    }

    public void setProxyFilters(Filter[] proxyFilters) {
        this.proxyFilters = proxyFilters;
    }

    public Boolean getUseGlobalDataSourceStat() {
        return useGlobalDataSourceStat;
    }

    public void setUseGlobalDataSourceStat(Boolean useGlobalDataSourceStat) {
        this.useGlobalDataSourceStat = useGlobalDataSourceStat;
    }

    public ServletProperties getServlet() {
        return servlet;
    }

    public void setServlet(ServletProperties servlet) {
        this.servlet = servlet;
    }

    public FilterProperties getFilter() {
        return filter;
    }

    public void setFilter(FilterProperties filter) {
        this.filter = filter;
    }
}
