public class LcgConstants {

    private long a;
    private long m;
    private long c;

    public long getA() {
        return a;
    }

    public void setA(long a) {
        this.a = a;
    }

    public long getM() {
        return m;
    }

    public void setM(long m) {
        this.m = m;
    }

    public long getC() {
        return c;
    }

    public void setC(long c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return "Our Lcg Constants: " +
                "a=" + a +
                ", m=" + m +
                ", c=" + c;
    }
}
