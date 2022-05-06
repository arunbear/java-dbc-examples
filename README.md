# Design by Contract in Java

This project shows a simplistic approach for using Design by Contract in Java

For some other options see these [blog](https://www.leadingagile.com/2018/05/design-by-contract-part-two/) [posts](https://blog.frankel.ch/programming-by-contract-jvm/).

First an example class that we want to add contracts to.

Here is how this example - a stack, is used:

```java
public class StackTest {

    private Stack<Integer> stack;

    @BeforeMethod
    public void setUp() {
        stack = new Stack<>();
    }

    @Test
    public void testPop() {
        assertThatThrownBy(() -> stack.pop()).hasMessageContaining("Must not be empty");

        stack.push(1);
        assertThat(stack.top()).isEqualTo(1);

        stack.pop();
        assertThat(stack.size()).isEqualTo(0);
    }
}
```

The stack class is:

```java
public class Stack<T> {
    private final LinkedList<T> elements = new LinkedList<>();

    public void push(T item) {
        elements.add(item);
    }

    public void pop() {
        elements.removeLast();
    }

    public T top() {
        return elements.getLast();
    }

    public int size() {
        return elements.size();
    }

    public boolean isEmpty() {
        return elements.size() == 0;
    }
}
```

Our test currently fails with

```
Expecting throwable message:
  null
to contain:
  "Must not be empty"
but did not.

Throwable that failed the check:

java.util.NoSuchElementException
    at java.base/java.util.LinkedList.removeLast(LinkedList.java:287)
    at org.example.Stack.pop(Stack.java:13)
    at org.example.StackTest.lambda$testPop$1(StackTest.java:36)
    ...
```

As this is an invalid operation on the stack, we want an exception to be thrown from the stack class itself.