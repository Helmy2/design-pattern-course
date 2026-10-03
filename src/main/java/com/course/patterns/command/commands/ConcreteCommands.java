package com.course.patterns.command.commands;

import com.course.patterns.command.Command;
import com.course.patterns.command.receivers.Receivers.CeilingFan;
import com.course.patterns.command.receivers.Receivers.Light;
import com.course.patterns.command.receivers.Receivers.Stereo;

public class ConcreteCommands {

    public static class LightOnCommand implements Command {
        private final Light light;
        public LightOnCommand(Light light) { this.light = light; }
        @Override public void execute() { light.on(); }
        @Override public void undo() { light.off(); }
    }

    public static class LightOffCommand implements Command {
        private final Light light;
        public LightOffCommand(Light light) { this.light = light; }
        @Override public void execute() { light.off(); }
        @Override public void undo() { light.on(); }
    }

    public static class StereoOnWithCDCommand implements Command {
        private final Stereo stereo;
        public StereoOnWithCDCommand(Stereo stereo) { this.stereo = stereo; }
        @Override
        public void execute() {
            stereo.on();
            stereo.setCd();
            stereo.setVolume(11);
        }
        @Override public void undo() { stereo.off(); }
    }

    /**
     * STARTER CODE:
     * State-tracking undo for Ceiling Fan.
     */
    public static class CeilingFanMediumCommand implements Command {
        private final CeilingFan ceilingFan;
        private int prevSpeed;

        public CeilingFanMediumCommand(CeilingFan ceilingFan) {
            this.ceilingFan = ceilingFan;
        }

        @Override
        public void execute() {
            // TODO: Step 1 - Record prevSpeed before changing state
            prevSpeed = ceilingFan.getSpeed();
            ceilingFan.medium();
        }

        @Override
        public void undo() {
            // TODO: Step 2 - Revert to recorded previous state
            switch (prevSpeed) {
                case CeilingFan.HIGH -> ceilingFan.high();
                case CeilingFan.MEDIUM -> ceilingFan.medium();
                case CeilingFan.LOW -> ceilingFan.low();
                case CeilingFan.OFF -> ceilingFan.off();
            }
        }
    }

    /**
     * STARTER CODE:
     * Macro Command: Executes array of commands, rolls back in reverse.
     */
    public static class MacroCommand implements Command {
        private final Command[] commands;

        public MacroCommand(Command[] commands) {
            this.commands = commands;
        }

        @Override
        public void execute() {
            // TODO: Step 3 - Iterate forward
            for (Command cmd : commands) {
                cmd.execute();
            }
        }

        @Override
        public void undo() {
            // TODO: Step 4 - Iterate in reverse order
            for (int i = commands.length - 1; i >= 0; i--) {
                commands[i].undo();
            }
        }
    }
}
