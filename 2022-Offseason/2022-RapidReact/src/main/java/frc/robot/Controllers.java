// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

/** Add your docs here. */
public class Controllers {
    // Controllers
    public final XboxController xboxController0 = new XboxController(0);
    public final XboxController xboxController1 = new XboxController(1);

    // Buttons Xbox 0
    public final JoystickButton xButton0 = new JoystickButton(xboxController0, XboxController.Button.kX.value);
    public final JoystickButton aButton0 = new JoystickButton(xboxController0, XboxController.Button.kA.value);
    public final JoystickButton bButton0 = new JoystickButton(xboxController0, XboxController.Button.kB.value);
    public final JoystickButton yButton0 = new JoystickButton(xboxController0, XboxController.Button.kY.value);
    public final JoystickButton rTrigger0 = new JoystickButton(xboxController0, XboxController.Button.kRightStick.value);
    public final JoystickButton lTrigger0 = new JoystickButton(xboxController0, XboxController.Button.kLeftStick.value);
    public final JoystickButton lBumper0 = new JoystickButton(xboxController0, XboxController.Button.kLeftBumper.value);
    public final JoystickButton rBumper0 = new JoystickButton(xboxController0, XboxController.Button.kRightBumper.value);
    public final JoystickButton startButton0 = new JoystickButton(xboxController0, 6);
    public final JoystickButton selectButton0 = new JoystickButton(xboxController0, 7);
    public final POVButton X0_pov0 = new POVButton(xboxController0, 0);
    public final POVButton X0_pov90 = new POVButton(xboxController0, 90);
    public final POVButton X0_pov180 = new POVButton(xboxController0, 180);
    public final POVButton X0_pov270 = new POVButton(xboxController0, 270);

    //Buttons Xbox 1
    public final JoystickButton xButton1 = new JoystickButton(xboxController1, XboxController.Button.kX.value);
    public final JoystickButton aButton1 = new JoystickButton(xboxController1, XboxController.Button.kA.value);
    public final JoystickButton bButton1 = new JoystickButton(xboxController1, XboxController.Button.kB.value);
    public final JoystickButton yButton1 = new JoystickButton(xboxController1, XboxController.Button.kY.value);
    public final JoystickButton rTrigger1 = new JoystickButton(xboxController1, XboxController.Button.kRightStick.value);
    public final JoystickButton lTrigger1 = new JoystickButton(xboxController1, XboxController.Button.kLeftStick.value);
    public final JoystickButton lBumper1 = new JoystickButton(xboxController1, XboxController.Button.kLeftBumper.value);
    public final JoystickButton rBumper1 = new JoystickButton(xboxController1, XboxController.Button.kRightBumper.value);
    public final JoystickButton startButton1 = new JoystickButton(xboxController1, 6);
    public final JoystickButton selectButton1 = new JoystickButton(xboxController1, 7);  
    public final POVButton X1_pov0 = new POVButton(xboxController1, 0); 
    public final POVButton X1_pov90 = new POVButton(xboxController1, 90); 
    public final POVButton X1_pov180 = new POVButton(xboxController1, 180); 
    public final POVButton X1_pov270 = new POVButton(xboxController1, 270); 


}

