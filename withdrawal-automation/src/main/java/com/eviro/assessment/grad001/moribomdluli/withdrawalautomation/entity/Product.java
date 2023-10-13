package com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @ManyToOne
    @JoinColumn(name = "investor_id")
    private Investor investor;
    private ProductE investmentType;
    private String name;
    private double balance;


}
