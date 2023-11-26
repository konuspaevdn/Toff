package edu.hw7.Task3;

import edu.hw7.Task3.DB.Person;
import edu.hw7.Task3.DB.PersonDatabase;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Service implements PersonDatabase {

    private final Map<Integer, Person> db = new HashMap<>();

    @Override
    public void add(Person person) {
        synchronized (db) {
            db.put(person.id(), person);
        }
    }

    @Override
    public void delete(int id) {
        synchronized (db) {
            db.remove(id);
        }
    }

    @Override
    public List<Person> findByName(String name) {
        synchronized (db) {
            return db.values().stream()
                .filter(person -> person.name().equals(name))
                .toList();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        synchronized (db) {
            return db.values().stream()
                .filter(person -> person.address().equals(address))
                .toList();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        synchronized (db) {
            return db.values().stream()
                .filter(person -> person.phoneNumber().equals(phone))
                .toList();
        }
    }
}
