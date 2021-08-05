package com.irecover.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name = "blog")
@Data
public class UserBlog implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private String content;
	private UserType userType;
	
    @OneToMany(mappedBy = "userBlog", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<HashTag> hashtag;
    
    private String profileImageName;
	private String profileImageUrl;

	@JsonManagedReference
    @JsonIgnore
	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User userId;

}
