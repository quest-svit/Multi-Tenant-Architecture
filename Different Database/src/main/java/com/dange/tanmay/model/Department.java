package com.dange.tanmay.model;

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
public class Department  {

    @Id
    @Column(name="ID")
    Long id;

    @Column(name="NAME")
    String name;

    @Column(name="DESCRIPTION")
    String description;

    @Column(name="HOD")
    String HeadOfDepartment;

}
