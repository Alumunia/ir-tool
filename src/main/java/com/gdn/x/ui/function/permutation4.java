/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdn.x.ui.function;

import com.gdn.x.ui.controller.Evaluation.CommonControllerEvaluation;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.solr.client.solrj.SolrServerException;

/**
 *
 * @author alumunia
 */
public class permutation4 {
    public static void main(String[] args) {
        
    }
    
        protected void fetchParameter() {
        String[] num = {"2", "4", "6", "8", "10"};
        List<List<String>> listActualId = new ArrayList<List<String>>();
        List<List<String>> listExpectedId = new ArrayList<List<String>>();
        int w = 0;
        int x = 0;
        int y = 0;
        int z = 0;

        while (w < 5) {
            List<String> num1 = new ArrayList<>();
            num1.add(num[w]);
            num1.add(num[x]);
            num1.add(num[y]);
            num1.add(num[z]);

            z++;
            if (z == 5) {
                y++;
                z = 0;
            }
            if (y == 5) {
                x++;
                y = 0;
            }
            if (x == 5) {
                w++;
                x = 0;
            }

           
        }

    }
    
}
