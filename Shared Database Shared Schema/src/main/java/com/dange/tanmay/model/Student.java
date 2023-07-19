package com.dange.tanmay.model;

import com.dange.tanmay.multitenancy.TenantSupport;
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
@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenantId", type = "string")})
@Filter(name = "tenantFilter", condition = "TENANT_ID = :tenantId")
public class Student implements TenantSupport {

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

	@Column(name="TENANT_ID")
	private String tenantId;


}
