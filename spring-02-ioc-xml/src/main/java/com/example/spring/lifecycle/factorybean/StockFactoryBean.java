package com.example.spring.lifecycle.factorybean;

import com.example.spring.lifecycle.Stock;
import org.springframework.beans.factory.FactoryBean;

/**
 * Stock工厂bean
 */
public class StockFactoryBean implements FactoryBean<Stock> {

    @Override
    public Stock getObject() throws Exception {
        Stock stock = new Stock();
        stock.setStockCode("399001");
        stock.setStockName("深证成指");
        return stock;
    }

    @Override
    public Class<?> getObjectType() {
        return Stock.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
