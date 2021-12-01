package com.etiya.rentACarSpring.dataAccess.abstracts;

import com.etiya.rentACarSpring.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.rentACarSpring.entities.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.*;
import java.sql.Date;
import java.util.List;


public interface CityDao extends JpaRepository<City, Integer>{


}
