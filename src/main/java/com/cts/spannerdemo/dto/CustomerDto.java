package com.cts.spannerdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
	private String customerId;

	private String username;
	private String password;
	private String emailId;
	private String address;
	private String phoneNumber;
}
