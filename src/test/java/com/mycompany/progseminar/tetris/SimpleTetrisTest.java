/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.tetris;

import static java.util.Arrays.asList;
import static java.util.Collections.EMPTY_LIST;
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
public class SimpleTetrisTest {
    
  
    @Test
    public void testWidthHeight(){
        Tetris t = new SimpleTetris(3, 2, EMPTY_LIST);
        assertEquals(3, t.boardWidth());
        assertEquals(2, t.boardHeight());
    }
    
    @Test
    public void testAllFree(){
        Tetris t = new SimpleTetris(3, 2, EMPTY_LIST);
        assertFalse(t.isFree(0, 0));
        assertFalse(t.isFree(2, 0));
        assertFalse(t.isFree(0, 1));
        assertFalse(t.isFree(2, 1));
    }
    
    @Test
    public void testDrop(){
        
    }
    
    
}
