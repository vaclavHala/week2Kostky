/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.task3;

public class Task3 {

    public static void main(String[] args) {
        System.out.println(encode(6));
    }

    public static String encode(int num) {
        String last = "1";
        for (int i = 0; i < num; i++) {
            last = pass(last);
        }
        return last;
    }

    public static String pass(String base) {
        StringBuilder out = new StringBuilder();
        char cur = base.charAt(0);
        int len = 0;
        for (char c : base.toCharArray()) {
            if (c == cur) {
                len++;
            } else {
                out.append(len + "" + cur);
                cur = c;
                len = 1;
            }
        }
        out.append(len+""+cur);
        System.out.println(out);
        return out.toString();
    }

}
