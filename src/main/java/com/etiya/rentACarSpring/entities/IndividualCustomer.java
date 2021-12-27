package com.etiya.rentACarSpring.entities;

import java.sql.Date;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "user_id")
@Table(name = "individualCustomers")
public class IndividualCustomer extends User {
	
	@Column (name="first_name")
	private String firstName;
	
	@Column (name="last_name")
	private String lastName;

	@Column (name="identity_number")
	private String identityNumber;
	
	@Column (name="birthday")
	private Date birthday;  

}


