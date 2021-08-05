package com.irecover.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User implements Serializable {

	private static final long serialVersionUID = -5845299412811762562L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String password;
	private String email;
	private Gender genderType;
	
	@Enumerated(EnumType.ORDINAL)
	private ProfessionType professionType;

	@Enumerated(EnumType.ORDINAL)
	private UserType userType;

	@Enumerated(EnumType.ORDINAL)
	private DiseaseType diseaseType;
	private String country;
	private String state;
	private String zipCode;
	private double latitude;
	private double longitude;
	private String profileImageName;
	private String profileImageUrl;

}
