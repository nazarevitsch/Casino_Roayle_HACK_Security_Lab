import java.math.BigInteger;

public class LcgRandomizerHack {

    public static LcgConstants calculateConstants(long[] numbers) {
        LcgConstants constants = new LcgConstants();

        long[] newNumbers = calculateNewArray(numbers);
        BigInteger[] newBigNumbers = calculateNewArray2(newNumbers);
        long modulus = findModulusRecursive(newBigNumbers[1].abs(), newBigNumbers[0].abs());
        constants.setM(modulus);
        long multiplier = getMultiplier(numbers, modulus);
        constants.setA(multiplier);
        long increment = getIncrement(numbers, modulus, multiplier);
        constants.setC(increment);
        return constants;
    }

    private static long getIncrement(long[] numbers, long modulus, long multiplier) {
        return Math.floorMod((numbers[1] - numbers[0] * multiplier), modulus);
    }

    private static long getMultiplier(long[] numbers, long modulus) {
        BigInteger x = BigInteger.valueOf(numbers[2]).subtract(BigInteger.valueOf(numbers[1]));
        x = x.multiply(modInversion(numbers[1] - numbers[0], modulus));
        return Long.parseLong(x.abs().mod(BigInteger.valueOf(modulus)).toString());
    }

    private static BigInteger modInversion(long sub, long modulus) {
        long[] array = modInversionRecursive(sub, modulus);
        return BigInteger.valueOf(array[1]).abs().mod(BigInteger.valueOf(modulus).abs());
    }

    private static long[] modInversionRecursive(long sub, long modulus){
        if (sub == 0) {
            return new long[]{modulus, 0 , 1};
        } else {
            long[] array = modInversionRecursive(modulus % sub, sub);
            return new long[]{array[0], array[2] - Math.floorDiv(modulus, sub) * array[1], array[1]};
        }
    }

    private static long findModulusRecursive(BigInteger number1, BigInteger number2) {
        if (number1.compareTo(BigInteger.ZERO) == 0) {
            return Long.parseLong(number2.toString());
        } else {
            return findModulusRecursive(number2.mod(number1), number1);
        }
    }

    private static BigInteger[] calculateNewArray2(long[] numbers) {
        BigInteger[] newNumbers = new BigInteger[numbers.length - 2];
        for (int i = 2; i < numbers.length; i++) {
            BigInteger b1 = BigInteger.valueOf(numbers[i]).multiply(BigInteger.valueOf(numbers[i - 2]));
            BigInteger b2 = BigInteger.valueOf(numbers[i - 1]).multiply(BigInteger.valueOf(numbers[i - 1]));
            newNumbers[i - 2] = b1.subtract(b2);
        }
        return newNumbers;
    }

    private static long[] calculateNewArray(long[] numbers) {
        long[] newNumbers = new long[numbers.length - 1];
        for (int i = 1; i < numbers.length; i++) {
            newNumbers[i - 1] = numbers[i] - numbers[i - 1];
        }
        return newNumbers;
    }
}
