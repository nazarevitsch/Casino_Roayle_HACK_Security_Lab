public class MersenneTwisterRandomizerReverse {

    private static final long w = 32;
    private static final int n = 624;
    private static final int m = 397;
    private static final int r = 31;

    private static final String a = "9908B0DF";

    private static final int u = 11;
    private static final String d = "FFFFFFFF";
    private static final int s = 7;
    private static final String b = "9D2C5680";
    private static final int t = 15;
    private static final String c = "EFC60000";

    private static final int l= 18;
    private static final int f = 1812433253;

    private static final String lowerMask = "7FFFFFFF";
    private static final String upperMask = "80000000";

    private static final int SIZE = 624;
    private long[] array;

    public MersenneTwisterRandomizerReverse() {
        this.array = new long[SIZE];
    }

    public void put(int index, long value) {
        this.array[index] = reverse(value);
    }

    private long reverse(long value) {
        long x = value;
        x = reverseRight(x, l, (((long) 1 << w) - 1));
//        System.out.println((((long) 1 << w) - 1));
//        System.out.println("1: " + x);
        x = reverseLeft(x, t, Long.parseLong(c, 16));
//        System.out.println("2: " + x);
        x = reverseLeft(x, s, Long.parseLong(b, 16));
//        System.out.println("3: " + x);
        x = reverseRight(x, u, Long.parseLong(d, 16));
//        System.out.println("4: " + x);

        return x;
    }

    private long reverseRight(long y, long a, long b) {
        long x = 0;
        for (int i = 0; i < w; i++) {
            if (i < a) {
                x = x | getBit(y, i);
            } else {
                x = x | (getBit(y, i) ^ ((getBit(x, i - a) >> a) & getBit(b, i)));
            }
        }
        return x;
    }

    private long reverseLeft(long y, long a, long b) {
        return reverseBits(reverseRight(reverseBits(y), a, reverseBits(b)));
    }

    private long reverseBits(long x) {
        long rev = 0;
        for (int i = 0; i < w; i++) {
            rev = rev << 1;
            if (x > 0) {
                if ((x & 1) == 1) {
                    rev = rev ^ 1;
                }
                x = x >> 1;
            }
        }
        return rev;
    }

    private long getBit(long x, long i) {
        return (x & (1 << (w - i - 1)));
    }

    public long[] getArray() {
        return array;
    }
}
