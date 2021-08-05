package com.irecover.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecover.entity.DiseaseType;
import com.irecover.entity.User;
import com.irecover.entity.UserType;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

	List<User> findByDiseaseType(DiseaseType diseaseType);

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

}
