package com.example.demo;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

public class Pharmacy_ {
    public static volatile SingularAttribute<Pharmacy, Integer> id;// 用戶ID，自增量
    public static volatile SingularAttribute<Pharmacy, String> name;
    public static volatile SingularAttribute<Pharmacy, BigDecimal> cash_balance;
    
}
