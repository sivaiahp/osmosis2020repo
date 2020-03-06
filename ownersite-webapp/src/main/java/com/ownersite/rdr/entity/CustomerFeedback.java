package com.ownersite.rdr.entity;

import javax.persistence.*;

@Entity
@Cacheable(false)
@Table(name = "CustomerFeedback")
public class  CustomerFeedback extends BaseEntity{
	private java.util.Date enquiry_created_date;
	private java.util.Date enquiry_resolved_date;
	private String enquiry_question;
	private String enquiry_answer;
	private Long customerId;
	private Long dealerId;
	
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getDealerId() {
		return dealerId;
	}

	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}

	@ManyToOne
	@JoinColumn(name = "customerId", referencedColumnName = "id", updatable = false, insertable = false)
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "dealerId", referencedColumnName = "id", updatable = false, insertable = false)
	private Dealer dealer;

	public java.util.Date getEnquiry_created_date(){
		return enquiry_created_date;
	}

	public void setEnquiry_created_date(java.util.Date enquiry_created_date){
		this.enquiry_created_date=enquiry_created_date;
	}

	public java.util.Date getEnquiry_resolved_date(){
		return enquiry_resolved_date;
	}

	public void setEnquiry_resolved_date(java.util.Date enquiry_resolved_date){
		this.enquiry_resolved_date=enquiry_resolved_date;
	}

	public String getEnquiry_question(){
		return enquiry_question;
	}

	public void setEnquiry_question(String enquiry_question){
		this.enquiry_question=enquiry_question;
	}

	public String getEnquiry_answer(){
		return enquiry_answer;
	}

	public void setEnquiry_answer(String enquiry_answer){
		this.enquiry_answer=enquiry_answer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	@Override
	public String toString() {
		return "CustomerFeedback [enquiry_created_date=" + enquiry_created_date + ", enquiry_resolved_date="
				+ enquiry_resolved_date + ", enquiry_question=" + enquiry_question + ", enquiry_answer="
				+ enquiry_answer + ", customerId=" + customerId + ", dealerId=" + dealerId + ", customer=" + customer
				+ ", dealer=" + dealer + "]";
	}

	
}