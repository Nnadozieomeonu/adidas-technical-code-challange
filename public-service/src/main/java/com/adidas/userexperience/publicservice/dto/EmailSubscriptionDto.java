package com.adidas.userexperience.publicservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailSubscriptionDto {

    private String email;

    private String firstName;

    private String gender;

    private String dob;

    private boolean consent;

}
