// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class ExtendClimber extends CommandBase {
  /** Creates a new ExtendClimber. */
  private Climber climber;
  private double speed;

  public ExtendClimber(Climber climber, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.climber = climber;
    this.speed = speed;
    addRequirements(climber);
  }

  @Override
  public void initialize() {  }

  @Override
  public void execute() {
    climber.extendClimber(speed);
  }


  @Override
  public void end(boolean interrupted) {
    climber.setClimberSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
