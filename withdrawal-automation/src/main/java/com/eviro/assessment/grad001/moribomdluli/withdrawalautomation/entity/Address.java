package com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(name = "str_addr_1")
    private String streetAddr1;
    @Column(name = "str_addr_2")
    private String streetAddr2;
    private String suburb;
    private String city;
    private String country;

}
