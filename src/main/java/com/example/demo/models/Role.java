package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
@Getter
@Setter
@ToString
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleID")
    private Long roleID;
    @Column(name = "manager")
    private String manager;
    @Column(name = "customer")
    private String customer;
    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();


}
