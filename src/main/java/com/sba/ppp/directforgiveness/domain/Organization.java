package com.sba.ppp.directforgiveness.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Organization {

	private String name;

	private String street_address;

	private String street_address2;

	private String city;

	private String state;

	private String zip;

	private String phone;

	private String fax;

	private String web;

	private String borrower_contact_phone;

	private String borrower_contact_email;

}
