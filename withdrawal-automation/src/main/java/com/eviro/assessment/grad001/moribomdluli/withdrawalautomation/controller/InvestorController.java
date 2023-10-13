package com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.controller;

import com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.entity.Investor;
import com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.services.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/investor")
public class InvestorController {

    @Autowired
    private InvestorService investService;

    @PostMapping
    public Investor saveNewInvestor(@RequestBody Investor investor){
        return investService.addNewInvestor(investor);
    }

    @GetMapping("/{id}")
    public Investor getInvestorById(@PathVariable long id){
        return investService.getInvestorById(id);
    }




}
