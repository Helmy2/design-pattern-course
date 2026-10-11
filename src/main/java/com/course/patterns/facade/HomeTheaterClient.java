package com.course.patterns.facade;

/**
 * CLEAN CODE SOLUTION:
 * Decoupled Client:
 * Now depends solely on the HomeTheaterFacade rather than 6 disparate subsystem devices.
 * Adheres strictly to the Principle of Least Knowledge (Law of Demeter).
 */
public class HomeTheaterClient {

    private final HomeTheaterFacade facade;

    public HomeTheaterClient(HomeTheaterFacade facade) {
        this.facade = facade;
    }

    public void watchMovie(String movie) {
        facade.watchMovie(movie);
    }

    public void endMovie() {
        facade.endMovie();
    }
}
