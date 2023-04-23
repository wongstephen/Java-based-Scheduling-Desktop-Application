package cms.stephenwongc195.model;

public class AppointmentsMonthType {
    String monthYear;
    String type;
    int count;

    public AppointmentsMonthType(String monthYear, String type, int count) {
        this.monthYear = monthYear;
        this.type = type;
        this.count = count;
    }

    public String getMonthYear() {
        return monthYear;
    }

    public int getYear(){
        String[] yearMonth = monthYear.split("-");
        return Integer.parseInt(yearMonth[0]);
    }

    public int getMonth(){
        String[] yearMonth = monthYear.split("-");
        return Integer.parseInt(yearMonth[1]);
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
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
        return monthYear + " " + type + " " + count;
    }
}
