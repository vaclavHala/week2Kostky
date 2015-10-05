/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.task0;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Task0 {

    public static void doIt(int num, Writer out) throws IOException {
        int at = -1;
        int max = -1;
        for (int i = 0; i < 1000; i++) {
            int sum = sumOfDivisors(i);
            if (sum > max) {
                at = i;
                max = sum;
            }
        }
        out.write(at + "; " + max);
    }

    public static int sumOfDivisors(int num) {
        return divisors(num).stream().collect(Collectors.summingInt((Integer x) -> x.intValue()));
    }

    public static List<Integer> divisors(int num) {
        List<Integer> divisors = new ArrayList<>();
        divisors.add(num);
        divisors.add(1);
        for (int i = num / 2; i > 1; i--) {
            if (num % i == 0) {
                divisors.add(i);
            }
        }
        return divisors;
    }

}
