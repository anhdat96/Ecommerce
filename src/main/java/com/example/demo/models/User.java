package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

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
    @Column(name = "userEmail")
    private String userEmail;
    @Column(name = "userPassword")
    private String userPassword;
    @Column(name = "userFirstName")
    private String userFirstName;
    @Column(name = "userLastName")
    private String userLastName;
    @Column(name = "userAddress")
    private String userAddress;
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

    @ManyToMany(fetch = FetchType.EAGER) //nguyentrong edit
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "userID"),
            inverseJoinColumns = @JoinColumn(name = "roleID"))
    private Set<Role> roles = new HashSet<>();

    //nguyentrong edit
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Orders> ordersList = new HashSet<>();
}
