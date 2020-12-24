package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
@ToString
public class User extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    private Long userID;

    @Email
    @NotNull
    @Size(max=50)
    @Column(name = "userEmail")
    private String userEmail;

    @NotNull
    @Size(min = 5, max=100 )
    @Column(name = "userPassword" , nullable = false)
    private String userPassword;

    @Size(max = 200)
    @Column(name = "userFirstName")
    private String userFirstName;

    @Size(max = 200)
    @Column(name = "userLastName")
    private String userLastName;

    @Column(name = "userAddress")
    private String userAddress;

    @Size(max = 50)
    @Column(name = "userPhone")
    private String userPhone;

    @Column(name = "gender")
    private String gender;

    @Column(name = "userCity")
    private String userCity;
    @Column(name = "userState")
    private String userState;
    @Column(name = "userCountry")
    private String userCountry;
    @Column(name = "dateOfBirth")
    private Instant dateOfBirth;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "userID"),
            inverseJoinColumns = @JoinColumn(name = "roleID"))
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Orders> orderList = new ArrayList<>();


}
