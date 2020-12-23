package com.example.demo.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class RoleDTO implements Serializable {
    @Null
    private Long id;
    @NotNull
    private String manager;
    @NotNull
    private String customer;
    private List<Long> userIds;
}
