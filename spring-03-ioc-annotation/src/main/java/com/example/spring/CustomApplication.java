package com.example.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration 标记当前类是一个配置类
 * @ComponentScan 标记需要扫描的包
 */
@Configuration
@ComponentScan("com.example.spring")
public class CustomApplication {

}
