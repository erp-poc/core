package com.pfonseca.erp.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Payment extends DefaultEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private PaymentType paymentType;
	
	@NotNull
	private Boolean paid;

	@NotNull
	@DecimalMin("0.00")
	private BigDecimal paidValue;
	
	/**
	 * In percentage
	 */
	@NotNull
	@Min(0)
	@Max(100)
	private Long discount;
	
	/**
	 * In percentage
	 */
	@NotNull
	@Min(0)
	@Max(100)
	private Long interest;
	
	@Size(max=512)
	private String comments;
	
	@Valid
	@OneToMany(cascade = CascadeType.ALL)
	private List<Installment> installments;

	public Long getDiscount() {
		return discount;
	}

	public void setDiscount(Long discount) {
		this.discount = discount;
	}

	public Long getInterest() {
		return interest;
	}

	public void setInterest(Long interest) {
		this.interest = interest;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Boolean getPaid() {
		return paid;
	}

	public void setPaid(Boolean paid) {
		this.paid = paid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Installment> getInstallments() {
		return installments;
	}

	public void setInstallments(List<Installment> installments) {
		this.installments = installments;
	}
}
