/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.task2;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author xhala3
 */
public class Task2Test {
    
    @Test
    public void testColumnSize() {
        assertEquals(2, Task2.columnSize(4, 2));
        assertEquals(3, Task2.columnSize(5, 2));
        System.out.println(Task2.normalize("kdo honi dva zajice, nechyti zadneho"));
        assertEquals(4, Task2.columnSize(
                Task2.normalize("kdo honi dva zajice, nechyti zadneho").length(),
                "petrklic".length()));
    }
    
}
