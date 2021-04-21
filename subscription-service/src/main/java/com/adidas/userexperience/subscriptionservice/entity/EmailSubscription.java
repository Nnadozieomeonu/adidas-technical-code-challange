package com.adidas.userexperience.subscriptionservice.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="emailsubscriptions")
@Data
public class EmailSubscription implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    @Email
    private String email;

    @Size(max = 100)
    private String firstName;

    @Size(max = 6)
    private String gender;

    @Column(nullable = false)
    private java.sql.Date dob;

    @Column(nullable = false)
    private boolean consent;

    @Basic(optional = false)
    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_on;
}
