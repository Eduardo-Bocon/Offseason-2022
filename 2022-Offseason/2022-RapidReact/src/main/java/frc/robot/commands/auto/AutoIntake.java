// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class AutoIntake extends CommandBase {

  private final Intake intake;
  private final Timer timer = new Timer();
  private final double time;

  public AutoIntake(Intake intake, double time) {
    this.intake = intake;
    this.time = time;
    addRequirements(intake);
  }



  @Override
  public void initialize() {
    timer.reset();
    timer.start();
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
    return timer.get() >= time;
  }
}
