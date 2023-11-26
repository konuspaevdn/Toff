package edu.hw4.errors;

import edu.hw4.Animal;

public class ValidationError {
    protected String msg;
    protected String fields;

    public String getMsg() {
        return msg;
    }

    protected void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFields() {
        return fields;
    }

    protected void setFields(String fields) {
        this.fields = fields;
    }

    public Boolean check(Animal animal) {
        return false;
    }
}


