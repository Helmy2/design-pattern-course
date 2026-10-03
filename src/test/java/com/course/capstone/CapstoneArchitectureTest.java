package com.course.capstone;

import com.course.capstone.application.OrderFulfillmentService;
import com.course.capstone.domain.DomainValueObjects.Email;
import com.course.capstone.domain.DomainValueObjects.Money;
import com.course.capstone.domain.DomainValueObjects.OrderId;
import com.course.capstone.domain.Order;
import com.course.capstone.domain.TaxPolicy;
import com.course.capstone.domain.ports.Ports.PaymentGatewayPort;
import com.course.capstone.infrastructure.InfrastructureAdapters.EmailNotificationAdapter;
import com.course.capstone.infrastructure.InfrastructureAdapters.InMemoryOrderRepository;
import com.course.capstone.infrastructure.InfrastructureAdapters.PayPalPaymentAdapter;
import com.course.capstone.infrastructure.InfrastructureAdapters.StripePaymentAdapter;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CapstoneArchitectureTest {

    private final JavaClasses classes = new ClassFileImporter()
            .importPackages("com.course.capstone");

    @Test
    @DisplayName("ArchUnit: Core Domain must NEVER depend on Infrastructure adapters (Hexagonal Architecture)")
    void domainMustNotDependOnInfrastructure() {
        ArchRule rule = noClasses().that().resideInAPackage("..domain..")
                .should().dependOnClassesThat().resideInAPackage("..infrastructure..");

        rule.check(classes);
    }

    @Test
    @DisplayName("Verify Clean Architecture fulfills orders with Strategy Map, Value Objects, and Decoupled Ports")
    void testCleanArchitectureOrderFulfillment() {
        StripePaymentAdapter stripeAdapter = new StripePaymentAdapter();
        PayPalPaymentAdapter payPalAdapter = new PayPalPaymentAdapter();
        EmailNotificationAdapter notificationAdapter = new EmailNotificationAdapter();
        InMemoryOrderRepository repository = new InMemoryOrderRepository();
        TaxPolicy taxPolicy = new TaxPolicy();

        OrderFulfillmentService service = new OrderFulfillmentService(
                List.of(stripeAdapter, payPalAdapter),
                notificationAdapter,
                repository,
                taxPolicy
        );

        Order order = new Order(
                new OrderId("ORD-999"),
                new Email("customer@enterprise.com"),
                "US",
                new Money(50.0),
                20,       // 50 * 20 = 1000, 10% discount -> 900, US tax 8% (72) -> 972 + express 25 -> 997.0
                true,
                true
        );

        boolean fulfilled = service.fulfillOrder(order, PaymentGatewayPort.Provider.STRIPE);

        assertThat(fulfilled).isTrue();
        assertThat(repository.contains("ORD-999")).isTrue();
        assertThat(stripeAdapter.getAudit()).contains("STRIPE_API_CHARGE:99700_CENTS");
        assertThat(notificationAdapter.getSentEmails()).contains("SMTP_SEND:customer@enterprise.com:TOTAL=997.00");
    }

    @Test
    @DisplayName("Verify Domain Value Objects reject invalid data at instantiation time")
    void testDomainValueObjectValidation() {
        assertThatThrownBy(() -> new OrderId("INVALID-123"))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Email("no-at-sign"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
