/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author xhala3
 */
public class Horses {

    public static void main(String[] args) {
        System.out.println(doIt(8, 8));
    }

    public static int doIt(int rawX, int rawY) {
        int x = Math.max(rawX, rawY) - 1;
        int y = Math.min(rawX, rawY) - 1;
        int count = 0;
        int[] atSide = reachSide(x, y);
        System.out.println("side: " + atSide[2]);
        int reachEnd = reachEnd(atSide[0]);
        System.out.println("end: " + reachEnd);
        return reachEnd + atSide[2];
    }

    //1 x 2 y 3 pocet
    public static int[] reachSide(int x, int y) {
        int atX = x;
        int atY = y;
        int count = 0;
        while (atY > 0) {
            if (x == 1 && y == 1) {
                return new int[]{0, 0, count + 4};
            }

            if (atX > atY) {
                if (atX >= 2) {
                    atX -= 2;
                    atY -= 1;
                } else {
                    atX -= 1;
                    atY -= 2;
                }
            } else {
                if (atY >= 2) {
                    atY -= 2;
                    atX -= 1;
                } else {
                    atY -= 1;
                    atX -= 2;
                }
            }

            count++;
        }
        return new int[]{atX, atY, count};
    }

    public static int reachEnd(int x) {
        int atX = x;
        int count = 0;
        while (atX >= 4) {
            count += 2;
            atX -= 4;
        }
        switch (atX) {
            case 3:
                count += 3;
                break;
            case 2:
                count += 2;
                break;
            case 1:
                count += 3;
                break;
        }
        return count;
    }

}
