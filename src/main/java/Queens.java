
import com.mycompany.progseminar.week2.Point2;
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

    private List<Point2> queens = new ArrayList<>();
    private int n;

    public static void main(String[] args) {
        int n = 20;
        List<Point2> queens = new Queens().doIt(n);
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                char c = queens.contains(new Point2(x, y))
                        ? 'x'
                        : '.';
                System.out.print(c);

            }
            System.out.println("");
        }
    }

    public List<Point2> doIt(int n) {
        this.n = n;
        this.queens.add(new Point2(0, 0));

        while (this.queens.size() < n) {
            Optional<Point2> nextFree = findNextFree(last(queens));
            if (nextFree.isPresent()) {
                this.queens.add(nextFree.get());
            } else {
                while (true) {
                    Point2 last = last(queens);
                    this.queens.remove(last);
                    Optional<Point2> nextPossible = findNextFree(next(last));
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

    private Point2 last(List<Point2> l) {
        return l.get(l.size() - 1);
    }

    private boolean isFree(Point2 p) {
        for (Point2 queen : queens) {
            if (Math.abs(p.getX() - queen.getX()) == Math.abs(p.getY() - queen.getY())
                    || (p.getX() == queen.getX() || p.getY() == queen.getY())) {
                return false;
            }
        }
        return true;
    }

    private Point2 next(Point2 from) {
        if (from.getX() == n-1) {
            return new Point2(0, from.getY() + 1);
        }
        return new Point2(from.getX() + 1, from.getY());
    }

    private boolean hasNext(Point2 point) {
        return !(point.getX() == n - 1 && point.getY() == n - 1);
    }

    private Optional<Point2> findNextFree(Point2 from) {
        Point2 at = new Point2(from.getX(), from.getY());
        while (hasNext(at)) {
            if (isFree(at)) {
                return Optional.of(at);
            }
            at = next(at);
        }
        return Optional.empty();
    }

}
