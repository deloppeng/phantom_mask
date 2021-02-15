package com.example.demo;

import java.math.BigDecimal;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity
public class Opentime {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Integer id;
    private Integer pharmacy_id;
    private String openweek;
    private Time opentime;
    private Time closetime;
    /*
    @ManyToOne
    @JoinColumn(name="pharmacy_id",referencedColumnName="id", insertable=false, updatable=false)
    private Pharmacy pharmacy;
    */

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPharmacy_id() {
		return pharmacy_id;
	}
	public void setPharmacy_id(Integer pharmacy_id) {
		this.pharmacy_id = pharmacy_id;
	}
	public String getOpenweek() {
		return openweek;
	}
	public void setOpenweek(String openweek) {
		this.openweek = openweek;
	}
	public Time getOpentime() {
		return opentime;
	}
	public void setOpentime(Time opentime) {
		this.opentime = opentime;
	}
	public Time getClosetime() {
		return closetime;
	}
	public void setClosetime(Time closetime) {
		this.closetime = closetime;
	}
/*
	public Pharmacy getPharmacy() {
		return pharmacy;
	}
	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}*/

	
}
