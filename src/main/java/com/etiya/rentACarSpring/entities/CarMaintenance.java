package com.etiya.rentACarSpring.entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "car_maintenances")
public class CarMaintenance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_maintenance_id")
	private int carMaintenanseId;
	
	@Column (name ="maintenance_date")
	private Date maintananceDate; 

	@Column (name="return_date")
	private Date returnDate;
	
	@ManyToOne
	@JoinColumn (name= "id")
	private Car car;
}
