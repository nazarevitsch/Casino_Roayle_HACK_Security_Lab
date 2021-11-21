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

    public long getNumber() {
        lastNumber = lastNumber * a;
        lastNumber = lastNumber > 4294967295L ? lastNumber - 4294967296L : lastNumber;
        lastNumber = lastNumber + c;
        lastNumber = lastNumber > 4294967295L ? lastNumber - 4294967296L : lastNumber;
        lastNumber = lastNumber % m;
        return lastNumber;
    }
}
