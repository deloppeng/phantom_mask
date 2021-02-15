package com.example.demo;

import java.sql.Time;

import javax.persistence.metamodel.SingularAttribute;

public class Opentime_ {
    public static volatile SingularAttribute<Opentime, Integer> id;// 用戶ID，自增量
    public static volatile SingularAttribute<Opentime, Integer> pharmacy_id;// 用戶ID，自增量
    public static volatile SingularAttribute<Opentime, String> openweek;
    public static volatile SingularAttribute<Opentime, Time> opentime;
    public static volatile SingularAttribute<Opentime, Time> closetime;
    
}
