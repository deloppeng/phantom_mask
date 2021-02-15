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



@Entity
public class Mask {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Integer id;
    private Integer pharmacy_id;
    private String name;
    private BigDecimal price;
    

  
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
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
