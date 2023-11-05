package edu.hw4.errors;

import edu.hw4.Animal;

public class CatBitesError extends ValidationError {
    public Boolean check(Animal animal) {
        if (animal.type() == Animal.Type.CAT && animal.bites()) {
            setMsg("Bad kitty");
            setFields("bites");
            return true;
        }
        return false;
    }
}
