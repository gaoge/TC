package com.feng.android.designpattern.command_pattern;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-22 18:22
 * @tips
 */
public class RightCommand implements Command{
    private TetrisMachine machine;

    public RightCommand(TetrisMachine machine) {
        this.machine = machine;
    }

    @Override
    public void execute() {
        machine.toRight();
    }
}
