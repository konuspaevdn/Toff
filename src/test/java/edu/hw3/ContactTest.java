package edu.hw3;

import edu.hw3.Task5.Contacts;
import edu.hw3.Task5.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ContactTest {

    @Test
    @DisplayName("Sort contacts")
    void checkParseContacts() {
        String[] contacts1 = new String[]{"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        String[] contacts1ASC = new String[]{"Thomas Aquinas", "Rene Descartes", "David Hume", "John Locke"};
        assertThat(Contacts.parseContacts(contacts1, Order.ASC)).isEqualTo(contacts1ASC);

        String[] contacts2 = new String[]{"Paul Erdos", "Leonhard Euler", "Carl Gauss"};
        String[] contacts2DESC = new String[]{"Carl Gauss", "Leonhard Euler", "Paul Erdos"};
        assertThat(Contacts.parseContacts(contacts2, Order.DESC)).isEqualTo(contacts2DESC);

        String[] contacts3 = new String[]{"Paul Erdos", "Leonhard", "Carl Gauss"};
        String[] contacts3ASC = new String[]{"Paul Erdos", "Carl Gauss", "Leonhard"};
        assertThat(Contacts.parseContacts(contacts3, Order.ASC)).isEqualTo(contacts3ASC);

        assertThat(Contacts.parseContacts(null, Order.DESC)).isEqualTo(new String[]{});
    }
}
