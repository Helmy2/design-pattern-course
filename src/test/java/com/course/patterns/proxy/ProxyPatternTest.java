package com.course.patterns.proxy;

import com.course.patterns.proxy.springaop.SelfInvocationDemonstration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
    @DisplayName("Problem Demonstration: Unprotected access allows user to tamper with own rating and strangers to vandalize profile")
    void testUnprotectedAccessProblem() {
        Person profile = com.course.patterns.proxy.problem.UnprotectedAccessProblem
                .createVulnerableProfile("Alice", "Photography");

        // Security flaw: Alice directly inflates her own score without authorization!
        profile.setGeekRating(10);
        assertThat(profile.getGeekRating()).isEqualTo(10);

        // Security flaw: Another user overwrites Alice's personal interests!
        profile.setInterests("Hacked Profile");
        assertThat(profile.getInterests()).isEqualTo("Hacked Profile");
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
