package com.mycompany.progseminar.week2;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AlgoTest {

    @Test
    //2.5
    public void testCorrectSizes() {
        Algo.doIt(1).stream().forEach(p -> System.out.println(p.render()));
        System.out.println("-----------------------------");
        Algo.doIt(2).stream().forEach(p -> System.out.println(p.render()));
        System.out.println("-----------------------------");
        Algo.doIt(3).stream().forEach(p -> System.out.println(p.render()));
        System.out.println("-----------------------------");
        Algo.doIt(4).stream().forEach(p -> System.out.println(p.render()));
        System.out.println("-----------------------------");
        Algo.doIt(5).stream().forEach(p -> System.out.println(p.render()));
        //assertEquals(35, Algo.doIt(6).size());
        //assertEquals(369, Algo.doIt(8).size());
        //assertEquals(1285, Algo.doIt(9).size());
         assertEquals(4655, Algo.doIt(10).size());
    }

}
