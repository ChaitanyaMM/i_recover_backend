package com.irecover.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.irecover.entity.DiseaseType;
import com.irecover.entity.User;
import com.irecover.entity.UserType;
import com.irecover.service.UserService;

@RestController
@RequestMapping("/api/analyze/")
public class AnalysisController {

	// need to analyse based upon symptom either pdf or some other file
	// for example cough is the symptom many people has went through this symptom
	// and recoverd safely
	// in this area these are active /passive cases
	// these are death cases
	// for cough these are the measure in recovered patients took the pre-cautions

	@Autowired
	private UserService userService;

	private List<User> fetchByCombinations(DiseaseType diseseType, String zipCode, String state, String country,
			UserType userType) {
		List<User> fetchedUsers = null;

		if (diseseType != null && zipCode != null && state != null && country != null && userType != null) {
			fetchedUsers = userService.findByDiseaseTypeAndZipCodeAndStateAndCountryAndUserType(diseseType, zipCode,
					state, country, userType);
		} else if (diseseType != null && zipCode != null) {
			fetchedUsers = userService.findByDiseaseTypeAndZipCode(diseseType, zipCode);
		} else if (diseseType != null && state != null) {
			fetchedUsers = userService.findByDiseaseTypeAndState(diseseType, state);
		} else if (diseseType != null && country != null) {
			fetchedUsers = userService.findByDiseaseTypeAndCountry(diseseType, country);
		} else if (diseseType != null && userType != null) {
			fetchedUsers = userService.findByDiseaseTypeAndUserType(diseseType, userType);
		}

		else if (zipCode != null && state != null) {
			fetchedUsers = userService.findByZipCodeAndState(zipCode, state);
		} else if (zipCode != null && country != null) {
			fetchedUsers = userService.findByZipCodeAndCountry(zipCode, country);
		} else if (zipCode != null && userType != null) {
			fetchedUsers = userService.findByZipCodeAndUserType(zipCode, userType);
		}

		else if (state != null && country != null) {
			fetchedUsers = userService.findByStateAndCountry(state, country);
		} else if (state != null && userType != null) {
			fetchedUsers = userService.findByStateAndUserType(state, userType);
		}

		else if (country != null && userType != null) {
			fetchedUsers = userService.findByCountryAndUserType(country, userType);
		} else {
			fetchedUsers =fetchBySingleCondition(diseseType, zipCode, state, country, userType, fetchedUsers);
		}

		return fetchedUsers;

	}

	@GetMapping("/type")
	public ResponseEntity<?> getBy(@RequestParam(required = false) DiseaseType diseseType,
			@RequestParam(required = false) String zipCode, @RequestParam(required = false) String state,
			@RequestParam(required = false) String country, @RequestParam(required = false) UserType userType) {
		List<User> fetchedUsers = fetchByCombinations(diseseType, zipCode, state, country, userType);
		return new ResponseEntity<>(fetchedUsers, HttpStatus.OK);
	}

	private List<User> fetchBySingleCondition(DiseaseType diseseType, String zipCode, String state, String country,
			UserType userType, List<User> fetchedUsers) {
		if (diseseType != null) {
			fetchedUsers = userService.findByDiseaseType(diseseType);
		} else if (zipCode != null) {
			fetchedUsers = userService.findByZipCode(zipCode);
		} else if (state != null) {
			fetchedUsers = userService.findByState(state);
		} else if (country != null) {
			fetchedUsers = userService.findByCountry(country);

		} else if (userType != null) {
			fetchedUsers = userService.findByUserType(userType);
		}
		return fetchedUsers;
	}

}
