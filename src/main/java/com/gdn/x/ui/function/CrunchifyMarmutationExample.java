/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdn.x.ui.function;

/**
 *
 * @author alumunia
 */
import java.util.HashSet;
import java.util.Set;
 
/**
 * @author Crunchify.com
 * 
 */
 
public class CrunchifyMarmutationExample {
 
	public static void main(String[] args) {
		
		String s2 = "2468";
		
		System.out.println("\nString " + s2 + ":\nPermutations: " + crunchifyPermutation(s2));
	}
 
	public static Set<String> crunchifyPermutation(String str) {
		Set<String> crunchifyResult = new HashSet<String>();
		if (str == null) {
			return null;
		} else if (str.length() == 0) {
			crunchifyResult.add("");
			return crunchifyResult;
		}
 
		char firstChar = str.charAt(0);
		String rem = str.substring(1);
		Set<String> words = crunchifyPermutation(rem);
		for (String newString : words) {
			for (int i = 0; i <= newString.length(); i++) {
				crunchifyResult.add(crunchifyCharAdd(newString, firstChar, i));
			}
		}
		return crunchifyResult;
	}
 
	public static String crunchifyCharAdd(String str, char c, int j) {
		String first = str.substring(0, j);
		String last = str.substring(j);
		return first + c + last;
	}
 
}
