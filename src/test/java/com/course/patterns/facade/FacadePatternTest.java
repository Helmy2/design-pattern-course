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
    @DisplayName("Verify individual subsystem devices can be tested directly")
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
    @DisplayName("Problem Demonstration: Client burdened by tightly coupling to 6 separate subsystem components")
    void testComplexSubsystemProblem() {
        Amplifier amp = new Amplifier();
        StreamingPlayer player = new StreamingPlayer();
        Projector projector = new Projector();
        TheaterLights lights = new TheaterLights();
        Screen screen = new Screen();
        Popper popper = new Popper();

        HomeTheaterClient client = new HomeTheaterClient(amp, player, projector, screen, lights, popper);
        client.watchMovie("Inception");

        assertThat(popper.isOn()).isTrue();
        assertThat(lights.getDimLevel()).isEqualTo(10);
        assertThat(screen.isDown()).isTrue();
        assertThat(projector.isOn()).isTrue();
        assertThat(amp.isOn()).isTrue();
        assertThat(player.getCurrentMovie()).isEqualTo("Inception");
    }
}
