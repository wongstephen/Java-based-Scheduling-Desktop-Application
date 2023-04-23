package cms.stephenwongc195.model;

public class AppointmentsByCustomer {
    String customerName;
    int count;

    public AppointmentsByCustomer(String customerName, int count) {
        this.customerName = customerName;
        this.count = count;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
