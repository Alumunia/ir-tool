/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdn.x.ui.model;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author rizki.a.utomo
 */
@Document(collection = "tool_hist")
public class ContentGoldenList {

    private String query;

    List<String> expectedResult = new ArrayList<String>();
    
    List<String> actualResult = new ArrayList<String>();

//    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private String timeStamp;

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<String> getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(List<String> expectedResult) {
        this.expectedResult = expectedResult;
    }

}
