package com.etiya.rentACarSpring.entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rentals")
public class Rental {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rental_id")
	private int rentalId;
	
	@Column (name ="rent_date")
	private Date rentDate; 

	@Column (name="return_date")
	private Date returnDate;

	@Column(name = "return_kilometer")
	private  String returnKilometer;

	@ManyToOne
	@JoinColumn (name= "id")
	private Car car;
	
	@ManyToOne
	@JoinColumn (name= "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "take_city_id")
	private City takeCity;

	@ManyToOne
	@JoinColumn(name = "return_city_id")
	private City returnCity;

	@OneToMany(mappedBy = "rental")
	private List<RentalAdditionalService> rentalAdditionalServices;

	@OneToOne(mappedBy = "rental")
	private Invoice invoice;
}
