package expression.types;

public class LongType implements GenericType<Long> {
    @Override
    public Long parseInt(int x) {
        return (long) x;
    }

    @Override
    public Long add(Long x, Long y) {
        return x + y;
    }

    @Override
    public Long subtract(Long x, Long y) {
        return x - y;
    }

    @Override
    public Long pow(Long x, Long y) {
        int step = y.intValue();
        Long result = x;
        while (step > 1) {
            result *= x;
            step--;
        }
        return result;
    }

    @Override
    public Long log(Long x, Long y) {
        Long step = (long) 0;
        while (x >= y) {
            x = x / y;
            step++;
        }
        return step;
    }

    @Override
    public Long min(Long x, Long y) {
        return Long.min(x, y);
    }

    @Override
    public Long max(Long x, Long y) {
        return Long.max(x, y);
    }

    @Override
    public Long multiply(Long x, Long y) {
        return x * y;
    }

    @Override
    public Long divide(Long x, Long y) {
        return x / y;
    }

    @Override
    public Long abs(Long x) {
        return Math.abs(x);
    }

    @Override
    public Long negate(Long x) {
        return -x;
    }

    @Override
    public String toString(Long x) {
        return x.toString();
    }

    @Override
    public Long parseValue(String value) {
        return Long.parseLong(value);
    }

    @Override
    public Long lzeroes(Long x) {
        return (long) Long.numberOfLeadingZeros(x);
    }

    @Override
    public Long tzeroes(Long x) {
        return (long) Long.numberOfTrailingZeros(x);
    }

    @Override
    public Long count(Long x) {
        return (long) Long.bitCount(x);
    }
}
