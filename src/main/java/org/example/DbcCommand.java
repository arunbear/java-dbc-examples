package org.example;

public abstract class DbcCommand {

    public void run() {
        boolean contractsEnabled = ! System.getProperty("contracts.enabled", "true").equals("false");

        if(contractsEnabled) {
            checkInvariants();
            checkPreConditions();
        }
        doIt();

        if(contractsEnabled) {
            checkPostConditions();
            checkInvariants();
        }
    }

    protected abstract void doIt();

    protected void checkInvariants() { }
    protected void checkPreConditions() { }
    protected void checkPostConditions() { }
}
