package com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.services.impl;

import com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.entity.Investor;
import com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.repositories.InvestorRepo;
import com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.services.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvestorServiceImpl implements InvestorService {

    @Autowired
    private InvestorRepo investorRepo;

    @Override
    public Investor addNewInvestor(Investor investor) {
        return investor==null? null:investorRepo.save(investor);
    }

    @Override
    public Investor getInvestorById(long id) {
        return id<1? null:investorRepo.findById(id).orElseThrow();
    }
}
