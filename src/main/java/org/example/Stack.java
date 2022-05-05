package org.example;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

public class Stack<T> {
    private final LinkedList<T> elements = new LinkedList<>();

    public void push(T item) {
        var cmd = new DbcCommand(){
            final int oldSize = elements.size();

            @Override
            protected void doIt() {
                elements.add(item);
            }

            @Override
            public void checkPostConditions() {
                assertThat(size())
                    .as("Size has increased")
                    .isEqualTo(oldSize + 1);

                assertThat(top()).isEqualTo(item);
            }
        };
        cmd.run();
    }

    public void pop() {
        var cmd = new DbcCommand() {
            final int oldSize = elements.size();

            @Override
            protected void doIt() {
                elements.removeLast();
            }

            @Override
            protected void checkPreConditions() {
                assertThat(size())
                    .as("Must not be empty")
                    .isGreaterThan(0);
            }

            @Override
            protected void checkPostConditions() {
                assertThat(size()).as("Size has decreased").isEqualTo(oldSize - 1);
            }
        };
        cmd.run();
    }

    public T top() {
        var q = new DbcQuery<T>() {
            @Override
            protected T doIt() {
                return elements.getLast();
            }

            @Override
            protected void checkPreConditions() {
                assertThat(size())
                    .as("Must not be empty")
                    .isGreaterThan(0);
            }
        };
        return q.run();
    }

    public int size() {
        return elements.size();
    }

    public boolean isEmpty() {
        return elements.size() == 0;
    }

}
