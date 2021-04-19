package com.adidas.userexperience.subscriptionservice.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="emailsubscriptions")
@Data
public class EmailSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String email;

    private String firstName;

    private String gender;

    private String dob;

    private boolean consent;
}
