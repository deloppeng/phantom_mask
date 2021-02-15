package com.example.demo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

public class PurchaseHistories_ {
    public static volatile SingularAttribute<PurchaseHistories, Integer> id;// 用戶ID，自增量
    public static volatile SingularAttribute<PurchaseHistories, Integer> user_id;
    public static volatile SingularAttribute<PurchaseHistories, Integer> pharmacy_id;
    public static volatile SingularAttribute<PurchaseHistories, Integer> mask_id;
    public static volatile SingularAttribute<PurchaseHistories, Date> transactionDate;
    public static volatile SingularAttribute<PurchaseHistories, BigDecimal> transactionAmount;
    
}
