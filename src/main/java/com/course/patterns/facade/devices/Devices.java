package com.course.patterns.facade.devices;

import java.util.ArrayList;
import java.util.List;

public class Devices {

    public static class Popper {
        private boolean on = false;
        public void on() { on = true; }
        public void off() { on = false; }
        public String pop() { return "Popcorn Popper popping popcorn!"; }
        public boolean isOn() { return on; }
    }

    public static class TheaterLights {
        private int dimLevel = 100;
        public void dim(int level) { this.dimLevel = level; }
        public void on() { this.dimLevel = 100; }
        public int getDimLevel() { return dimLevel; }
    }

    public static class Screen {
        private boolean down = false;
        public void down() { down = true; }
        public void up() { down = false; }
        public boolean isDown() { return down; }
    }

    public static class Projector {
        private boolean on = false;
        private String mode = "4x3";
        public void on() { on = true; }
        public void off() { on = false; }
        public void wideScreenMode() { mode = "16x9"; }
        public boolean isOn() { return on; }
        public String getMode() { return mode; }
    }

    public static class StreamingPlayer {
        private boolean on = false;
        private String currentMovie = null;
        public void on() { on = true; }
        public void off() { on = false; }
        public void play(String movie) { this.currentMovie = movie; }
        public void stop() { this.currentMovie = null; }
        public boolean isOn() { return on; }
        public String getCurrentMovie() { return currentMovie; }
    }

    public static class Amplifier {
        private boolean on = false;
        private StreamingPlayer player;
        private boolean surroundSound = false;
        private int volume = 0;

        public void on() { on = true; }
        public void off() { on = false; }
        public void setStreamingPlayer(StreamingPlayer player) { this.player = player; }
        public void setSurroundSound() { this.surroundSound = true; }
        public void setVolume(int level) { this.volume = level; }

        public boolean isOn() { return on; }
        public boolean hasSurroundSound() { return surroundSound; }
        public int getVolume() { return volume; }
    }
}
