/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdn.x.ui.service.Evaluation;

import com.gdn.x.ui.model.evaluation.ParameterWeight;
import java.util.List;

/**
 *
 * @author alumunia
 */
public interface ParameterWeightService {
    
    public ParameterWeight insert(ParameterWeight document);
    
    public List<ParameterWeight> findAll();
    
    public void deleteAll();
    
}
