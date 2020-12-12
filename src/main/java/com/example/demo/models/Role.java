package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "role")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
@ToString
public class Role extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleID")
    private Long roleID;
    @Column(name = "manager")
    private String manager;
    @Column(name = "customer")
    private String customer;
    @ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER, cascade = CascadeType.ALL) //nguyentrong edit
    @JsonIgnore
    private List<User> users = new ArrayList<>();


}
