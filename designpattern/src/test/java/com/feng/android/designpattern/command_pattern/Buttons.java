package com.feng.android.designpattern.command_pattern;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-22 18:25
 * @tips
 */
public class Buttons {
    LeftCommand leftCommand;
    RightCommand rightCommand;

    public void setLeftCommand(LeftCommand leftCommand) {
        this.leftCommand = leftCommand;
    }

    public void setRightCommand(RightCommand rightCommand) {
        this.rightCommand = rightCommand;
    }

    public void toLeft(){
        leftCommand.execute();
    }

    public void toRight(){
        rightCommand.execute();
    }

}
