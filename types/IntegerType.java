package expression.types;

import expression.exceptions.CheckOverflow;
import expression.exceptions.DivisionByZeroException;
import expression.exceptions.IllegalArgumentException;
import expression.exceptions.OverflowException;

public class IntegerType extends UIntegerType {

    @Override
    public Integer add(Integer x, Integer y) {
        if (CheckOverflow.checkAdd(x, y)) {
            throw new OverflowException("Overflow in operation: " + x + " + " + y);
        }
        return x + y;
    }

    @Override
    public Integer subtract(Integer x, Integer y) {
        if (CheckOverflow.checkSubtract(x, y)) {
            throw new OverflowException("Overflow in operation: " + x + " - " + y);
        }
        return x - y;
    }

    @Override
    public Integer pow(Integer x, Integer y) {
        if (y < 0 || x == 0 && y == 0) {
            throw new IllegalArgumentException("Illegal arguments for pow function: " + x + " " + y);
        }
        if (x == 1) {
            return 1;
        }
        if (x == -1) {
            return -1 * (y % 2 == 0 ? -1 : 1);
        }
        int result = 1;
        for (int i = 0; i < y; i++) {
            if (CheckOverflow.checkMultiply(result, x)) {
                throw new OverflowException("Overflow in operation: " + x + " ** " + y);
            }
            result *= x;
        }
        return result;
    }

    @Override
    public Integer log(Integer x, Integer y) {
        if (x > 0 && y > 0 && y != 1) {
            return super.log(x, y);
        } else {
            throw new IllegalArgumentException("Illegal arguments for log function: " + x + " " + y);
        }
    }

    @Override
    public Integer multiply(Integer x, Integer y) {
        if (CheckOverflow.checkMultiply(x, y)) {
            throw new OverflowException("Overflow in operation: " + x + " * " + y);
        }
        return x * y;
    }

    @Override
    public Integer divide(Integer x, Integer y) {
        if (y == 0) {
            throw new DivisionByZeroException("Division by zero in operation: " + x + " / " + y);
        } else if (CheckOverflow.checkDivide(x, y)) {
            throw new OverflowException("Overflow in operation: " + x + " / " + y);
        }
        return x / y;
    }

    @Override
    public Integer abs(Integer x) {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException("Overflow in operation: abs " + x);
        }
        return Math.abs(x);
    }

    @Override
    public Integer negate(Integer x) {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException("Overflow in operation: -" + x);
        }
        return -x;
    }

}
