package com.course.patterns.adapter;

import com.course.patterns.adapter.hexagonal.PaymentGatewayPort;
import com.course.patterns.adapter.hexagonal.StripePaymentAdapter;
import com.course.patterns.adapter.hexagonal.ThirdPartyStripeSdk;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AdapterPatternTest {

    @Test
    @DisplayName("Verify MallardDuck behaves naturally in DuckSimulator")
    void testMallardDuck() {
        Duck duck = new MallardDuck();
        DuckSimulator simulator = new DuckSimulator();

        assertThat(duck.quack()).isEqualTo("Quack");
        assertThat(duck.fly()).isEqualTo("I'm flying");
        assertThat(simulator.simulate(duck)).isEqualTo("Quack | I'm flying");
    }

    @Test
    @DisplayName("Verify WildTurkey behaves like a turkey")
    void testWildTurkey() {
        Turkey turkey = new WildTurkey();
        assertThat(turkey.gobble()).isEqualTo("Gobble gobble");
        assertThat(turkey.fly()).isEqualTo("I'm flying a short distance");
    }

    @Test
    @DisplayName("Verify TurkeyAdapter allows DuckSimulator to treat Turkey polymorphically as a Duck")
    void testTurkeyAdapter() {
        Turkey turkey = new WildTurkey();
        Duck adaptedTurkey = new TurkeyAdapter(turkey);
        DuckSimulator simulator = new DuckSimulator();

        assertThat(adaptedTurkey.quack()).isEqualTo("Gobble gobble");
        assertThat(adaptedTurkey.fly()).contains("I'm flying a short distance");
        assertThat(adaptedTurkey.fly().split("\n")).hasSize(5);

        String simulationResult = simulator.simulate(adaptedTurkey);
        assertThat(simulationResult).startsWith("Gobble gobble | I'm flying a short distance");
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
        ThirdPartyStripeSdk sdk = new ThirdPartyStripeSdk();
        PaymentGatewayPort port = new StripePaymentAdapter(sdk);

        PaymentGatewayPort.PaymentConfirmation confirmation = port.charge("cust_123", 49.99);
        assertThat(confirmation.successful()).isTrue();
        assertThat(confirmation.amount()).isEqualTo(49.99);
        assertThat(confirmation.transactionId()).startsWith("ch_");
    }
}
