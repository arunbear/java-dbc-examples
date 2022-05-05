package org.example;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BoundedStackTest {

    @Test
    public void testConstruction() {
        assertThatThrownBy(() -> new BoundedStack<Integer>(0))
            .hasMessageContaining("Maximum size");
    }

    @Test
    public void testPush() {
        var s = new BoundedStack<Integer>(3);
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        assertThat(s.size()).isEqualTo(3);
    }
}