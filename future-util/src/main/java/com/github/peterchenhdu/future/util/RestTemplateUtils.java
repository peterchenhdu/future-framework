/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.util;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author chenpi
 * @since 2018/8/8 23:06
 */
public class RestTemplateUtils {

    private static RestTemplate restTemplate;

    static {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setReadTimeout(5000);
        requestFactory.setConnectTimeout(5000);
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new MappingJackson2XmlHttpMessageConverter());
        messageConverters.add(new MappingJackson2HttpMessageConverter());

        restTemplate = new RestTemplate(messageConverters);
        restTemplate.setRequestFactory(requestFactory);
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
    }

    private RestTemplateUtils() {

    }

    public static RestTemplate getClient() {
        return restTemplate;
    }

    public static String getRequest(String url, Map<String, Object> params) {
        String response = restTemplate.getForObject(url, String.class, params);
        return response;
    }

    public static String getRequest(String url, String params) {
        String response = restTemplate.getForObject(url, String.class, params);
        return response;
    }

    public static String postRequest(String url, Map<String, Object> params) {
        String response = restTemplate.postForObject(url, null, String.class, params);
        return response;
    }

    public static String postRequest(String url, String params) {
        String response = restTemplate.postForObject(url, null, String.class, params);
        return response;
    }
}