package edu.hw7;

import edu.hw7.Task3.DB.Person;
import edu.hw7.Task3.Service;
import edu.hw7.Task3.ServiceImproved;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PersonDBTest {
    private final Predicate<Person> completeProfile = person -> person.name() != null
        && person.address() != null
        && person.phoneNumber() != null;
    @Test
    @DisplayName("Synchronized service test")
    void SyncedServiceTest() {
        var service = new ServiceImproved();
        AtomicInteger counter = new AtomicInteger();
        var users = new Thread(() -> {
           service.add(new Person(counter.getAndIncrement(), "Kamilla", "NY", "+14193529157"));
           service.add(new Person(counter.getAndIncrement(), "Kian", "Washington", "+12199842448"));
           service.add(new Person(counter.getAndIncrement(), "Kiara", "Albany", "+12144235460"));
           service.add(new Person(counter.getAndIncrement(), "Kevin", "Salt Lake City", "+16368312770"));
           service.add(new Person(counter.getAndIncrement(), "Kendall", "Chicago", "+18608318706"));
           service.add(new Person(counter.getAndIncrement(), "Kennedy", "Utica", "+16188476148"));
           service.add(new Person(counter.getAndIncrement(), "Kamilla", "Dallas", "88005553555"));
           service.add(new Person(counter.getAndIncrement(), "Kelly", "Albany", "+17026106914"));
           service.add(new Person(counter.getAndIncrement(), "Kevin", "Dallas", "+12097450636"));
           service.add(new Person(counter.getAndIncrement(), "Karen", "NY", "+16123695816"));
           service.add(new Person(counter.getAndIncrement(), "Katherine", "Detroit", "+17038760537"));
           service.add(new Person(counter.getAndIncrement(), "Kelly", "Las Vegas", "+14147123302"));
           service.add(new Person(counter.getAndIncrement(), "Kian", "LA", "+17243148015"));

        });
        var users2 = new Thread(() -> {
            service.add(new Person(counter.getAndIncrement(), "Ken", "NY", "+14193529158"));
            service.add(new Person(counter.getAndIncrement(), "Kristy", "Washington", "+12189842448"));
            service.add(new Person(counter.getAndIncrement(), "Kacey", "Albany", "+12144238460"));
            service.add(new Person(counter.getAndIncrement(), "Karter", "Salt Lake City", "+16868312770"));
            service.add(new Person(counter.getAndIncrement(), "Kala", "Chicago", "+18608318806"));
            service.add(new Person(counter.getAndIncrement(), "Kennedy", "Utica", "+16188478148"));
            service.add(new Person(counter.getAndIncrement(), "Kamilla", "Dallas", "88005558555"));
            service.add(new Person(counter.getAndIncrement(), "Karter", "Albany", "+17026806914"));
            service.add(new Person(counter.getAndIncrement(), "Kelly", "Dallas", "+12097850636"));
            service.add(new Person(counter.getAndIncrement(), "Kaia", "NY", "+16123695886"));
            service.add(new Person(counter.getAndIncrement(), "Katherine", "Detroit", "+18038760537"));
            service.add(new Person(counter.getAndIncrement(), "Kelly", "Las Vegas", "+14847123302"));
            service.add(new Person(counter.getAndIncrement(), "Kane", "LA", "+17243148815"));
        });
        var request1 = new Thread(() -> {
            var list1 = service.findByName("Kelly");
            assertThat(list1.stream().filter(completeProfile).count()).isEqualTo(list1.size());
        });

        var request2 = new Thread(() -> {
            var list2 = service.findByAddress("Dallas");
            assertThat(list2.stream().filter(completeProfile).count()).isEqualTo(list2.size());
        });

        var request3 = new Thread(() -> {
            var list3 = service.findByName("+16123695886");
            assertThat(list3.stream().filter(completeProfile).count()).isEqualTo(list3.size());
        });

        users.start();
        users2.start();
        request1.start();
        request2.start();
        request3.start();
        try {
            users.join();
            users2.join();
            request1.join();
            request2.join();
            request3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("ReadWriteLock service test")
    void ReadWriteServiceTest() {
        Service service = new Service();
        AtomicInteger counter = new AtomicInteger();
        var users = new Thread(() -> {
            service.add(new Person(counter.getAndIncrement(), "Kamilla", "NY", "+14193529157"));
            service.add(new Person(counter.getAndIncrement(), "Kian", "Washington", "+12199842448"));
            service.add(new Person(counter.getAndIncrement(), "Kiara", "Albany", "+12144235460"));
            service.add(new Person(counter.getAndIncrement(), "Kevin", "Salt Lake City", "+16368312770"));
            service.add(new Person(counter.getAndIncrement(), "Kendall", "Chicago", "+18608318706"));
            service.add(new Person(counter.getAndIncrement(), "Kennedy", "Utica", "+16188476148"));
            service.add(new Person(counter.getAndIncrement(), "Kamilla", "Dallas", "88005553555"));
            service.add(new Person(counter.getAndIncrement(), "Kelly", "Albany", "+17026106914"));
            service.add(new Person(counter.getAndIncrement(), "Kevin", "Dallas", "+12097450636"));
            service.add(new Person(counter.getAndIncrement(), "Karen", "NY", "+16123695816"));
            service.add(new Person(counter.getAndIncrement(), "Katherine", "Detroit", "+17038760537"));
            service.add(new Person(counter.getAndIncrement(), "Kelly", "Las Vegas", "+14147123302"));
            service.add(new Person(counter.getAndIncrement(), "Kian", "LA", "+17243148015"));

        });
        var users2 = new Thread(() -> {
            service.add(new Person(counter.getAndIncrement(), "Ken", "NY", "+14193529158"));
            service.add(new Person(counter.getAndIncrement(), "Kristy", "Washington", "+12189842448"));
            service.add(new Person(counter.getAndIncrement(), "Kacey", "Albany", "+12144238460"));
            service.add(new Person(counter.getAndIncrement(), "Karter", "Salt Lake City", "+16868312770"));
            service.add(new Person(counter.getAndIncrement(), "Kala", "Chicago", "+18608318806"));
            service.add(new Person(counter.getAndIncrement(), "Kennedy", "Utica", "+16188478148"));
            service.add(new Person(counter.getAndIncrement(), "Kamilla", "Dallas", "88005558555"));
            service.add(new Person(counter.getAndIncrement(), "Karter", "Albany", "+17026806914"));
            service.add(new Person(counter.getAndIncrement(), "Kelly", "Dallas", "+12097850636"));
            service.add(new Person(counter.getAndIncrement(), "Kaia", "NY", "+16123695886"));
            service.add(new Person(counter.getAndIncrement(), "Katherine", "Detroit", "+18038760537"));
            service.add(new Person(counter.getAndIncrement(), "Kelly", "Las Vegas", "+14847123302"));
            service.add(new Person(counter.getAndIncrement(), "Kane", "LA", "+17243148815"));
        });
        var request1 = new Thread(() -> {
            var list1 = service.findByName("Kelly");
            assertThat(list1.stream().filter(completeProfile).count()).isEqualTo(list1.size());
        });

        var request2 = new Thread(() -> {
            var list2 = service.findByAddress("Dallas");
            assertThat(list2.stream().filter(completeProfile).count()).isEqualTo(list2.size());
        });

        var request3 = new Thread(() -> {
            var list3 = service.findByName("+16123695886");
            assertThat(list3.stream().filter(completeProfile).count()).isEqualTo(list3.size());
        });

        users.start();
        users2.start();
        request1.start();
        request2.start();
        request3.start();
        try {
            users.join();
            users2.join();
            request1.join();
            request2.join();
            request3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
