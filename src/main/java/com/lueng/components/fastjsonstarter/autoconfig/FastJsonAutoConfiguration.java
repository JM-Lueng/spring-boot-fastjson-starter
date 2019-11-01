package com.lueng.components.fastjsonstarter.autoconfig;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * fastjson配置类
 * @author JMLiang
 * @since 2018-04-15
 */
@Configuration
@ConditionalOnClass(JSON.class)
@ConditionalOnMissingBean(FastJsonHttpMessageConverter4.class)
@ConditionalOnWebApplication
@ConditionalOnProperty(
        name = {"spring.http.converters.preferred-json-mapper"},
        havingValue = "fastjson",
        matchIfMissing = true
)
public class FastJsonAutoConfiguration {
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converter.setFastJsonConfig(FastJsonBuilder.fastjsonConfig());
        converter.setSupportedMediaTypes(FastJsonBuilder.supportMediaTypes());
        return new HttpMessageConverters(converter);
    }

}
