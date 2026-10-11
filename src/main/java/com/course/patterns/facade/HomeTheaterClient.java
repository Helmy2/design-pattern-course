package com.course.patterns.facade;

import com.course.patterns.facade.devices.Devices.Amplifier;
import com.course.patterns.facade.devices.Devices.Popper;
import com.course.patterns.facade.devices.Devices.Projector;
import com.course.patterns.facade.devices.Devices.Screen;
import com.course.patterns.facade.devices.Devices.StreamingPlayer;
import com.course.patterns.facade.devices.Devices.TheaterLights;

/**
 * STARTER ANTI-PATTERN: Direct Subsystem Orchestration
 *
 * Flaws:
 * 1. Tight Subsystem Coupling: Client directly references and instantiates 6 separate classes.
 * 2. Violates Principle of Least Knowledge (Law of Demeter): Client talks to low-level subsystem internals.
 * 3. High Cognitive Load & Brittle Code: Watching a movie requires executing a multi-step sequence with strict ordering.
 */
public class HomeTheaterClient {

    private final Amplifier amp;
    private final StreamingPlayer player;
    private final Projector projector;
    private final Screen screen;
    private final TheaterLights lights;
    private final Popper popper;

    public HomeTheaterClient(
            Amplifier amp,
            StreamingPlayer player,
            Projector projector,
            Screen screen,
            TheaterLights lights,
            Popper popper
    ) {
        this.amp = amp;
        this.player = player;
        this.projector = projector;
        this.screen = screen;
        this.lights = lights;
        this.popper = popper;
    }

    public void watchMovie(String movie) {
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
