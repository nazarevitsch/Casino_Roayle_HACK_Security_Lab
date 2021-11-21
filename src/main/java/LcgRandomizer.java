public class LcgRandomizer {

    private long a;
    private long m;
    private long c;

    private long lastNumber;


    public LcgRandomizer(LcgConstants lcgConstants, long lastNumber) {
        this.a = lcgConstants.getA();
        this.c = lcgConstants.getC();
        this.m = lcgConstants.getM();
        this.lastNumber = lastNumber;
    }

    public int getNumber() {
        lastNumber = (lastNumber * a + c) % m;
        return (int) lastNumber;
    }
}
