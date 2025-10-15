

# Bean 的生命周期

## 没有 setter 注入时

```xml
    <!--
        bean的生命周期
        init-method="initMethod"    // 配置初始化方法
        destroy-method="destroyMethod"  // 配置销毁方法
    -->

    <bean id="stock1" class="com.example.spring.lifecycle.Stock"
          init-method="initMethod"
          destroy-method="destroyMethod"
    >
        <constructor-arg name="stockName" value="上证指数"></constructor-arg>
        <constructor-arg name="stockCode" value="1A0001"></constructor-arg>
    </bean>
```

1. bean 构造函数
2. bean 的 initMethod
3. bean 对象就绪
4. bean 对象销毁
5. ioc 容器销毁

获取 `stock1` 日志
```text
Stock 有参构造... stockName = 上证指数, stockCode = 1A0001
Stock initMethod...
stock1: Stock(stockName=上证指数, stockCode=1A0001)
Stock destroyMethod...
```

## 有 setter 注入时

```xml
    <bean id="stock2" class="com.example.spring.lifecycle.Stock"
          init-method="initMethod"
          destroy-method="destroyMethod"
    >
        <property name="stockName" value="上证指数"></property>
        <property name="stockCode" value="1A0001"></property>
    </bean>
```

1. bean 构造函数
2. bean 的 setter 注入
3. bean 的 initMethod
4. bean 对象就绪
5. bean 对象销毁
6. ioc 容器销毁

获取 `stock2` 日志

```text
Stock 无参构造..
Stock setStockName(): stockName=上证指数
Stock setStockCode(): stockCode=1A0001
Stock initMethod...
stock2: Stock(stockName=上证指数, stockCode=1A0001)
Stock destroyMethod...
```

## `Bean` 的作用域

spring 中可以通过配置 `bean` 标签的 `scope` 属性来指定 `bean` 的作用域范围，各个取值含义如下:

| 取值               | 含义                          | 创建对象时机    | 回收时机        | 使用场景                         |
|------------------|-----------------------------|-----------|-------------|------------------------------|
| `singleton` (默认) | 在 IOC 容器中，该 bean 的对象实例始终为单例 | IOC容器初始化时 | 容器关闭时       | 无状态的共享服务，例如 service,dao 等 (最常用) |
| `prototype`      | 在 IOC 容器中，该 bean 对象有多个实例    | 获取 bean 时 | GC回收 (容器管理) | 有状态的对象，每次都要新实例，例如 连接池中的连接    |
| `request` | 在一个请求范围内有效 | 每次 http 请求 | 请求结束 | 存储请求相关数据，例如 表单对象 |
| `session` | 在一个会话范围内有效 | 会话中第一次被请求 | 会话失效 | 存储用户会话数据，例如登录信息、购物车 |

## `BeanPostProcessor` bean 的后置处理器

`Bean` 的后置处理器会在生命周期的 初始化操作(`init-method`) 前后添加额外的操作，`Bean` 的后置处理器需要实现 `BeanPostProcessor` 接口，此外需要将实现该接口的 bean 后置处理器配置到 ioc 容器中；

需要注意的是 `Bean` 的后置处理器作用的是整个 `IOC` 容器中的所有 `bean`，而不是某个单独的 `bean` 对象;

```xml
    <!--
        配置 bean 的后置处理器，后置处理器只有配置到ioc容器中才会生效
    -->
    <bean id="myBeanPostProcessor" class="com.example.spring.processor.MyBeanPostProcessor"></bean>
```

[`MyBeanPostProcessor`](./src/main/java/com/example/spring/processor/MyBeanPostProcessor.java)


```java
package org.springframework.beans.factory.config;

import org.springframework.beans.BeansException;
import org.springframework.lang.Nullable;

public interface BeanPostProcessor {
    
    // bean 对象创建并完成属性注入后回调
    @Nullable
    default Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    // bean 对象执行完 init-method 初始化方法之后回调
    @Nullable
    default Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
```

添加 bean 后置处理器后，启动 spring 容器，执行日志如下

```text
Stock 无参构造..
Stock setStockName(): stockName=上证指数
Stock setStockCode(): stockCode=1A0001
// 完成 setter 注入后，在 init-method 方法之前执行 bean后置处理器的 postProcessBeforeInitialization() 回调方法
MyBeanPostProcessor: postProcessBeforeInitialization() bean=Stock(stockName=上证指数, stockCode=1A0001), beanName=stock2
Stock initMethod...
// 在 init-method 方法执行完毕后，执行 bean后置处理器的 postProcessAfterInitialization() 回调方法
MyBeanPostProcessor: postProcessAfterInitialization() bean=Stock(stockName=上证指数, stockCode=1A0001), beanName=stock2
stock2: Stock(stockName=上证指数, stockCode=1A0001)
Stock destroyMethod...
```


## `FactoryBean`

`FactoryBean` 是 `Spring` 提供的一种整合第三方框架的常用机制。和普通的 `Bean` 不同，配置一个 `FactoryBean` 类型的 `Bean`，在获取 `Bean` 的时候得到的不是 `class` 属性中配置的这个类的对象，而是 `FactoryBean.getObject()` 方法的返回值。通过这种机制，`spring` 可以帮助我们把复杂组件创建的详细过程和繁琐的细节屏蔽起来，只把最简洁的使用界面展示出来。

```java
package org.springframework.beans.factory;

import org.springframework.lang.Nullable;

public interface FactoryBean<T> {
    String OBJECT_TYPE_ATTRIBUTE = "factoryBeanObjectType";

    // 返回目标 bean 对象
    @Nullable
    T getObject() throws Exception;

    // 目标对象类型
    @Nullable
    Class<?> getObjectType();

    // 作用域是否是 singleton
    default boolean isSingleton() {
        return true;
    }
}
```


