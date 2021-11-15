package enity;

import java.io.Serializable;

public class Result implements Serializable {

    private Account account;
    private String message;
    private long realNumber;

    public Result() {}

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getRealNumber() {
        return realNumber;
    }

    public void setRealNumber(long realNumber) {
        this.realNumber = realNumber;
    }

    @Override
    public String toString() {
        return "Result{" +
                "account=" + account +
                ", message='" + message + '\'' +
                ", realNumber=" + realNumber +
                '}';
    }
}
