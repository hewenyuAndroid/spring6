package com.example.spring.bean.impl;

import com.example.spring.bean.ApplicationContext;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class AnnotationApplicationContext implements ApplicationContext {

    /**
     * 缓存bean对象
     */
    private Map<Class<?>, Object> beanFactory = new HashMap<>();

    public AnnotationApplicationContext(String beanPackage) {
        // 设置包扫描的规则
        // 当前包及其子包，哪个类有 @Bean 注解，就通过反射创建对象

        // basePackage: com.example.spring 转成 com\example\spring
        String basePackagePath = beanPackage.replaceAll("\\.", "\\\\");

        // 获取包绝对路径
        try {
            Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(basePackagePath);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                String filePath = URLDecoder.decode(url.getFile(), "UTF-8");

                System.out.println("filePath:" + filePath);

                // todo 包扫描

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        new AnnotationApplicationContext("com.example.spring.bean");
    }

    @Override
    public Object getBean(Class<?> clazz) {
        return beanFactory.get(clazz);
    }

}
