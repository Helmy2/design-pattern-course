package com.course.patterns.proxy;

import com.course.patterns.proxy.springaop.SelfInvocationDemonstration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProxyPatternTest {

    @Test
    @DisplayName("Verify real PersonImpl stores state directly without proxy")
    void testRealPersonDirect() {
        Person person = new PersonImpl();
        person.setName("Linus Torvalds");
        person.setInterests("Linux, Git");
        person.setGeekRating(10);

        assertThat(person.getName()).isEqualTo("Linus Torvalds");
        assertThat(person.getGeekRating()).isEqualTo(10);
    }

    @Test
    @DisplayName("Verify Owner Dynamic Proxy allows profile edits but blocks modifying own rating")
    void testOwnerProxyPermissions() {
        Person joe = new PersonImpl();
        joe.setName("Joe Geek");
        joe.setInterests("Cars, Computers");

        Person ownerProxy = PersonProxyFactory.getOwnerProxy(joe);

        // Owner can read
        assertThat(ownerProxy.getName()).isEqualTo("Joe Geek");

        // Owner can update personal interests
        ownerProxy.setInterests("Cars, Computers, Kotlin");
        assertThat(ownerProxy.getInterests()).isEqualTo("Cars, Computers, Kotlin");

        // Owner CANNOT rate themselves! InvocationHandler throws IllegalAccessException
        assertThatThrownBy(() -> ownerProxy.setGeekRating(10))
                .hasRootCauseInstanceOf(IllegalAccessException.class)
                .hasRootCauseMessage("Owners cannot modify their own rating score!");
    }

    @Test
    @DisplayName("Verify Non-Owner Dynamic Proxy allows rating but blocks modifying another user's profile")
    void testNonOwnerProxyPermissions() {
        Person joe = new PersonImpl();
        joe.setName("Joe Geek");
        joe.setInterests("Cars, Computers");

        Person nonOwnerProxy = PersonProxyFactory.getNonOwnerProxy(joe);

        // Outside user can read
        assertThat(nonOwnerProxy.getName()).isEqualTo("Joe Geek");

        // Outside user CAN rate profile
        nonOwnerProxy.setGeekRating(8);
        assertThat(nonOwnerProxy.getGeekRating()).isEqualTo(8);

        // Outside user CANNOT change personal interests!
        assertThatThrownBy(() -> nonOwnerProxy.setInterests("Hacked Interests"))
                .hasRootCauseInstanceOf(IllegalAccessException.class)
                .hasRootCauseMessage("You cannot alter another user's personal profile!");
    }

    @Test
    @DisplayName("Demonstrate Spring Proxy self-invocation trap: direct call bypasses secondary interceptor")
    void testSpringSelfInvocationTrap() {
        SelfInvocationDemonstration.RawOrderService raw = new SelfInvocationDemonstration.RawOrderService();
        SelfInvocationDemonstration.AuditingProxy proxy = new SelfInvocationDemonstration.AuditingProxy(raw);

        // When external client calls processOrder through the proxy:
        proxy.processOrder("ORD-101");

        // processOrder was intercepted by the proxy!
        assertThat(proxy.getProxyInterceptions())
                .contains("INTERCEPT_TRANSACTION_START:processOrder", "INTERCEPT_TRANSACTION_COMMIT:processOrder");

        // BUT because processOrder internally called saveWithAudit on 'this',
        // the secondary proxy interceptor "INTERCEPT_AUDIT_TRANSACTION:saveWithAudit" was NEVER triggered!
        assertThat(proxy.getProxyInterceptions())
                .doesNotContain("INTERCEPT_AUDIT_TRANSACTION:saveWithAudit");
    }
}
