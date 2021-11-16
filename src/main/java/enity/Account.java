package enity;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class Account implements Serializable {
    private long id;
    private int money;
//    private ZonedDateTime creationTime;

    public Account() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

//    public ZonedDateTime getCreationTime() {
//        return creationTime;
//    }
//
//    public void setCreationTime(ZonedDateTime creationTime) {
//        this.creationTime = creationTime;
//    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", money=" + money +
//                ", creationTime=" + creationTime +
                '}';
    }
}
