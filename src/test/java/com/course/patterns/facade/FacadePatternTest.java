package com.course.patterns.facade;

import com.course.patterns.facade.devices.Devices.Amplifier;
import com.course.patterns.facade.devices.Devices.Popper;
import com.course.patterns.facade.devices.Devices.Projector;
import com.course.patterns.facade.devices.Devices.Screen;
import com.course.patterns.facade.devices.Devices.StreamingPlayer;
import com.course.patterns.facade.devices.Devices.TheaterLights;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FacadePatternTest {

    @Test
    @DisplayName("Verify individual subsystem devices can be tested directly (Non-exclusivity of Facade)")
    void testSubsystemDirectAccess() {
        Popper popper = new Popper();
        popper.on();
        assertThat(popper.isOn()).isTrue();
        assertThat(popper.pop()).isEqualTo("Popcorn Popper popping popcorn!");

        Projector projector = new Projector();
        projector.on();
        projector.wideScreenMode();
        assertThat(projector.getMode()).isEqualTo("16x9");
    }

    @Test
    @DisplayName("Verify HomeTheaterFacade coordinates entire subsystem on watchMovie and endMovie")
    void testHomeTheaterFacadeExecution() {
        Amplifier amp = new Amplifier();
        StreamingPlayer player = new StreamingPlayer();
        Projector projector = new Projector();
        Screen screen = new Screen();
        TheaterLights lights = new TheaterLights();
        Popper popper = new Popper();

        HomeTheaterFacade facade = new HomeTheaterFacade(amp, player, projector, screen, lights, popper);

        // 1. Trigger complex startup workflow via facade
        facade.watchMovie("Inception");

        assertThat(popper.isOn()).isTrue();
        assertThat(lights.getDimLevel()).isEqualTo(10);
        assertThat(screen.isDown()).isTrue();
        assertThat(projector.isOn()).isTrue();
        assertThat(projector.getMode()).isEqualTo("16x9");
        assertThat(amp.isOn()).isTrue();
        assertThat(amp.hasSurroundSound()).isTrue();
        assertThat(amp.getVolume()).isEqualTo(5);
        assertThat(player.isOn()).isTrue();
        assertThat(player.getCurrentMovie()).isEqualTo("Inception");

        // 2. Trigger teardown workflow via facade
        facade.endMovie();

        assertThat(popper.isOn()).isFalse();
        assertThat(lights.getDimLevel()).isEqualTo(100);
        assertThat(screen.isDown()).isFalse();
        assertThat(projector.isOn()).isFalse();
        assertThat(amp.isOn()).isFalse();
        assertThat(player.isOn()).isFalse();
        assertThat(player.getCurrentMovie()).isNull();
    }

    @Test
    @DisplayName("Verify decoupled HomeTheaterClient interacts strictly through HomeTheaterFacade")
    void testDecoupledHomeTheaterClient() {
        Amplifier amp = new Amplifier();
        StreamingPlayer player = new StreamingPlayer();
        Projector projector = new Projector();
        Screen screen = new Screen();
        TheaterLights lights = new TheaterLights();
        Popper popper = new Popper();

        HomeTheaterFacade facade = new HomeTheaterFacade(amp, player, projector, screen, lights, popper);
        HomeTheaterClient client = new HomeTheaterClient(facade);

        client.watchMovie("Interstellar");
        assertThat(player.getCurrentMovie()).isEqualTo("Interstellar");
        assertThat(amp.isOn()).isTrue();

        client.endMovie();
        assertThat(player.isOn()).isFalse();
    }
}
