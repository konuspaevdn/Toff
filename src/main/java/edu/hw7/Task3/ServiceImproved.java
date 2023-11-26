package edu.hw7.Task3;

import edu.hw7.Task3.DB.Person;
import edu.hw7.Task3.DB.PersonDatabase;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ServiceImproved implements PersonDatabase {
    private final Map<Integer, Person> db = new HashMap<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void add(Person person) {
        lock.writeLock().lock();
        db.put(person.id(), person);
        lock.writeLock().unlock();
    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();
        db.remove(id);
        lock.writeLock().unlock();
    }

    @Override
    public List<Person> findByName(String name) {
        lock.readLock().lock();
        var list = db.values().stream()
            .filter(person -> person.name().equals(name))
            .toList();
        lock.readLock().unlock();
        return list;
    }

    @Override
    public List<Person> findByAddress(String address) {
        lock.readLock().lock();
        var list = db.values().stream()
            .filter(person -> person.address().equals(address))
            .toList();
        lock.readLock().unlock();
        return list;
    }

    @Override
    public List<Person> findByPhone(String phone) {
        lock.readLock().lock();
        var list = db.values().stream()
            .filter(person -> person.address().equals(phone))
            .toList();
        lock.readLock().unlock();
        return list;
    }
}
