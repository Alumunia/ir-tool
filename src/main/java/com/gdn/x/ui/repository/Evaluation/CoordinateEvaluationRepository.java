/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdn.x.ui.repository.Evaluation;

import com.gdn.x.ui.model.evaluation.CoordinateEvaluation;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author rizki.a.utomo
 */
public interface CoordinateEvaluationRepository extends MongoRepository<CoordinateEvaluation, String> {
    
    
}
