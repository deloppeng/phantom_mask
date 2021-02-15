package com.example.demo;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

public class User_ {
    public static volatile SingularAttribute<Mask, Integer> id;// 用戶ID，自增量
    public static volatile SingularAttribute<Mask, String> name;
    public static volatile SingularAttribute<Mask, BigDecimal> cash_balance;
    
}
