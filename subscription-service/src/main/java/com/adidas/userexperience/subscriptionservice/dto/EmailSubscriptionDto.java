package com.adidas.userexperience.subscriptionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailSubscriptionDto {

    private String email;

    private String firstName;

    private String gender;

    private String dob;

    private boolean consent;
}
