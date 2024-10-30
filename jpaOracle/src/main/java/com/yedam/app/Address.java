package com.yedam.app;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long address_id;
	
	private String zipcode;
	private String address;
	private String detail_address;
	
//	@OneToOne
//	@JoinColumn(name = "customer_id")
//	Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	Customer customer;
}