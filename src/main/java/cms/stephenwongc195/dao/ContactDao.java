package cms.stephenwongc195.dao;

import cms.stephenwongc195.model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

import static cms.stephenwongc195.dao.Query.tableQuery;

public class ContactDao {
    static ObservableList<Contact> allContacts = FXCollections.observableArrayList();

    public static void addContact(Contact contact) {
        allContacts.add(contact);
    }

    public static ObservableList<Contact> getAllContacts() {
        return allContacts;
    }

    public static void deleteContact(Contact contact) {
        allContacts.remove(contact);
    }

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

    static {
       updateAllContacts();
    }
}
