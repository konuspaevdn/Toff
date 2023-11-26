package edu.hw4.errors;

import edu.hw4.Animal;

public class CatBitesError extends ValidationError {

    public CatBitesError() {
        msg = "Bad kitty";
        fields = "bites";
    }

    public Boolean check(Animal animal) {
        return animal.type() == Animal.Type.CAT && animal.bites();
    }
}
