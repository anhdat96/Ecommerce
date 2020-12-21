package com.example.demo.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDTO {

    private Long roleID;

    private String name;

    private String description;
}
