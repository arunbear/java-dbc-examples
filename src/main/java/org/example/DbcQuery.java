package org.example;

public abstract class DbcQuery<T> {

    public T run() {
        checkPreConditions();
        T result = doIt();
        checkPostConditions();
        return result;
    }

    protected abstract T doIt();

    protected void checkPreConditions() { }
    protected void checkPostConditions() { }
}
