package com.adidas.userexperience.publicservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailSubscriptionDto {

    private String token;

    @NotNull(message = "Email field can not be blank or null")
    @Email
    private String email;

    @Size(max = 100, min = 1)
    private String firstName;

    @Size(max = 7, min = 1)
    private String gender;

    @NotNull(message = "Date of birth field can not be blank or null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dob;

    @NotNull(message = "Consent field can not be blank or null")
    private boolean consent;

}
