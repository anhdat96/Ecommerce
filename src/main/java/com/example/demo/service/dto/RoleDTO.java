package com.example.demo.service.dto;

import com.example.demo.models.AbstractAuditingEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
@ToString
public class RoleDTO extends AbstractAuditingEntity implements Serializable {
    private Long roleID;
    private String manager;
    private String customer;
    private List<Long> user_ids;
    private Set<UserDTO> users = new HashSet<>();
}
