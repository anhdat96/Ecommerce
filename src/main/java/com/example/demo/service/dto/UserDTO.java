package com.example.demo.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class UserDTO implements Serializable {
    @Null
    private Long userID;
    @NotNull
    private String userEmail;
    @NotNull
    private String userPassword;
    @NotNull
    private String userFirstName;
    @NotNull
    private String userLastName;
    @NotNull
    private String userAddress;
    @NotNull
    private String userPhone;
    @NotNull
    private String gender;
    @NotNull
    private String userCity;
    @NotNull
    private String userState;
    @NotNull
    private String userCountry;
    @NotNull
    private Instant dateOfBirth;
    private List<Long> roleIds;
    private List<Long> orderIds;
}
