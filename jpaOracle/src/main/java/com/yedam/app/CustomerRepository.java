package com.yedam.app;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	// find -> select, by -> where
  List<Customer> findByLastName(String lastName);

  Customer findById(long id);
  
  List<Customer> findByLastNameOrFirstName(String lastName, String firstName);
}
