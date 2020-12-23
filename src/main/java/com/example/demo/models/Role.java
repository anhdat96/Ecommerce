package com.example.demo.models;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "role")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
public class Role extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "manager")
    private String manager;
    @Column(name = "customer")
    private String customer;
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER) //nguyentrong edit
    private Set<User> users = new HashSet<>(); //nguyentrong edit


}
