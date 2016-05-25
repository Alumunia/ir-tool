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
public class incre {

    public static void main(String[] args) {
        String[] num = {"2", "4", "6", "8", "10"};
        incremental(num);

    }

    static void incremental(String[] num) {
        int x = 0;
        int y = 0;
        int z = 0;

        while (x < 5) {
            List<String> num1 = new ArrayList<>();
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

            System.out.println("Ini apa" + num1.toString());
            for (int i = 0; i < num1.size(); i++) {

//                System.out.println(num1.get(i).toString());
//                System.out.println(num1.size());

            }
            System.out.println(num1.size());

        }

    }
}
