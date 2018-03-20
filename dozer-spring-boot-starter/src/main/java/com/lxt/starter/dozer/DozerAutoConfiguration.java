package com.lxt.starter.dozer;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zer0
 * @version 1.0
 */

@Configuration
public class DozerAutoConfiguration {

    @Autowired
    private ResourcePatternResolver patternResolver;

    @Bean
    public DozerBeanMapper dozerBeanMapper() throws IOException {
        Resource[] resources = patternResolver.getResources("classpath:dozer/*.xml");
        if (resources == null || resources.length == 0) return new DozerBeanMapper();

        List<String> mappingFiles = new ArrayList<String>();
        for (Resource resource : resources){
            mappingFiles.add("dozer/" + resource.getFilename());
        }

        DozerBeanMapper mapper = new DozerBeanMapper(mappingFiles);
        return mapper;
    }

}
