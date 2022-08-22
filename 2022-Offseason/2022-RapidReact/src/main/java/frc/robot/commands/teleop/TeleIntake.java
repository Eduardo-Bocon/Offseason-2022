// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class TeleIntake extends CommandBase {

  private final Intake intake;

  public TeleIntake(Intake intake) {
    this.intake = intake;
    addRequirements(intake);
  }



  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    intake.grabBalls(Constants.Intake.MAX_SPEED);
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
