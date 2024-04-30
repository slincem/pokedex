package com.lincz.pokedex.web.config;

import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfiguration implements WebMvcConfigurer {

    /**
     * Configuration in order to let Spring 3 behaves as Spring <2,
     * having trailing slash in the url, representing the same endping
     *  /greeting = /greeting/
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseTrailingSlashMatch(true);
    }
}
