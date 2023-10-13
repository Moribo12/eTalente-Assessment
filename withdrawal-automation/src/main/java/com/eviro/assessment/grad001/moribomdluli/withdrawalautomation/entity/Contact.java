package com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String email;
    @Column(name = "cel_number")
    private String celNumber;
    @Column(name = "home_tel")
    private String homeTel;
    @Column(name = "work_tel")
    private String workTel;

}
