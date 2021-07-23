package com.feng.android.designpattern.command_pattern;

import org.junit.Test;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-22 18:23
 * @tips
 */
public class Client {
    @Test
    public void main(){
        //三个角色 ： 命令角色 ，命令的接收者(执行者)，命令的发送者
        TetrisMachine tetrisMachine = new TetrisMachine();

        LeftCommand leftCommand = new LeftCommand(tetrisMachine);
        RightCommand rightCommand = new RightCommand(tetrisMachine);

        Buttons buttons = new Buttons();
        buttons.setLeftCommand(leftCommand);
        buttons.setRightCommand(rightCommand);

        buttons.toLeft();
        buttons.toRight();
        buttons.toLeft();
    }
}
