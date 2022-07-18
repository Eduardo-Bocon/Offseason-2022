// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveStraight extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveTrain driveTrain;
  private final Intake intake = new Intake();
  private final Timer timer = new Timer();
  private final double time;
  private double speed;

  /**
   * Creates a new PIDDriveStraight.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveStraight(DriveTrain dt, double speed, double time) {
    driveTrain = dt;
    this.time = time;
    this.speed = speed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveTrain.arcadeDrive(speed, 0);
  //   if (speed > 0){ 
  //  intake.grabBalls(Constants.Intake.MAX_SPEED);
  //   }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.arcadeDrive(0, 0);
    intake.grabBalls(0);
    timer.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.get() >= time;
}
}

