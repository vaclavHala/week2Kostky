/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.task0;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class NewClassTest {
    
    @Test
    public void base(){
        assertEquals(18, Task0.sumOfDivisors(10));
    }
    
    @Test
    public void testDivisors(){
        List<Integer> divisors = Task0.divisors(10);
        assertTrue(divisors.contains(1));
        assertTrue(divisors.contains(2));
        assertTrue(divisors.contains(5));
        assertTrue(divisors.contains(10));
    }
    
    @Test
    public void foo() throws IOException{
        StringWriter writer = new StringWriter();
        Task0.doIt(1000, writer);
        System.out.println(writer.toString());
    }
    
}
