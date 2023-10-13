package com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.dto;

import com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.entity.ProductE;
import lombok.Data;

@Data
public class ProductDto {

    private long productId;
    private long investorId;
    private String name;
    private ProductE investmentType;
    private double balance;



}
