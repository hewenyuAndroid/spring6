package com.example.spring.lifecycle;

import lombok.ToString;

@ToString
public class Stock {

    private String stockName;

    private String stockCode;

    public Stock() {
        System.out.println("Stock 无参构造..");
    }

    public Stock(String stockName, String stockCode) {
        this.stockName = stockName;
        this.stockCode = stockCode;
        System.out.println("Stock 有参构造... stockName = " + stockName + ", stockCode = " + stockCode);
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
        System.out.println("Stock setStockName(): stockName=" + stockName);
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
        System.out.println("Stock setStockCode(): stockCode=" + stockCode);
    }

    public void initMethod() {
        System.out.println("Stock initMethod...");
    }

    public void destroyMethod() {
        System.out.println("Stock destroyMethod...");
    }

}
