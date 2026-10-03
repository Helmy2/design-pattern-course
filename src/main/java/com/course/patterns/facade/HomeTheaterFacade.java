package com.course.patterns.facade;

import com.course.patterns.facade.devices.Devices.Amplifier;
import com.course.patterns.facade.devices.Devices.Popper;
import com.course.patterns.facade.devices.Devices.Projector;
import com.course.patterns.facade.devices.Devices.Screen;
import com.course.patterns.facade.devices.Devices.StreamingPlayer;
import com.course.patterns.facade.devices.Devices.TheaterLights;

/**
 * STARTER CODE:
 * Home Theater Facade:
 * Wraps 6 distinct subsystem components via composition.
 */
public class HomeTheaterFacade {

    private final Amplifier amp;
    private final StreamingPlayer player;
    private final Projector projector;
    private final Screen screen;
    private final TheaterLights lights;
    private final Popper popper;

    public HomeTheaterFacade(
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

    // TODO: Step 1 - Implement watchMovie(String movie):
    // Coordinate popper, lights dim(10), screen down, projector widescreen, amp setup, and player playback.
    public void watchMovie(String movie) {
        // Unimplemented starter
    }

    // TODO: Step 2 - Implement endMovie():
    // Cleanly power down or reset all subsystem components.
    public void endMovie() {
        // Unimplemented starter
    }
}
