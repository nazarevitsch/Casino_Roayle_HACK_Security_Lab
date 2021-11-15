public class MersenneTwisterRandomizer {

    private static final int w = 32;
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

    private long[] array;
    private int index;

    private long seed;

    public MersenneTwisterRandomizer(long[] array) {
        index = 0;
        this.array = array;
        twist();
    }

    public MersenneTwisterRandomizer(long seed) {
        this.seed = seed;

        index = n + 1;
        array = new long[n];
        array[0] = seed;
        for (int i = 1; i < n; i++) {
            array[i] = (f * (array[i - 1] ^ (array[i - 1] >> (w - 2))) + i) & Long.parseLong("ffffffff", 16);
        }
    }

    public long getRandomNumber() {
        if (index >= n){
            twist();
            index = 0;
        }

        long result = array[index];
        result = result ^ ((result >> u) & Long.parseLong(d, 16));
        result = result ^ ((result << s) & Long.parseLong(b, 16));
        result = result ^ ((result << t) & Long.parseLong(c, 16));
        result = result ^ (result >> l);

        index++;
        return result;
    }

    private void twist() {
        for (int i = 0; i < n; i++) {
            long x = (array[i] & Long.parseLong(upperMask, 16)) + (array[(i + 1) % n] & Long.parseLong(lowerMask, 16));
            long xA = x >> 1;
            if ((x % 2) != 0) {
                xA = xA ^ Long.parseLong(a, 16);
            }
            array[i] = array[(i + m) % n] ^ xA;
        }
    }
}