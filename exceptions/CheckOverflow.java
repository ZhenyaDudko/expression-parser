package expression.exceptions;

public class CheckOverflow {
    public static boolean checkMultiply(int x, int y) {
        int maxResult;
        if (Integer.signum(x) == Integer.signum(y)) {
            maxResult = Integer.MAX_VALUE;
        } else {
            maxResult = Integer.MIN_VALUE;
        }
        return x != 0 && y != 0 && (x == -1 && y == Integer.MIN_VALUE || y == -1 && x == Integer.MIN_VALUE
                || x != -1 && (y > 0 && y > maxResult / x || y < 0 && y < maxResult / x));
    }

    public static boolean checkAdd(int x, int y) {
        return x >= 0 && Integer.MAX_VALUE - x < y || x < 0 && Integer.MIN_VALUE - x > y;
    }

    public static boolean checkSubtract(int x, int y) {
        return y > 0 && x < Integer.MIN_VALUE + y || y < 0 && x > Integer.MAX_VALUE + y;
    }

    public static boolean checkDivide(int x, int y) {
        return x == Integer.MIN_VALUE && y == -1;
    }
}
