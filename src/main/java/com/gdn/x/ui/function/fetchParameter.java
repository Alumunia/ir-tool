/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdn.x.ui.function;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumunia
 */
public class fetchParameter {

    public static void main(String[] args) {
        String[] fieldList = {"nameSearch", "brandSearch", "descriptionSearch","categories"};
        fetchParameter(fieldList);

    }

    protected static void fetchParameter(String[] fieldList) {
        String[] num = {"2", "4", "6", "8", "10"};
        int w = 0;
        int x = 0;
        int y = 0;
        int z = 0;
        int is = 0;

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
            String var_parameter = "";
            for (int i = 0; i < num1.size(); i++) {

                int len = fieldList.length;

                var_parameter += fieldList[i] + "^" + num1.get(i) + " ";

            }
            System.out.println(var_parameter);
            System.out.println(var_parameter.length());
            System.out.println(is);
            is++;
//            setParameter(var_parameter);
        }

    }

}
