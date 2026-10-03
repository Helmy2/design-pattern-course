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
    @DisplayName("Problem Demonstration: Client burdened by orchestrating 6 separate subsystem components")
    void testComplexSubsystemProblem() {
        var amp = new Amplifier();
        var player = new StreamingPlayer();
        var projector = new Projector();
        var lights = new TheaterLights();
        var screen = new Screen();
        var popper = new Popper();

        com.course.patterns.facade.problem.ComplexSubsystemClientProblem
                .watchMovieNaive(amp, player, projector, lights, screen, popper, "Inception");

        assertThat(popper.isOn()).isTrue();
        assertThat(lights.getDimLevel()).isEqualTo(10);
        assertThat(screen.isDown()).isTrue();
        assertThat(projector.isOn()).isTrue();
        assertThat(amp.isOn()).isTrue();
        assertThat(player.getCurrentMovie()).isEqualTo("Inception");
    }
}
