package com.course.patterns.command;

import com.course.patterns.command.receivers.Receivers.Light;
import com.course.patterns.command.receivers.Receivers.Stereo;

/**
 * STARTER ANTI-PATTERN: Tightly Coupled Direct Remote Control
 *
 * Flaws:
 * 1. Invoker Tightly Coupled to Concrete Receivers: References concrete Light and Stereo directly.
 * 2. Violation of Open/Closed Principle: Adding a new appliance (e.g. CeilingFan) requires modifying this remote.
 * 3. Inability to Support Undo or Macro Commands: Commands are not encapsulated as first-class objects.
 */
public class RemoteControl {

    private final Light livingRoomLight;
    private final Stereo stereo;

    public RemoteControl(Light livingRoomLight, Stereo stereo) {
        this.livingRoomLight = livingRoomLight;
        this.stereo = stereo;
    }

    public void onButtonWasPushed(int slot) {
        if (slot == 0) {
            livingRoomLight.on();
        } else if (slot == 1) {
            stereo.on();
            stereo.setCd();
            stereo.setVolume(11);
        }
    }

    public void offButtonWasPushed(int slot) {
        if (slot == 0) {
            livingRoomLight.off();
        } else if (slot == 1) {
            stereo.off();
        }
    }
}
