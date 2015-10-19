
import com.mycompany.progseminar.week2.Point;
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
        Map<Point, Integer> distances = new HashMap<>();
        Queue<Point> q = new LinkedList<>();
        Point start = new Point();
        q.add(new Point(0, 0));
        int i=0;
        while(true){
            Queue<Point> next = new LinkedList<>();
            for(Point p: q){
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
        Point target = new Point(x, y);
        
    }
    
    
    
}
