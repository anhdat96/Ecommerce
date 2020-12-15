package com.example.demo.service.dto;

import com.example.demo.models.AbstractAuditingEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
@ToString
public class UserDTO extends AbstractAuditingEntity implements Serializable {
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
    private List<Long> role_ids;
    private Set<RoleDTO> roles = new HashSet<>();
    private List<Long> order_ids;
    private Set<OrderDTO> ordersList = new HashSet<>();
}
