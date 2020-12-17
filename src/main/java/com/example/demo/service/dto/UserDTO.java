package com.example.demo.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.Instant;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
// bao jackson bỏ qua một trường trong quá trình tuần tự hóa nếu nó null
public class UserDTO {
    private Long userID;

    private String userEmail;

    private String userPassword;

    private String userFirstName;

    private String userLastName;

    private String userAddress;

    private String userPhone;

    private String gender;

    private String userCity;

    private String userState;

    private String userCountry;

    private Instant dateOfBirth;
}
