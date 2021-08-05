package com.irecover.entity;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

import lombok.Data;

@Data
@Entity
@Table(name = "suggestion")
public class Suggestion implements Serializable {

	private static final long serialVersionUID = 9157395051130877385L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.ORDINAL)
	private Type type;
	private String suggestion;
	private String name;
	private String address;
	private double latitude;
	private double longitude;

	@ElementCollection(fetch = FetchType.LAZY)
	@MapKeyColumn(name = "id", length = 50)
	@Column(name = "additionalInfo", length = 100)
	@BatchSize(size = 20)
	private Map<String, String> additionalInfo;

}
