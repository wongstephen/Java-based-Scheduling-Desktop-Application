package cms.stephenwongc195.dao;

import cms.stephenwongc195.model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

import static cms.stephenwongc195.dao.Query.tableQuery;

/**
 * This class is a data access object for contacts
 */
public class ContactDao {
    static ObservableList<Contact> allContacts = FXCollections.observableArrayList();

    /**
     * This method adds a contact to the allContacts ObservableList
     * @param contact
     */
    public static void addContact(Contact contact) {
        allContacts.add(contact);
    }

    /**
     * This method returns all contacts
     * @return allContacts List
     */
    public static ObservableList<Contact> getAllContacts() {
        return allContacts;
    }

    /**
     * This method deletes a contact from the allContacts ObservableList
     * @param contact
     */
    public static void deleteContact(Contact contact) {
        allContacts.remove(contact);
    }

    /**
     * This method returns a contact based on the contact id
     * @param id
     * @return contact
     */
    public static Contact getContactById(int id) {
        for (Contact contact : allContacts) {
            if (contact.getContactId() == id) {
                return contact;
            }
        }
        return null;
    }

    /**
     * This method updates the allContacts ObservableList
     */
    public static void updateAllContacts() {
        allContacts.clear();
        try {
            ResultSet rs = tableQuery("contacts");
            while (rs.next()) {
                Contact contact = new Contact(
                        rs.getInt("Contact_ID"),
                        rs.getString("Contact_Name"),
                        rs.getString("Email")
                );
                allContacts.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method updates the allContacts ObservableList on load
     */
    static {
       updateAllContacts();
    }
}
