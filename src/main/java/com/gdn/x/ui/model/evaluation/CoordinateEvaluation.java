/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdn.x.ui.model.evaluation;

import java.util.List;
import java.util.Map;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author rizki.a.utomo
 */
@Document(collection = "coordinate_db")
public class CoordinateEvaluation {

    private List<Double> coordinateEvaluation;

    private String timeStamp;
    
    private Map<String, String> parameter;

    public Map<String, String> getParameter() {
        return parameter;
    }

    public void setParameter(Map<String, String> parameter) {
        this.parameter = parameter;
    }
    
    

    public List<Double> getCoordinateEvaluation() {
        return coordinateEvaluation;
    }

    public void setCoordinateEvaluation(List<Double> coordinateEvaluation) {
        this.coordinateEvaluation = coordinateEvaluation;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

}
