package com.course.patterns.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

class SingletonConcurrencyTest {

    @Test
    @DisplayName("Demonstrate race condition: UnsafeConnectionPool creates multiple instances under concurrent load")
    void testUnsafeConnectionPoolRaceCondition() throws InterruptedException {
        UnsafeConnectionPool.reset();
        int threadCount = 50;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch readyLatch = new CountDownLatch(threadCount);
        CountDownLatch startLatch = new CountDownLatch(1);
        Set<UnsafeConnectionPool> instances = Collections.newSetFromMap(new ConcurrentHashMap<>());

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                readyLatch.countDown();
                try {
                    startLatch.await();
                    instances.add(UnsafeConnectionPool.getInstance());
                } catch (InterruptedException ignored) {}
            });
        }

        readyLatch.await();
        startLatch.countDown();
        executor.shutdown();
        executor.awaitTermination(5, java.util.concurrent.TimeUnit.SECONDS);

        // Demonstrating the flaw: race condition creates multiple distinct instances!
        assertThat(instances.size()).isGreaterThan(1);
    }

    @Test
    @DisplayName("Verify Enum Singleton GlobalAppConfig maintains exact single instance")
    void testEnumSingleton() {
        GlobalAppConfig config1 = GlobalAppConfig.INSTANCE;
        GlobalAppConfig config2 = GlobalAppConfig.INSTANCE;

        assertThat(config1).isSameAs(config2);
        assertThat(config1.getEnvironment()).isEqualTo("PRODUCTION");
    }

    @Test
    @DisplayName("Verify Kotlin object singleton eliminates creational boilerplate")
    void testKotlinObjectSingleton() {
        KotlinCacheRegistry registry = KotlinCacheRegistry.INSTANCE;
        registry.put("auth-token", "jwt-xyz");

        assertThat(registry.get("auth-token")).isEqualTo("jwt-xyz");
        assertThat(KotlinCacheRegistry.INSTANCE).isSameAs(registry);
    }
}
