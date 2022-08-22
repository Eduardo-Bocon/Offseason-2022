// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Controllers;
import frc.robot.subsystems.Intake;

public class GrabBalls extends CommandBase {

  private final Intake intake;
  private Controllers controllers;

  public GrabBalls(Intake intake, Controllers controllers) {
    this.intake = intake;
    this.controllers = controllers;
    addRequirements(intake);
   
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute(){
    double rightTrigger = controllers.xboxController1.getRawAxis(XboxController.Axis.kRightTrigger.value);
    if(rightTrigger > 0.3){
      intake.grabBalls(rightTrigger * 0.7);
    }else if(controllers.xboxController1.getRightBumper()){
      intake.grabBalls(-0.7);
    }else{
      intake.grabBalls(0);
    }

  }


  @Override
  public void end(boolean interrupted) {
    intake.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
