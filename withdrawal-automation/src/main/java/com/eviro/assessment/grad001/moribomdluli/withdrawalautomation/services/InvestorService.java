package com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.services;

import com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.entity.Investor;

public interface InvestorService {

    Investor addNewInvestor(Investor investor);
    Investor getInvestorById(long id);



}
