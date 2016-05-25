/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdn.x.ui.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpSession;
import static org.omg.IOP.CodecPackage.InvalidTypeForEncodingHelper.id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author rizki.a.utomo
 */
@Controller
public class MemberProfileController {


    @RequestMapping(value = {"/about"}, method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {
        String dateStop = "07/14/2016 10:31:48";

        //HH converts hour in 24 hours format (0-23), day calculation
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        Date d1 = new Date();
        Date d2 = null;

        long diffDays = 0;
        try {

            d2 = format.parse(dateStop);

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();

            diffDays = diff / (24 * 60 * 60 * 1000);

            System.out.print(diffDays + " days, ");

        } catch (Exception e) {
            e.printStackTrace();
        }
        

        model.addAttribute("day", diffDays);

        return "about";

    }

}
