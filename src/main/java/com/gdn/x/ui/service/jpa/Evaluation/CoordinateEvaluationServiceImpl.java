/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdn.x.ui.service.jpa.Evaluation;

import com.gdn.x.ui.model.evaluation.CoordinateEvaluation;
import com.gdn.x.ui.repository.Evaluation.CoordinateEvaluationRepository;
import com.gdn.x.ui.service.Evaluation.CoordinateEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rizki.a.utomo
 */
@Service
public class CoordinateEvaluationServiceImpl implements CoordinateEvaluationService {

    @Autowired
    CoordinateEvaluationRepository coordinateEvaluationRepository;
    
    @Override
    public CoordinateEvaluation insert(CoordinateEvaluation document) {
        return coordinateEvaluationRepository.insert(document);
    }
    
}
