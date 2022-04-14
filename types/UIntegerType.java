package expression.types;

public class UIntegerType implements GenericType<Integer> {

    @Override
    public Integer parseInt(int x) {
        return x;
    }

    @Override
    public Integer add(Integer x, Integer y) {
        return x + y;
    }

    @Override
    public Integer subtract(Integer x, Integer y) {
        return x - y;
    }

    @Override
    public Integer pow(Integer x, Integer y) {
        if (x == 1) {
            return 1;
        }
        if (x == -1) {
            return -1 * (y % 2 == 0 ? -1 : 1);
        }
        int result = 1;
        for (int i = 0; i < y; i++) {
            result *= x;
        }
        return result;
    }

    @Override
    public Integer log(Integer x, Integer y) {
        int step = 0;
        while (x >= y) {
            x /= y;
            step++;
        }
        return step;
    }

    @Override
    public Integer min(Integer x, Integer y) {
        return Math.min(x, y);
    }

    @Override
    public Integer max(Integer x, Integer y) {
        return Math.max(x, y);
    }

    @Override
    public Integer multiply(Integer x, Integer y) {
        return x * y;
    }

    @Override
    public Integer divide(Integer x, Integer y) {
        return x / y;
    }

    @Override
    public Integer abs(Integer x) {
        return Math.abs(x);
    }

    @Override
    public Integer negate(Integer x) {
        return -x;
    }

    @Override
    public String toString(Integer x) {
        return Integer.toString(x);
    }

    @Override
    public Integer parseValue(String value) {
        return Integer.parseInt(value);
    }

    @Override
    public Integer lzeroes(Integer x) {
        return Integer.numberOfLeadingZeros(x);
    }

    @Override
    public Integer tzeroes(Integer x) {
        return Integer.numberOfTrailingZeros(x);
    }

    @Override
    public Integer count(Integer x) {
        return Integer.bitCount(x);
    }

}
