import java.math.BigInteger;

public class LcgRandomizerHack {

    public static LcgConstants calculateConstants(long[] numbers) {
        LcgConstants constants = new LcgConstants();
        long m = (long) Math.pow(2, 32);

        BigInteger a1 = BigInteger.valueOf(numbers[1]).subtract(BigInteger.valueOf(numbers[2]));
        BigInteger a2 = BigInteger.valueOf(numbers[0]).subtract(BigInteger.valueOf(numbers[1]));
        BigInteger a = (a1.multiply(a2.modInverse(BigInteger.valueOf(m)))).mod(BigInteger.valueOf(m));

        long c = (numbers[1] - a.longValue() * numbers[0]) % m;

        constants.setM(m);
        constants.setC(c);
        constants.setA(a.longValue());

        return constants;
    }
}
