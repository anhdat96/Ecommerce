package com.example.demo.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
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
public class User extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "user_email")
    private String userEmail;
    @Column(name = "user_password")
    private String userPassword;
    @Column(name = "user_first_name")
    private String userFirstName;
    @Column(name = "user_last_name")
    private String userLastName;
    @Column(name = "user_address")
    private String userAddress;
    @Column(name = "user_phone")
    private String userPhone;
    @Column(name = "gender")
    private String gender;
    @Column(name = "user_city")
    private String userCity;
    @Column(name = "user_state")
    private String userState;
    @Column(name = "user_country")
    private String userCountry;
    @Column(name = "date_of_birth")
    private Instant dateOfBirth;

    @ManyToMany(fetch = FetchType.EAGER) //nguyentrong edit
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();

    //nguyentrong edit
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Orders> ordersList = new HashSet<>();
}
