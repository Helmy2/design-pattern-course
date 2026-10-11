package com.course.patterns.adapter;

import com.course.patterns.adapter.hexagonal.PaymentGatewayPort;
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
    @DisplayName("Demonstrate interface incompatibility: Turkey cannot be passed directly to DuckSimulator")
    void testIncompatibleInterfaceProblem() {
        Turkey turkey = new WildTurkey();
        // A Turkey has gobble(), not quack(); cannot be passed to methods expecting Duck:
        // DuckSimulator simulator = new DuckSimulator();
        // simulator.simulate(turkey); // Compilation error! Turkey does not implement Duck.
        assertThat(turkey.gobble()).isEqualTo("Gobble gobble");
    }

    @Test
    @DisplayName("Demonstrate hexagonal port incompatibility: ThirdPartyStripeSdk does not implement PaymentGatewayPort")
    void testHexagonalIncompatibility() {
        ThirdPartyStripeSdk sdk = new ThirdPartyStripeSdk();
        // Domain requires PaymentGatewayPort:
        // PaymentGatewayPort port = sdk; // Compilation error! SDK does not implement PaymentGatewayPort.
        ThirdPartyStripeSdk.VendorChargeResponse response = sdk.createCharge("cust_123", 4999, "usd");
        assertThat(response.paid()).isTrue();
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
}
