package org.example;

public abstract class DbcInit {

    public void run() {
        checkPreConditions();
        doCmd();
        checkPostConditions();
        checkInvariants();
    }

    protected abstract void doCmd();

    protected void checkInvariants() { }
    protected void checkPreConditions() { }
    protected void checkPostConditions() { }
}
