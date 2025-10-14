

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


