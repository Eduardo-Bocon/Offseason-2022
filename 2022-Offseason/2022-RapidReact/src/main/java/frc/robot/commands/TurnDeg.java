// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class TurnDeg extends CommandBase {

  private final DriveTrain driveTrain;
  private final double startingAngle;
  private final double angle;
  private final double speed;

  public TurnDeg(DriveTrain driveTrain, double angle, double speed) {
    this.driveTrain = driveTrain;
    this.startingAngle = driveTrain.getGyro().getAngle();
    this.angle = angle;
    this.speed = speed;
    addRequirements(driveTrain);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    driveTrain.arcadeDrive(0.0, speed);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    if(angle >= 0){
    return driveTrain.getGyro().getAngle()-startingAngle >= angle;
    } else{
      return driveTrain.getGyro().getAngle()-startingAngle <= angle;
    }
  }
}
