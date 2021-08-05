package com.irecover.service;

import java.util.List;
import java.util.Optional;

import com.irecover.entity.DiseaseType;
import com.irecover.entity.User;
import com.irecover.entity.UserType;

public interface UserService {

	User findByEmail(String email);

	User save(User user);

	User update(User user, Long userId);

	Optional<User> findById(Long userId);

	void deleteById(Long userId);

	List<User> findByDiseaseType(DiseaseType diseseType);

	List<User> findByZipCode(String zipCode);

	List<User> findByState(String state);

	List<User> findByUserType(UserType userType);

	List<User> findByCountry(String country);

	List<User> findByDiseaseTypeAndZipCodeAndStateAndCountryAndUserType(DiseaseType diseseType, String zipCode,
			String state, String country, UserType userType);

	List<User> findByDiseaseTypeAndZipCode(DiseaseType diseseType, String zipCode);

	List<User> findByDiseaseTypeAndState(DiseaseType diseseType, String state);

	List<User> findByDiseaseTypeAndCountry(DiseaseType diseseType, String country);

	List<User> findByDiseaseTypeAndUserType(DiseaseType diseseType, UserType userType);

	List<User> findByZipCodeAndState(String zipCode, String state);

	List<User> findByZipCodeAndCountry(String zipCode, String country);

	List<User> findByZipCodeAndUserType(String zipCode, UserType userType);

	List<User> findByStateAndCountry(String state, String country);

	List<User> findByStateAndUserType(String state, UserType userType);

	List<User> findByCountryAndUserType(String country, UserType userType);

	Iterable<User> findAll();

}
