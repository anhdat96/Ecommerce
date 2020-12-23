package com.example.demo.service.dto;

import com.example.demo.models.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDTO {

    private Long roleID;

    private String name;

    private String description;

    private List<Long> userId;
}
