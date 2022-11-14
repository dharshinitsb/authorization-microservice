package com.cts.spannerdemo.services;


import com.cts.spannerdemo.exceptions.UserAlredyExistsException;
import com.cts.spannerdemo.repo.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.cts.spannerdemo.model.customer;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		customer customer= customerRepository.findByUsername(userName);
		if (customer == null) {
			throw new UsernameNotFoundException("Customer not found with customername: " + userName);
		}
		log.info("Customer found");
		log.info("Customer successfully located");
		return new org.springframework.security.core.userdetails.User(customer.getUsername(), customer.getPassword(), new ArrayList<>());
	}
	
	@Transactional
	public void save(customer customer) throws Exception{
		if(CheckIfUserAlreadyExists(customer.getUsername())) {
			throw new UserAlredyExistsException("Customer with customer name "+customer.getUsername()+" already exists");
		}
		customer newUser = new customer();
		//newUser.setFirstName(customer.getFirstName());
		//newUser.setLastName(customer.getLastName());
newUser.setUsername(customer.getUsername());
		newUser.setEmailId(customer.getEmailId());
		newUser.setPassword(bcryptEncoder.encode(customer.getPassword()));
		newUser.setAddress(customer.getAddress());
		newUser.setPhoneNumber(customer.getPhoneNumber());


		customerRepository.save(newUser);
		log.info("customer successfully saved!");
	}
	
	public customer getUserByName(String username) {
		log.info("User found with username {}" + username);
		return customerRepository.findByUsername(username);
	}
	
	public boolean CheckIfUserAlreadyExists(String username) {
		//return customerRepository.findAll().stream().anyMatch(u -> u.getUsername().equals(username));
		return customerRepository.findAll().equals(username);
	}
}
