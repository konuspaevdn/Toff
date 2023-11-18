package edu.hw4.errors;

import edu.hw4.Animal;

public class DisproportionError extends ValidationError {

    public DisproportionError() {
        msg = "Disproportion in weight/height relation";
        fields = "height weight";
    }

    @SuppressWarnings("MagicNumber")
    public Boolean check(Animal animal) {
        return animal.height() / animal.weight() >= 5;
    }

}
