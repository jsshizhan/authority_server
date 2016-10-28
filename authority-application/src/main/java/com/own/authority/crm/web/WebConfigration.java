package com.own.authority.crm.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xiemeilong on 2014-10-23.
 */
@Configuration
public class WebConfigration extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private static final Charset UTF8 = Charset.forName("UTF-8");
    //应答消息转换器
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //能够返回String
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "plain", UTF8)));
        stringConverter.setWriteAcceptCharset(false);
        converters.add(stringConverter);

        //能够返回图片
        BufferedImageHttpMessageConverter imageConverter = new BufferedImageHttpMessageConverter();
        imageConverter.setDefaultContentType(MediaType.IMAGE_PNG);
        converters.add(imageConverter); //这个必须在MappingJackson2HttpMessageConverter前面
        converters.add(new ResourceHttpMessageConverter());

        //能够返回JSON
        converters.add(new MappingJackson2HttpMessageConverter());

    }
}
