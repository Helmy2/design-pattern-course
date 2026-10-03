package com.course.patterns.facade.problem;

import com.course.patterns.facade.devices.Devices.Amplifier;
import com.course.patterns.facade.devices.Devices.Popper;
import com.course.patterns.facade.devices.Devices.Projector;
import com.course.patterns.facade.devices.Devices.Screen;
import com.course.patterns.facade.devices.Devices.StreamingPlayer;
import com.course.patterns.facade.devices.Devices.TheaterLights;

/**
 * STARTER PROBLEM CODE:
 *
 * Demonstrates the flaws BEFORE introducing the Facade pattern:
 *
 * 1. Tight Subsystem Coupling:
 *    The client must know about, instantiate, and coordinate 6 separate subsystem classes.
 *
 * 2. Violation of Principle of Least Knowledge (Law of Demeter):
 *    The client talks directly to all low-level subsystem internals.
 *
 * 3. Boilerplate & High Cognitive Load:
 *    To watch a single movie, the client must execute a brittle 12-step sequence.
 */
public class ComplexSubsystemClientProblem {

    public static void watchMovieNaive(
            Amplifier amp,
            StreamingPlayer player,
            Projector projector,
            TheaterLights lights,
            Screen screen,
            Popper popper,
            String movie
    ) {
        // Brittle, tight coordination of 6 separate devices:
        popper.on();
        popper.pop();
        lights.dim(10);
        screen.down();
        projector.on();
        projector.wideScreenMode();
        amp.on();
        amp.setStreamingPlayer(player);
        amp.setSurroundSound();
        amp.setVolume(5);
        player.on();
        player.play(movie);
    }
}
