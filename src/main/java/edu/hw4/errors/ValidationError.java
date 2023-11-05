package edu.hw4.errors;

import edu.hw4.Animal;

public abstract class ValidationError {
    String msg = "";
    String fields = "";

    protected void setMsg(String str) {
        msg = str;
    }

    protected void setFields(String str) {
        fields = str;
    }

    public String getFields() {
        return fields;
    }

    public abstract Boolean check(Animal animal);

}


