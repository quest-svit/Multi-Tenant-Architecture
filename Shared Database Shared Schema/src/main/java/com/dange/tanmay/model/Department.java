package com.dange.tanmay.model;

import com.dange.tanmay.multitenancy.TenantSupport;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
//import org.bson.codecs.pojo.annotations.BsonProperty;

import javax.persistence.*;

@Data
@Entity
@ToString
@Table(name="DEPARTMENT")
@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenantId", type = "string")})
@Filter(name = "tenantFilter", condition = "TENANT_ID = :tenantId")
public class Department implements TenantSupport {

    @Id
    @Column(name="ID")
    Long id;

    @Column(name="NAME")
    String name;

    @Column(name="DESCRIPTION")
    String description;

    @Column(name="HOD")
    String HeadOfDepartment;

    @Column(name="TENANT_ID")
    private String tenantId;

}
