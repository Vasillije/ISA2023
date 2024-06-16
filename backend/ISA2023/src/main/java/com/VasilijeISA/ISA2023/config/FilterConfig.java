package com.VasilijeISA.ISA2023.config;


import com.VasilijeISA.ISA2023.filter.RemoveChunkedEncodingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<RemoveChunkedEncodingFilter> loggingFilter() {
        FilterRegistrationBean<RemoveChunkedEncodingFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new RemoveChunkedEncodingFilter());
        registrationBean.addUrlPatterns("/api/*"); // ili specifične putanje koje želite da filter bude primenjen

        return registrationBean;
    }
}
