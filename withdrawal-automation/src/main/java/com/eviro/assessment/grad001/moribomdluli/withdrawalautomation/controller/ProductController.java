package com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.controller;

import com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.dto.ProductDto;
import com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.dto.WithdrawalNoticeDto;
import com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.exception.ProductException;
import com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.services.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {


    @Autowired
    private ProductService prodService;


    @PostMapping
    public ProductDto addNewProduct(@RequestBody ProductDto dto){

        return prodService.addNewProduct(dto);
    }

    @GetMapping("/inv/{id}")
    public List<ProductDto> getInvestorProducts(@PathVariable long id){
        return prodService.getAllProductsByInvestorId(id);
    }

    @PostMapping("/inv/notice")
    public void getWithdrawalNotice(HttpServletResponse response, @RequestBody WithdrawalNoticeDto dto) throws ProductException {

        prodService.createWithdrawalNotice(response,dto.getProdId(), dto.getAmount());
    }

}
