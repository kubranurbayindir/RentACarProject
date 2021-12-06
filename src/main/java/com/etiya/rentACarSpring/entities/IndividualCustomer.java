package com.etiya.rentACarSpring.entities;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	

}


