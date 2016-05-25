/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdn.x.ui.repository.Evaluation;

import com.gdn.x.ui.model.evaluation.ParameterWeight;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author alumunia
 */
@Repository
public interface ParameterWeightRepository extends MongoRepository<ParameterWeight, String> {

    List<ParameterWeight> findAll();

    

}
