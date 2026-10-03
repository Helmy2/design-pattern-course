package com.course.patterns.command.receivers;

public class Receivers {

    public static class Light {
        private boolean on = false;
        public void on() { on = true; }
        public void off() { on = false; }
        public boolean isOn() { return on; }
    }

    public static class Stereo {
        private boolean on = false;
        private int volume = 0;
        private boolean cd = false;

        public void on() { on = true; }
        public void off() { on = false; }
        public void setCd() { cd = true; }
        public void setVolume(int volume) { this.volume = volume; }

        public boolean isOn() { return on; }
        public int getVolume() { return volume; }
        public boolean isCd() { return cd; }
    }

    public static class CeilingFan {
        public static final int HIGH = 3;
        public static final int MEDIUM = 2;
        public static final int LOW = 1;
        public static final int OFF = 0;

        private int speed = OFF;

        public void high() { speed = HIGH; }
        public void medium() { speed = MEDIUM; }
        public void low() { speed = LOW; }
        public void off() { speed = OFF; }
        public int getSpeed() { return speed; }
    }
}
