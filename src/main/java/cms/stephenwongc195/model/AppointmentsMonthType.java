package cms.stephenwongc195.model;

import java.time.LocalDateTime;

public class AppointmentsMonthType {
    String month;
    String type;
    int count;

    public AppointmentsMonthType(String month, String type, int count) {
        this.month = month;
        this.type = type;
        this.count = count;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getMonthValue() {
        LocalDateTime month = LocalDateTime.of(2020, Integer.parseInt(this.month), 1, 0, 0);
        return month.getMonth().toString();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString () {
        return month + " " + type + " " + count;
    }
}
