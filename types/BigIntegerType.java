package expression.types;

import java.math.BigInteger;

public class BigIntegerType implements GenericType<BigInteger>{
    @Override
    public BigInteger parseInt(int x) {
        return BigInteger.valueOf(x);
    }

    @Override
    public BigInteger add(BigInteger x, BigInteger y) {
        return x.add(y);
    }

    @Override
    public BigInteger subtract(BigInteger x, BigInteger y) {
        return x.subtract(y);
    }

    @Override
    public BigInteger pow(BigInteger x, BigInteger y) {
        return x.pow(y.intValue());
    }

    @Override
    public BigInteger log(BigInteger x, BigInteger y) {
        int step = 0;
        while (x.compareTo(y) >= 0) {
            x = x.divide(y);
            step++;
        }
        return BigInteger.valueOf(step);
    }

    @Override
    public BigInteger min(BigInteger x, BigInteger y) {
        return x.min(y);
    }

    @Override
    public BigInteger max(BigInteger x, BigInteger y) {
        return x.max(y);
    }

    @Override
    public BigInteger multiply(BigInteger x, BigInteger y) {
        return x.multiply(y);
    }

    @Override
    public BigInteger divide(BigInteger x, BigInteger y) {
        return x.divide(y);
    }

    @Override
    public BigInteger abs(BigInteger x) {
        return x.abs();
    }

    @Override
    public BigInteger negate(BigInteger x) {
        return x.negate();
    }

    @Override
    public String toString(BigInteger x) {
        return x.toString();
    }

    @Override
    public BigInteger parseValue(String value) {
        return new BigInteger(value);
    }

    @Override
    public BigInteger lzeroes(BigInteger x) {
        return BigInteger.valueOf(0);
    }

    @Override
    public BigInteger tzeroes(BigInteger x) {
        return BigInteger.valueOf(x.getLowestSetBit());
    }

    @Override
    public BigInteger count(BigInteger x) {
        return BigInteger.valueOf(x.bitCount());
    }
}
