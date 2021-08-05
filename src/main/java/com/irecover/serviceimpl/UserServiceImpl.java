package com.irecover.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irecover.entity.DiseaseType;
import com.irecover.entity.User;
import com.irecover.entity.UserType;
import com.irecover.repository.UserRepository;
import com.irecover.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User update(User user, Long userId) {
		User updateUser = user;
		updateUser.setId(userId);
		return userRepository.save(updateUser);
	}

	@Override
	public Optional<User> findById(Long userId) {
		return userRepository.findById(userId);
	}

	@Override
	public void deleteById(Long userId) {
		userRepository.deleteById(userId);

	}

	@Override
	public List<User> findByDiseaseType(DiseaseType diseseType) {
		return userRepository.findByDiseaseType(diseseType);

	}

	@Override
	public List<User> findByZipCode(String zipCode) {
		return userRepository.findByZipCode(zipCode);

	}

	@Override
	public List<User> findByState(String state) {
		return userRepository.findByState(state);

	}

	@Override
	public List<User> findByUserType(UserType userType) {
		return userRepository.findByUserType(userType);

	}

	@Override
	public List<User> findByCountry(String country) {
		return userRepository.findByCountry(country);

	}

	@Override
	public List<User> findByDiseaseTypeAndZipCodeAndStateAndCountryAndUserType(DiseaseType diseseType, String zipCode,
			String state, String country, UserType userType) {
		return userRepository.findByDiseaseTypeAndZipCodeAndStateAndCountryAndUserType(diseseType, zipCode, state,
				country, userType);

	}

	@Override
	public List<User> findByDiseaseTypeAndZipCode(DiseaseType diseseType, String zipCode) {
		return userRepository.findByDiseaseTypeAndZipCode(diseseType, zipCode);

	}

	@Override
	public List<User> findByDiseaseTypeAndState(DiseaseType diseseType, String state) {
		return userRepository.findByDiseaseTypeAndState(diseseType, state);

	}

	@Override
	public List<User> findByDiseaseTypeAndCountry(DiseaseType diseseType, String country) {
		return userRepository.findByDiseaseTypeAndCountry(diseseType, country);

	}

	@Override
	public List<User> findByDiseaseTypeAndUserType(DiseaseType diseseType, UserType userType) {
		return userRepository.findByDiseaseTypeAndUserType(diseseType, userType);

	}

	@Override
	public List<User> findByZipCodeAndState(String zipCode, String state) {
		return userRepository.findByZipCodeAndState(zipCode, state);

	}

	@Override
	public List<User> findByZipCodeAndCountry(String zipCode, String country) {
		return userRepository.findByZipCodeAndCountry(zipCode, country);

	}

	@Override
	public List<User> findByZipCodeAndUserType(String zipCode, UserType userType) {
		return userRepository.findByZipCodeAndUserType(zipCode, userType);

	}

	@Override
	public List<User> findByStateAndCountry(String state, String country) {
		return userRepository.findByStateAndCountry(state, country);

	}

	@Override
	public List<User> findByStateAndUserType(String state, UserType userType) {
		return userRepository.findByStateAndUserType(state, userType);

	}

	@Override
	public List<User> findByCountryAndUserType(String country, UserType userType) {
		return userRepository.findByCountryAndUserType(country, userType);

	}

	@Override
	public Iterable<User> findAll() {
		return userRepository.findAll();

	}

}
