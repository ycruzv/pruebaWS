package com.valid.repository;

import org.springframework.data.repository.CrudRepository;

import com.valid.dto.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{

}
