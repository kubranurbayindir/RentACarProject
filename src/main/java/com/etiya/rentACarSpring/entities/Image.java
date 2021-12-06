package com.etiya.rentACarSpring.entities;

import java.sql.Date;

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
@Table(name = "images")
@Entity
public class Image {

	@Id
	@Column(name = "image_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int imageId;

	@ManyToOne
	@JoinColumn(name = "id")
	private Car car;

	@Column(name = "date")
	private Date date;

	@Column(name = "image_url")
	private String imageUrl;
}
