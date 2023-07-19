package com.dange.tanmay.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;

@ToString
@Data
@Entity
@Table(name="STUDENT")
public class Student {

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name="NAME")
	private String name;

	@Column(name="AGE")
	private int age;

	@Column(name="REGISTRATION_NUMBER")
	private String registrationNumber;

	@Transient
	private Long departmentId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DEPARTMENT_ID" , nullable = false)
	Department department;



}
