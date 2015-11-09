
import com.mycompany.progseminar.week2.Point2;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author xhala3
 */
public class Horses2 {
    public static void main(String[] args) {
        Map<Point2, Integer> distances = new HashMap<>();
        Queue<Point2> q = new LinkedList<>();
        Point2 start = new Point2();
        q.add(new Point2(0, 0));
        int i=0;
        while(true){
            Queue<Point2> next = new LinkedList<>();
            for(Point2 p: q){
                if(!distances.containsKey(p)){
                    distances.
                }
                if(p.equals(start)){
                    System.out.println(i);
                    return;
                }
            }
            q = next;
            i ++;
        }
        Point2 target = new Point2(x, y);
        
    }
    
    
    
}
