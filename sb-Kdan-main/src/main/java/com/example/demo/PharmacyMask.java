package com.example.demo;

import java.math.BigDecimal;

public class PharmacyMask {
	private String PharmacyName;
	private String MaskName;
	private BigDecimal MaskPrice;

	public String getPharmacyName() {
		return PharmacyName;
	}

	public void setPharmacyName(String pharmacyName) {
		PharmacyName = pharmacyName;
	}

	public String getMaskName() {
		return MaskName;
	}

	public void setMaskName(String maskName) {
		MaskName = maskName;
	}

	public BigDecimal getMaskPrice() {
		return MaskPrice;
	}

	public void setMaskPrice(BigDecimal maskPrice) {
		MaskPrice = maskPrice;
	}

}
