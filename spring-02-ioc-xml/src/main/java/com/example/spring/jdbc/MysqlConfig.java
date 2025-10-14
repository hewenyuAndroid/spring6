package com.example.spring.jdbc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MysqlConfig {

    private String user;

    private String password;

    private String url;

    private String driver;

}
