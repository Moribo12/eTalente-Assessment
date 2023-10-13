package com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Investor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addr_id")
    private Address address;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contacts_id")
    private Contact contacts;
    private String firstname;
    private String lastname;
    private int age;
}
