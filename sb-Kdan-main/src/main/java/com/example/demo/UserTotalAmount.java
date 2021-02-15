package com.example.demo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



public class UserTotalAmount {


    private String name;
    private BigDecimal AmountTotal;
    

	/**
	 * @return the name
	 */ 
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getAmountTotal() {
		return AmountTotal;
	}
	public void setAmountTotal(BigDecimal amountTotal) {
		AmountTotal = amountTotal;
	}
	
}
