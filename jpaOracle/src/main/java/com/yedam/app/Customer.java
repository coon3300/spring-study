package com.yedam.app;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  
  @Column(length = 30, nullable = false)
  private String firstName;
  private String lastName;
  
//  @OneToOne(mappedBy = "customer")
//  Address address;
  
  @OneToMany(mappedBy = "customer")
  List<Address> address;
  
}