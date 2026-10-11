package com.course.patterns.command.commands;

import com.course.patterns.command.Command;
import com.course.patterns.command.receivers.Receivers.CeilingFan;
import com.course.patterns.command.receivers.Receivers.Light;
import com.course.patterns.command.receivers.Receivers.Stereo;

public class ConcreteCommands {

    public static class LightOnCommand implements Command {
        private final Light light;

        public LightOnCommand(Light light) {
            this.light = light;
        }

        @Override public void execute() { light.on(); }
        @Override public void undo() { light.off(); }
    }

    public static class LightOffCommand implements Command {
        private final Light light;

        public LightOffCommand(Light light) {
            this.light = light;
        }

        @Override public void execute() { light.off(); }
        @Override public void undo() { light.on(); }
    }

    public static class StereoOnWithCDCommand implements Command {
        private final Stereo stereo;

        public StereoOnWithCDCommand(Stereo stereo) {
            this.stereo = stereo;
        }

        @Override
        public void execute() {
            stereo.on();
            stereo.setCd();
            stereo.setVolume(11);
        }

        @Override
        public void undo() {
            stereo.off();
        }
    }

    public static class StereoOffCommand implements Command {
        private final Stereo stereo;

        public StereoOffCommand(Stereo stereo) {
            this.stereo = stereo;
        }

        @Override public void execute() { stereo.off(); }
        @Override public void undo() {
            stereo.on();
            stereo.setCd();
            stereo.setVolume(11);
        }
    }

    public static class CeilingFanHighCommand implements Command {
        private final CeilingFan fan;
        private int prevSpeed;

        public CeilingFanHighCommand(CeilingFan fan) {
            this.fan = fan;
        }

        @Override
        public void execute() {
            prevSpeed = fan.getSpeed();
            fan.high();
        }

        @Override
        public void undo() {
            restorePreviousSpeed();
        }

        private void restorePreviousSpeed() {
            if (prevSpeed == CeilingFan.HIGH) fan.high();
            else if (prevSpeed == CeilingFan.MEDIUM) fan.medium();
            else if (prevSpeed == CeilingFan.LOW) fan.low();
            else if (prevSpeed == CeilingFan.OFF) fan.off();
        }
    }

    public static class CeilingFanMediumCommand implements Command {
        private final CeilingFan fan;
        private int prevSpeed;

        public CeilingFanMediumCommand(CeilingFan fan) {
            this.fan = fan;
        }

        @Override
        public void execute() {
            prevSpeed = fan.getSpeed();
            fan.medium();
        }

        @Override
        public void undo() {
            restorePreviousSpeed();
        }

        private void restorePreviousSpeed() {
            if (prevSpeed == CeilingFan.HIGH) fan.high();
            else if (prevSpeed == CeilingFan.MEDIUM) fan.medium();
            else if (prevSpeed == CeilingFan.LOW) fan.low();
            else if (prevSpeed == CeilingFan.OFF) fan.off();
        }
    }

    public static class CeilingFanOffCommand implements Command {
        private final CeilingFan fan;
        private int prevSpeed;

        public CeilingFanOffCommand(CeilingFan fan) {
            this.fan = fan;
        }

        @Override
        public void execute() {
            prevSpeed = fan.getSpeed();
            fan.off();
        }

        @Override
        public void undo() {
            if (prevSpeed == CeilingFan.HIGH) fan.high();
            else if (prevSpeed == CeilingFan.MEDIUM) fan.medium();
            else if (prevSpeed == CeilingFan.LOW) fan.low();
            else if (prevSpeed == CeilingFan.OFF) fan.off();
        }
    }

    public static class MacroCommand implements Command {
        private final Command[] commands;

        public MacroCommand(Command[] commands) {
            this.commands = commands;
        }

        @Override
        public void execute() {
            for (Command cmd : commands) {
                cmd.execute();
            }
        }

        @Override
        public void undo() {
            // Unroll in reverse order
            for (int i = commands.length - 1; i >= 0; i--) {
                commands[i].undo();
            }
        }
    }
}
