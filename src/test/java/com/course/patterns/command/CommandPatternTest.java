package com.course.patterns.command;

import com.course.patterns.command.receivers.Receivers.Light;
import com.course.patterns.command.receivers.Receivers.Stereo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CommandPatternTest {

    @Test
    @DisplayName("Verify direct control of Light and Stereo devices")
    void testDirectDeviceControl() {
        Light light = new Light();
        Stereo stereo = new Stereo();

        RemoteControl remote = new RemoteControl(light, stereo);

        remote.onButtonWasPushed(0);
        assertThat(light.isOn()).isTrue();

        remote.offButtonWasPushed(0);
        assertThat(light.isOn()).isFalse();

        remote.onButtonWasPushed(1);
        assertThat(stereo.isOn()).isTrue();
        assertThat(stereo.getVolume()).isEqualTo(11);
    }

    @Test
    @DisplayName("Problem Demonstration: RemoteControl is rigidly coupled to Light and Stereo, cannot add new devices or undo")
    void testDirectRemoteCouplingProblem() {
        Light light = new Light();
        Stereo stereo = new Stereo();

        RemoteControl remote = new RemoteControl(light, stereo);
        remote.onButtonWasPushed(0);
        assertThat(light.isOn()).isTrue();

        // Flaw 1: Adding a third device (e.g. CeilingFan) requires modifying RemoteControl source code!
        // Flaw 2: RemoteControl has no way to track or undo previous commands!
    }
}
