package com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.repositories;

import com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Long> {

    List<Product> findAllByInvestor_Id(long id);

}
