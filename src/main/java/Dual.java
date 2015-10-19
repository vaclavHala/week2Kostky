
public class Dual {

    private int baseX, baseY;

    public static void main(String[] args) {
Dual d =new Dual(4, 3);
        d.print(d.fill());

    }

    public Dual(int baseX, int baseY) {
        this.baseX = baseX - 1;
        this.baseY = baseY - 1;
    }

    public boolean[][] fill() {
        boolean[][] grid = new boolean[8][8];
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                grid[i][j] = isDiag(i, j) || isHorVert(i, j);
            }

        }
        return grid;
    }

    //vrati tru jestli je na diagonale
    private boolean isDiag(int x, int y) {

        return Math.abs(x - baseX) == Math.abs(y - baseY);
    }

    //vrati tru jestli je na horiz nebo vert
    private boolean isHorVert(int x, int y) {
        return x == baseX || y == baseY;
    }

    public void print(boolean[][] grid) {
        //to je jedno co dostanes jen se to tu vykresli
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                char c = grid[x][y]
                        ? 'x'
                        : '.';
                if (y == baseY && x == baseX) {
                    System.out.print("*");
                } else {
                    System.out.print(c);
                }
            }
            System.out.println("");
        }

    }

}
