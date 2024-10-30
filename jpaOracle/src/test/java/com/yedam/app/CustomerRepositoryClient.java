package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class CustomerRepositoryClient {

	@Autowired
	CustomerRepository repository;
	
	@Autowired
	AddressRepository addressRepository;
	
//	@Test
	void save() {
//		repository.save(new Customer("김", "길동"));
//		
//		Customer customer = repository.findById(1L);
//		assertEquals(customer.getFirstName(),"김");
		
//		repository.save(new Customer("홍", "기자"));
		repository.save(Customer.builder().firstName("김").lastName("길동").build());
		
		Customer customer = repository.findById(1L);
		assertEquals(customer.getFirstName(),"김");
	}
	
//	@Test
	void find() {
		List<Customer> cust = repository.findByLastNameOrFirstName("기자", "홍");
		System.out.println(cust);
	}

//	@Test
	void oneToOneCoustomerOwner() {

		// 1. oneToOne
//		// Address 등록
//		Address address = Address
//			.builder()
//			.zipcode("11122")
//			.address("대구")
//			.build();
//		addressRepository.save(address);
//		
//		// Customer 등록
//		Customer customer = Customer
//				.builder()
//				.firstName("김")
//				.lastName("길동")
//				.address(address)
//				.build();
//		addressRepository.save(address);
//		
//		repository.save(customer);
//		
//		Customer cust = repository.findById(1L);
//		System.out.println(cust.getFirstName() + " : " + cust.getAddress().getZipcode());
		
		// 2. ManyToOne
		// Customer 등록
		Customer customer = Customer
				.builder()
				.firstName("김")
				.lastName("길동")
				//.address(address) //
				.build();
		
		repository.save(customer);
		
		// Address 등록
		Address address = Address
				.builder()
				.zipcode("11122")
				.address("대구")
				
				.customer(customer) // 추가
				
				.build();
		
		addressRepository.save(address);
		
		address = Address
				.builder()
				.zipcode("00011")
				.address("서울")
				
				.customer(customer) // 추가
				
				.build();
		
		addressRepository.save(address);
		
//		Optional<Address> cust = addressRepository.findById(1L);
		
//		System.out.println(cust.get().getZipcode() + " : " + cust.get().getAddress());
		
		
		
	}
	
	@Test
	@Transactional
	void oneToMay() {
		Customer customer = repository.findById(1L);
		System.out.println(customer.getFirstName());
		customer.getAddress().forEach(addr -> System.out.println(addr.getZipcode()));
	}
	
//	@Test
	@Transactional
	void manyToOne() {
		// Customer insert
		Customer customer = new Customer();
//		repository.save(new Customer("홍", "기자"));
		repository.save(Customer.builder().firstName("홍").lastName("기자").build());
		
		// Address insert
		addressRepository.save(Address.builder()
				.zipcode("01440")
				.address("대구")
				.detail_address("4번지")
				//.customer(customer)
				.build());
		
		addressRepository.save(Address.builder()
				.zipcode("01441")
				.address("서울")
				.detail_address("100번지")
				//.customer(customer)
				.build());
		
		// customer select 
		System.err.println("----------------------");
		System.err.println("----------------------");
		System.err.println("----------------------");
		System.out.println(repository.findAll());
	}
	

	
}
