package com.course.patterns.command;

import com.course.patterns.command.commands.ConcreteCommands;
import com.course.patterns.command.receivers.Receivers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CommandPatternTest {

    @Test
    @DisplayName("Verify RemoteControl executes Light on/off and undo")
    void testLightCommands() {
        RemoteControl remote = new RemoteControl();
        Receivers.Light light = new Receivers.Light();

        remote.setCommand(0, new ConcreteCommands.LightOnCommand(light), new ConcreteCommands.LightOffCommand(light));

        remote.onButtonWasPushed(0);
        assertThat(light.isOn()).isTrue();

        remote.offButtonWasPushed(0);
        assertThat(light.isOn()).isFalse();

        // Undo off -> turns back on
        remote.undoButtonWasPushed();
        assertThat(light.isOn()).isTrue();
    }

    @Test
    @DisplayName("Problem Demonstration: Naive remote couples directly to hardware devices without undo support")
    void testTightlyCoupledRemoteProblem() {
        var light = new Receivers.Light();
        var stereo = new Receivers.Stereo();
        var naiveRemote = new com.course.patterns.command.problem.TightlyCoupledRemoteProblem.NaiveDirectRemote(light, stereo);

        naiveRemote.pushOn(0);
        assertThat(light.isOn()).isTrue();

        naiveRemote.pushOn(1);
        assertThat(stereo.isOn()).isTrue();
        assertThat(stereo.getVolume()).isEqualTo(11);
    }

    @Test
    @DisplayName("Verify CeilingFan state-tracking undo reverts to previous speed")
    void testCeilingFanUndo() {
        Receivers.CeilingFan fan = new Receivers.CeilingFan();
        fan.high(); // Start at HIGH (3)

        Command mediumCommand = new ConcreteCommands.CeilingFanMediumCommand(fan);
        mediumCommand.execute();
        assertThat(fan.getSpeed()).isEqualTo(Receivers.CeilingFan.MEDIUM);

        mediumCommand.undo();
        assertThat(fan.getSpeed()).isEqualTo(Receivers.CeilingFan.HIGH); // Reverted to HIGH!
    }

    @Test
    @DisplayName("Verify MacroCommand Party Mode executes sequence and unrolls in reverse on undo")
    void testMacroCommand() {
        Receivers.Light light = new Receivers.Light();
        Receivers.Stereo stereo = new Receivers.Stereo();

        Command[] partyOn = { new ConcreteCommands.LightOnCommand(light), new ConcreteCommands.StereoOnWithCDCommand(stereo) };
        ConcreteCommands.MacroCommand partyMacro = new ConcreteCommands.MacroCommand(partyOn);

        partyMacro.execute();
        assertThat(light.isOn()).isTrue();
        assertThat(stereo.isOn()).isTrue();
        assertThat(stereo.getVolume()).isEqualTo(11);

        partyMacro.undo();
        assertThat(light.isOn()).isFalse();
        assertThat(stereo.isOn()).isFalse();
    }
}
