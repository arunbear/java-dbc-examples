package org.example;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

public class BoundedStack<T> {
    private final LinkedList<T> elements = new LinkedList<>();

    private int maxSize;

    abstract class DbcAction extends DbcCommand {

        protected void checkInvariants() {
            assertThat(size())
                .as("Maximum size")
                .isLessThanOrEqualTo(maximumSize());
        }
    }

    public BoundedStack(int maxSize) {

        var cmd = new DbcAction(){
            @Override
            protected void doIt() {
                BoundedStack.this.maxSize = maxSize;
            }

            @Override
            protected void checkPreConditions() {
                assertThat(maxSize)
                    .as("Maximum size")
                    .isGreaterThan(0);
            }

            @Override
            public void checkPostConditions() {
                assertThat(isEmpty()).isTrue();
            }
        };
        cmd.run();
    }

    public void push(T item) {
        var cmd = new DbcAction(){
            @Override
            protected void doIt() {
                if(elements.size() == maxSize) {
                    elements.removeFirst();
                }
                elements.add(item);
            }

            @Override
            public void checkPostConditions() {
                assertThat(isEmpty()).isFalse();
                assertThat(top()).isEqualTo(item);
            }
        };
        cmd.run();
    }

    public void pop() {
        var cmd = new DbcAction() {
            final int oldSize = elements.size();

            @Override
            protected void doIt() {
                elements.removeLast();
            }

            @Override
            protected void checkPreConditions() {
                assertThat(size()).isGreaterThan(0);
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
                assertThat(size()).isGreaterThan(0);
            }
        };
        return q.run();
    }

    public int maximumSize() {
        return maxSize;
    }

    public int size() {
        return elements.size();
    }

    public boolean isEmpty() {
        return elements.size() == 0;
    }

}
