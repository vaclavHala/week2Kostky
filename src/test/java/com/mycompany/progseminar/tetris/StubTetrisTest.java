/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.tetris;

import static com.mycompany.progseminar.tetris.Pieces.L;
import static java.util.Arrays.asList;
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
public class StubTetrisTest {
    
    @Test
    public void test() {
        Tetris t = new StubTetris(Pieces.getShapesOf('L'), asList(
                ".....",
                ".....",
                ".....",
                ".X...",
                "XX.X.",
                ".XXXX",
                "XX.XX"
        ));
        AI ai = new AI(t);
        ai.Round();
    }
    
}
