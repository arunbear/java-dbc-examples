package org.example;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StackTest {

    private Stack<Integer> stack;

    @BeforeMethod
    public void setUp() {
        stack = new Stack<>();
    }

    @Test
    public void testPush() {
        assertThat(stack.isEmpty()).isTrue();
        stack.push(1);
        assertThat(stack.isEmpty()).isFalse();
    }

    @Test
    public void testTop() {
        assertThat(stack.isEmpty()).isTrue();
        assertThatThrownBy(() -> stack.top()).hasMessageContaining("Must not be empty");

        stack.push(1);
        assertThat(stack.top()).isEqualTo(1);
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