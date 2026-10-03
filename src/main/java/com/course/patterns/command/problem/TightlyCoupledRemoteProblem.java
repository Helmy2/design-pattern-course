package com.course.patterns.command.problem;

import com.course.patterns.command.receivers.Receivers.Light;
import com.course.patterns.command.receivers.Receivers.Stereo;

/**
 * STARTER PROBLEM CODE:
 *
 * Demonstrates the flaws BEFORE introducing the Command pattern:
 *
 * 1. Invoker Tightly Coupled to Concrete Receivers:
 *    NaiveDirectRemote references concrete hardware devices (Light, Stereo) directly.
 *
 * 2. Violation of Open/Closed Principle (OCP):
 *    Adding a new appliance (e.g. CeilingFan, SecurityAlarm) requires altering the remote.
 *
 * 3. Inability to Support Undo, Queues, or Macro Execution:
 *    Without encapsulating a request as an object, undoing arbitrary actions or creating
 *    composite macro sequences ("Party Mode") is virtually impossible without complex
 *    device-specific conditional ladders.
 */
public class TightlyCoupledRemoteProblem {

    public static class NaiveDirectRemote {
        private final Light livingRoomLight;
        private final Stereo stereo;

        public NaiveDirectRemote(Light light, Stereo stereo) {
            this.livingRoomLight = light;
            this.stereo = stereo;
        }

        // Hardcoded button slots bound directly to concrete device APIs:
        public void pushOn(int slot) {
            if (slot == 0) {
                livingRoomLight.on();
            } else if (slot == 1) {
                stereo.on();
                stereo.setCd();
                stereo.setVolume(11);
            }
        }

        public void pushOff(int slot) {
            if (slot == 0) {
                livingRoomLight.off();
            } else if (slot == 1) {
                stereo.off();
            }
        }
    }
}
