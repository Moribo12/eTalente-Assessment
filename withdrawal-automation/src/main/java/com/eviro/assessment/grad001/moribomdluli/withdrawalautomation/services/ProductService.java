package com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.services;

import com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.dto.ProductDto;
import com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.exception.ProductException;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface ProductService {

    ProductDto addNewProduct(ProductDto dto);
    List<ProductDto> getAllProductsByInvestorId(long investorId);
    void createWithdrawalNotice(HttpServletResponse response,long prodId,double requestedAmt)throws ProductException;


}
