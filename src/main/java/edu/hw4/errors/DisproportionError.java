package edu.hw4.errors;

import edu.hw4.Animal;

public class DisproportionError extends ValidationError {

    @SuppressWarnings("MagicNumber")
    public Boolean check(Animal animal) {
        if (animal.height() / animal.weight() >= 5) {
            setMsg("Disproportion in weight/height relation");
            setFields("height weight");
            return true;
        }
        return false;
    }

}
