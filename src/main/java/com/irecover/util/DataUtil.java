package com.irecover.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class DataUtil {
	
	public List<Object> getHospitalInfo() {
		List<Object> listofHospitals = new ArrayList<>();

		Map<String, String> map = new HashMap<>();
		map.put("name", "KIMS");
		map.put("address", "Hyderabad");

		Map<String, String> map2 = new HashMap<>();
		map2.put("name", "RIMS");
		map2.put("address", "Hyderabad");

		Map<String, String> map3 = new HashMap<>();
		map3.put("name", "CONTINENTAL");
		map3.put("address", "Hyderabad");

		Map<String, String> map4 = new HashMap<>();
		map4.put("name", "APOLLO");
		map4.put("address", "Hyderabad");

		Map<String, String> map5 = new HashMap<>();
		map5.put("name", "BASAVATARAKAM");
		map5.put("address", "Hyderabad");

		listofHospitals.add(map);
		listofHospitals.add(map2);
		listofHospitals.add(map3);
		listofHospitals.add(map4);
		listofHospitals.add(map5);
		return listofHospitals;
	}
	
	
	public List<Map<String, String>> bloodInfo() {
		List<Map<String, String>> listofHospitals = new ArrayList<>();

		Map<String, String> map = new HashMap<>();
		map.put("bloodGgroup", "A+ve");
		map.put("address", "Kukatpaly");

		Map<String, String> map2 = new HashMap<>();
		map2.put("bloodGgroup", "O-ve");
		map2.put("address", "chandanagar");

		Map<String, String> map3 = new HashMap<>();
		map3.put("bloodGgroup", "o+ve");
		map3.put("address", "madhapur");

		Map<String, String> map4 = new HashMap<>();
		map4.put("bloodGgroup", "AB-ve");
		map4.put("address", "dilsuknagar");

		Map<String, String> map5 = new HashMap<>();
		map5.put("bloodGgroup", "b-ve");
		map5.put("address", "jublihills");

		listofHospitals.add(map);
		listofHospitals.add(map2);
		listofHospitals.add(map3);
		listofHospitals.add(map4);
		listofHospitals.add(map5);
		return listofHospitals;
	}
	
	public List<Map<String, String>> plasmaInfo() {
		List<Map<String, String>> listofHospitals = new ArrayList<>();

		Map<String, String> map = new HashMap<>();
		map.put("type", "plasma");
		map.put("address", "Kukatpaly");

		Map<String, String> map2 = new HashMap<>();
		map2.put("type", "plasma");
		map2.put("address", "chandanagar");

		Map<String, String> map3 = new HashMap<>();
		map3.put("type", "plasma");
		map3.put("address", "madhapur");

		Map<String, String> map4 = new HashMap<>();
		map4.put("type", "plasma");
		map4.put("address", "dilsuknagar");

		Map<String, String> map5 = new HashMap<>();
		map5.put("type", "plasma");
		map5.put("address", "jublihills");

		listofHospitals.add(map);
		listofHospitals.add(map2);
		listofHospitals.add(map3);
		listofHospitals.add(map4);
		listofHospitals.add(map5);
		return listofHospitals;
	}
	
	
	public List<Map<String, String>> doctorsInfo() {
		List<Map<String, String>> listofHospitals = new ArrayList<>();

		Map<String, String> map = new HashMap<>();
		map.put("doctorName", "surendraNadh");
		map.put("specialization", "M.D.Criticalcare");
		map.put("address", "Kukatpaly");

		Map<String, String> map2 = new HashMap<>();
		map2.put("doctorName", "charan");
		map2.put("specialization", "anesthesia specialist");
		map2.put("address", "chandanagar");

		Map<String, String> map3 = new HashMap<>();
		map3.put("doctorName", "SivaKumar");
		map3.put("specialization", "Cardiologist");
		map3.put("address", "madhapur");

		Map<String, String> map4 = new HashMap<>();
		map4.put("doctorName", "SarathChand");
		map4.put("specialization", "Radiology");
		map4.put("address", "dilsuknagar");

		Map<String, String> map5 = new HashMap<>();
		map5.put("doctorName", "prasadRao");
		map5.put("specialization", "GeneralSurgin");
		map5.put("address", "jublihills");

		listofHospitals.add(map);
		listofHospitals.add(map2);
		listofHospitals.add(map3);
		listofHospitals.add(map4);
		listofHospitals.add(map5);
		
		return listofHospitals;
	}
	

}
