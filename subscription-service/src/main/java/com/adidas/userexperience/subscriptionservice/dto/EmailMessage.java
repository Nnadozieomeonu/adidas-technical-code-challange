package com.adidas.userexperience.subscriptionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailMessage {

    private String name;

    @NotNull
    @Email(message = "Email field can not be null")
    private String email;

    @NotNull(message = "Subject field can not be null")
    private String subject;

    @NotNull(message = "Message field can not be null")
    @Min(10)
    private String message;
}
