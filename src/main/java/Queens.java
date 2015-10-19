
import com.mycompany.progseminar.week2.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author xhala3
 */
public class Queens {

    private List<Point> queens = new ArrayList<>();
    private int n;

    public static void main(String[] args) {
        int n = 20;
        List<Point> queens = new Queens().doIt(n);
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                char c = queens.contains(new Point(x, y))
                        ? 'x'
                        : '.';
                System.out.print(c);

            }
            System.out.println("");
        }
    }

    public List<Point> doIt(int n) {
        this.n = n;
        this.queens.add(new Point(0, 0));

        while (this.queens.size() < n) {
            Optional<Point> nextFree = findNextFree(last(queens));
            if (nextFree.isPresent()) {
                this.queens.add(nextFree.get());
            } else {
                while (true) {
                    Point last = last(queens);
                    this.queens.remove(last);
                    Optional<Point> nextPossible = findNextFree(next(last));
                    if(nextPossible.isPresent()){
                        this.queens.add(nextPossible.get());
                        break;
                    } 
                }
            }
        }
        System.out.println(queens.size());
        System.out.println(queens);
        return queens;
    }

    private Point last(List<Point> l) {
        return l.get(l.size() - 1);
    }

    private boolean isFree(Point p) {
        for (Point queen : queens) {
            if (Math.abs(p.getX() - queen.getX()) == Math.abs(p.getY() - queen.getY())
                    || (p.getX() == queen.getX() || p.getY() == queen.getY())) {
                return false;
            }
        }
        return true;
    }

    private Point next(Point from) {
        if (from.getX() == n-1) {
            return new Point(0, from.getY() + 1);
        }
        return new Point(from.getX() + 1, from.getY());
    }

    private boolean hasNext(Point point) {
        return !(point.getX() == n - 1 && point.getY() == n - 1);
    }

    private Optional<Point> findNextFree(Point from) {
        Point at = new Point(from.getX(), from.getY());
        while (hasNext(at)) {
            if (isFree(at)) {
                return Optional.of(at);
            }
            at = next(at);
        }
        return Optional.empty();
    }

}
