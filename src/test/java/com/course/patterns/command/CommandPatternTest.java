package com.course.patterns.command;

import com.course.patterns.command.commands.ConcreteCommands.*;
import com.course.patterns.command.receivers.Receivers.CeilingFan;
import com.course.patterns.command.receivers.Receivers.Light;
import com.course.patterns.command.receivers.Receivers.Stereo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CommandPatternTest {

    @Test
    @DisplayName("Verify RemoteControl executes and undoes single Light commands cleanly")
    void testLightCommandsWithUndo() {
        RemoteControl remote = new RemoteControl();
        Light light = new Light();

        remote.setCommand(0, new LightOnCommand(light), new LightOffCommand(light));

        remote.onButtonWasPushed(0);
        assertThat(light.isOn()).isTrue();

        remote.offButtonWasPushed(0);
        assertThat(light.isOn()).isFalse();

        // Undo off -> turns back on
        remote.undoButtonWasPushed();
        assertThat(light.isOn()).isTrue();
    }

    @Test
    @DisplayName("Verify state-tracking Undo on CeilingFan accurately restores previous speed")
    void testCeilingFanStateUndo() {
        RemoteControl remote = new RemoteControl();
        CeilingFan fan = new CeilingFan();

        remote.setCommand(0, new CeilingFanMediumCommand(fan), new CeilingFanOffCommand(fan));
        remote.setCommand(1, new CeilingFanHighCommand(fan), new CeilingFanOffCommand(fan));

        // Start off, turn medium
        remote.onButtonWasPushed(0);
        assertThat(fan.getSpeed()).isEqualTo(CeilingFan.MEDIUM);

        // Turn high
        remote.onButtonWasPushed(1);
        assertThat(fan.getSpeed()).isEqualTo(CeilingFan.HIGH);

        // Undo high -> should accurately restore previous speed (MEDIUM)!
        remote.undoButtonWasPushed();
        assertThat(fan.getSpeed()).isEqualTo(CeilingFan.MEDIUM);
    }

    @Test
    @DisplayName("Verify MacroCommand (Party Mode) executes all actions and undoes them in reverse order")
    void testPartyModeMacroCommand() {
        RemoteControl remote = new RemoteControl();
        Light light = new Light();
        Stereo stereo = new Stereo();

        Command[] partyOn = { new LightOnCommand(light), new StereoOnWithCDCommand(stereo) };
        Command[] partyOff = { new LightOffCommand(light), new StereoOffCommand(stereo) };

        MacroCommand partyOnMacro = new MacroCommand(partyOn);
        MacroCommand partyOffMacro = new MacroCommand(partyOff);

        remote.setCommand(0, partyOnMacro, partyOffMacro);

        remote.onButtonWasPushed(0);
        assertThat(light.isOn()).isTrue();
        assertThat(stereo.isOn()).isTrue();
        assertThat(stereo.getVolume()).isEqualTo(11);

        // Undo Party Mode -> should reverse both
        remote.undoButtonWasPushed();
        assertThat(light.isOn()).isFalse();
        assertThat(stereo.isOn()).isFalse();
    }
}
