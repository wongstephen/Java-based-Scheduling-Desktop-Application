package cms.stephenwongc195.model;

/**
 * This class is as a model for contacts
 */
public class Contact {
    private int contactId;
    private String contactName;
    private String contactEmail;

    public Contact(int contactId, String contactName, String contactEmail) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    /**
     * Returns contact id as an int
     * @return contact id
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Sets contact id as an int
     * @return void
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * Returns contact name as a String
     * @return contact name
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets contact name as a String
     * @return void
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Returns contact email as a String
     * @return contact email
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * Sets contact email as a String
     * @return void
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /**
     * Overrides toString method to return contact name and id
     * @return contact name and id as a String
     */
    @Override
    public String toString(){
        return contactName + " [ID# " + contactId + "]";
    }
}

