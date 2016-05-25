/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdn.x.ui.service.jpa.Evaluation;

import com.gdn.x.ui.model.evaluation.ParameterWeight;
import com.gdn.x.ui.repository.Evaluation.ParameterWeightRepository;
import com.gdn.x.ui.service.Evaluation.ParameterWeightService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author alumunia
 */
@Service
public class ParameterWeightServiceImpl implements ParameterWeightService {
    
    @Autowired
    ParameterWeightRepository parameterWeightRepository;

    @Override
    public ParameterWeight insert(ParameterWeight document) {
        return parameterWeightRepository.insert(document);
    }

    @Override
    public List<ParameterWeight> findAll() {
        return parameterWeightRepository.findAll((new Sort(Sort.Direction.DESC, "finalScore")));
    }

    @Override
    public void deleteAll() {
        parameterWeightRepository.deleteAll();
    }

  

    
    
}
