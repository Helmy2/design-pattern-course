package com.course.patterns.adapter;

import com.course.patterns.adapter.hexagonal.PaymentGatewayPort;
import com.course.patterns.adapter.hexagonal.StripePaymentAdapter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AdapterPatternTest {

    @Test
    @DisplayName("Verify MallardDuck behaves naturally")
    void testMallardDuck() {
        Duck duck = new MallardDuck();
        assertThat(duck.quack()).isEqualTo("Quack");
        assertThat(duck.fly()).isEqualTo("I'm flying");
    }

    @Test
    @DisplayName("Verify WildTurkey behaves like a turkey")
    void testWildTurkey() {
        Turkey turkey = new WildTurkey();
        assertThat(turkey.gobble()).isEqualTo("Gobble gobble");
        assertThat(turkey.fly()).isEqualTo("I'm flying a short distance");
    }

    @Test
    @DisplayName("Demonstrate interface incompatibility: Turkey cannot be passed directly to Duck simulation")
    void testIncompatibleInterfaceProblem() {
        Turkey turkey = new WildTurkey();
        // A Turkey has gobble(), not quack(); cannot be passed to methods expecting Duck:
        // IncompatibleInterfaceProblem.runDuckSimulation(turkey); // Compilation error without Adapter!
        assertThat(turkey.gobble()).isEqualTo("Gobble gobble");
    }

    @Test
    @DisplayName("Verify EnumerationIterator adapts legacy Enumeration to Iterator")
    void testEnumerationIterator() {
        Vector<String> vector = new Vector<>();
        vector.add("Apple");
        vector.add("Banana");

        EnumerationIterator<String> iterator = new EnumerationIterator<>(vector.elements());
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo("Apple");
        assertThat(iterator.next()).isEqualTo("Banana");
        assertThat(iterator.hasNext()).isFalse();

        assertThatThrownBy(iterator::remove)
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("Verify Hexagonal Driven Adapter StripePaymentAdapter maps domain types cleanly")
    void testStripePaymentAdapter() {
        StripePaymentAdapter.ThirdPartyStripeSdk sdk = new StripePaymentAdapter.ThirdPartyStripeSdk();
        PaymentGatewayPort port = new StripePaymentAdapter(sdk);

        PaymentGatewayPort.PaymentConfirmation confirmation = port.charge("cust_123", 49.99);
        assertThat(confirmation.successful()).isTrue();
        assertThat(confirmation.amount()).isEqualTo(49.99);
        assertThat(confirmation.transactionId()).startsWith("ch_");
    }
}
