package cms.stephenwongc195.model;

/**
 * This class is used to store the division id, division name, and country id of a division
 */
public class Customer {
    private int customerId;
    private String customerName;
    private String customerAddress;
    private String customerPostalCode;
    private String customerPhone;
    private int customerDivisionId;

    public Customer(int customerId, String customerName, String customerAddress, String customerPostalCode, String customerPhone, int customerDivisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerPhone = customerPhone;
        this.customerDivisionId = customerDivisionId;
    }

    /**
     * Returns customer id as an int
     * @return customer id
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * sets customer id as an int
     * @return customer id
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Returns customer name as an String
     * @return customer name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Set customer name as an String
     * @return void
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Returns customer address as a String
     * @return customer address
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * Set customer address as a String
     * @return void
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     * Returns customer postal code as a String
     * @return customer postal code
     */
    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    /**
     * Set customer postal code as a String
     * @return void
     */
    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }

    /**
     * Returns customer phone as a String
     * @return customer phone
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * Set customer phone as a String
     * @return void
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    /**
     * Returns customer division id as an int
     * @return customer division id
     */
    public int getCustomerDivisionId() {
        return customerDivisionId;
    }

    /**
     * sets customer division id as an int
     * @return customer division id
     */
    public void setCustomerDivisionId(int customerDivisionId) {
        this.customerDivisionId = customerDivisionId;
    }

    /**
     * Returns customer name and id as a String
     * @return customer name and id
     */
    @Override
    public String toString() {
        return customerName +" [ID# "+customerId + "]"  ;
    }
}
