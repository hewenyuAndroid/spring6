# 基于注解管理Bean

Spring 从 2.5 版本开始提供了对注解技术的全面支持，我们可以使用注解来实现自动装配，简化 spring 的 xml 配置;

使用 Spring 注解实现自动装配的步骤如下:

1. 引入依赖
2. 开启组件扫描
3. 使用注解定义 Bean
4. 依赖注入

> 引入依赖

```xml

<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>6.0.2</version>
</dependency>
```

> 开启组件扫描

Spring 默认不使用注解装配 Bean ，因此需要在 Spring 的 XML 配置中，通过 `<context:componet-scan base-package="" />`
配置需要扫描注解的包。
配置了 `compoent-scan` 后，spring 会自动扫描 `base-package` 指定的包及其子包下的所有类，如果类上使用了 `@Component`
注解，则该类就会被装配到容器中。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="
       http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
">

    <!--
        spring 默认不使用注解装配 bean， 需要手动在 spring 的 xml 文件中，通过 context:component-scan 元素开启 spring bean 的自动扫描功能
        该功能开启后，spring 会扫描指定的包，及其子包下的所有类，如果类上使用了 @Component 注解，则该类会被加载到 spring 容器中
    -->
    <context:component-scan base-package="com.example.spring"/>

</beans>
```

# 使用注解定义 Bean

Spring 提供了多个注解定义 Bean:

| 注解               | 说明                                                                                                                                    |
|------------------|---------------------------------------------------------------------------------------------------------------------------------------|
| `@Component`     | 用于描述一个 Spring 中的Bean，是一个泛化的概念，仅仅表示是容器中的一个组件(`Bean`)，并且可以作用在应用的任意层次，例如: `Service`，`Dao`;                                               |
| `@Repository`    | 用于描述一个 Spring 中的数据访问层(`dao`层)的 Bean，功能与 `@Component` 相同;                                                                              |
| `@Service`       | 用于描述一个 Spring 中的业务层(`service`层) 的 Bean，功能与 `@Component` 相同;                                                                           |
| `@Controller`    | 用于描述一个 Spring 中的控制层 (`Controller`层) 的 Bean，功能与 `@Component` 相同;                                                                       |
| `@Configuration` | 用于描述一个 Spring 中的配置类，使用 `@Configuration` 标记类本身是一个 `Bean`，然后其内部主要是用于定义和配置 `Spring Bean`，使用 `Configuration` 类标记配置类能够保证 `@Bean` 注解的对象是个单例 |

> `@Configuration` 中 `CGLIB` 代理保证 `@Bean` 单例

```java
public class SomeConfig {
    @Bean
    public BeanA beanA() {
        return new BeanA(); // 假设这是一个需要单例的 Bean
    }

    @Bean
    public BeanB beanB() {
        // 问题所在：这里直接调用了 beanA() 方法
        BeanA a = beanA(); // 期望拿到的是 Spring 容器管理的单例 BeanA
        return new BeanB(a);
    }
}
```

使用 `@Component` 注解时，存在的问题:

当 `@Bean` 标记的方法 `beanB()` 执行时，其内部调用了另外一个 `@Bean` 方法 `beanA()`，触发的是普通的 java 方法调用，此时 `beanB()` 方法内部得到的 `BeanA` 对象并不是由 spring 容器管理的 `BeanA` 对象；

使用 `@Configuration` 解决上述问题：

1. `@Configuration` 注解标记的配置类，会启用 `CGLIB` 代理，Spring 启动时，会创建 `CGLIB` 代理实例，真正的 `SomeConfig` 实例被包在代理实例内部；
2. 容器开始处理 `@Bean` 方法;(假设先处理的是 `beanB()`)
3. 调用代理对象的 `beanB()`
4. 在 `beanB()` 方法内部，执行到 `beanA()`
5. 代理拦截对 `beanA()` 的调用;
   - 检查容器中是否有 `beanA` 实例? (假设没有，首次调用)
   - 代理委托给 `someConfig` 实例，执行其 `beanA()` 方法，创建 `beanA` 实例;
   - 代理将 `beanA` 实例注册到 Spring 容器中(作为单例);
   - 代理将 `beanA` 实例返回给 `beanB()` 方法内的调用点;
6. `beanB()` 方法继续执行，使用来自 Spring 容器的 `beanA` 实例创建 `beanB` 实例;


# 加载外部资源

1. 在 `resources` 目录下创建 [`jdbc.properties`](./src/main/resources/jdbc.properties)
2. 使用 `@PropertySource("classpath:jdbc.properties")` 加载资源文件
3. 读取配置文件资源 `@Value`

```java
/**
 * 使用 @PropertySource 加载 classPath 目录下的资源文件
 */
@Data
@Configuration
@PropertySource("classpath:jdbc.properties")
public class JdbcConfig {

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.url}")
    private String url;

}
```



