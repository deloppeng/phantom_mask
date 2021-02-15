package com.example.demo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class PurchaseHistories {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Integer id;
    private Integer user_id;
    private Integer pharmacy_id;
    private Integer mask_id;
    private BigDecimal transactionAmount;
    private Date transactionDate;
    

  
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getPharmacy_id() {
		return pharmacy_id;
	}
	public void setPharmacy_id(Integer pharmacy_id) {
		this.pharmacy_id = pharmacy_id;
	}
	public Integer getMask_id() {
		return mask_id;
	}
	public void setMask_id(Integer mask_id) {
		this.mask_id = mask_id;
	}
	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

}
