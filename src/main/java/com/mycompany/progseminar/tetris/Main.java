/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.tetris;

import com.mycompany.progseminar.tetris.ai.AI;
import com.mycompany.progseminar.tetris.core.*;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author xhala3
 */
public class Main {

    public static void main(String[] args) {
        List<List<Shape>> shapes
            = "IIOZOTISJJLTISSOSLSIZJLOSSLOSOZILIOIOSLSLTZJJOIIILJLLZTZOITSLOZISIOZTLLLJJJOZIOLJJOZSITLTLOZTZSIJSJJZILOLJIZLLSZSSTSLTOSSOSOJIJLZITIZJZOZJLTSIOOSOLSLSSSOOZJSIIOZLTZJTLIJLJLJSOTSOILTSSZSISOTSOTTZZTTJZLOZLTOLZTJSOSLSILLISSZTSZLZLZIZJTJZTZLLOIIJIIIOTTIIIZZLJISLJISOOLOOOJSJOIZILLTTZZTTIOLZTJJOOTOILSOLJJSILIOJITTJJSOIITLSZOIZTSJOOZJSIZOSSSZJTTJSTSJIJSOLTTTIOOTTOTLILOIJSLZLOOSTTTSLOOTOZTSZLTSTIOZSSJTIZJLLOIOISSTTSTSOTTZJJSZJIOIIOZITLTITIISTOTTJSOLLOJJSTSLJJZSSJSLOOTOOOJITZOJLLITIJSSJSTZOOTOIZZZITOLSOO"
            .chars().mapToObj(c -> Pieces.getShapesOf((char) c))
            .collect(toList());
        SimpleTetris t = new SimpleTetris(10, 20, shapes.iterator());
        AI ai = new AI(t);
        int c = 0;
        while (!t.isOver()) {
            ai.round();
            t.render();
            c++;
        }
        System.out.println("GRAND TOTAL: " + c);

    }
}
