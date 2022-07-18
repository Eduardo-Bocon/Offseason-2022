// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.Climber;
import frc.robot.commands.ActivateStorage;
import frc.robot.commands.DriveRobot;
import frc.robot.commands.ExtendClimber;
import frc.robot.commands.GrabBalls;
import frc.robot.commands.InvertDriveTrain;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.StorageSystem;
import frc.robot.commands.commandgroups.AutoRoutine;
import frc.robot.commands.RetractClimber;
import frc.robot.commands.Shoot;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class RobotContainer {

  //subsystems
  private final frc.robot.subsystems.DriveTrain driveTrain = new DriveTrain();
  private final Climber climber = new Climber();
  private final Shooter shooter = new Shooter();
  private final StorageSystem storageSystem = new StorageSystem();
  private final frc.robot.subsystems.Intake intake = new Intake();
  private final Controllers controllers = new Controllers();
  // private POVButton povButton = new POVButton(controllers.xboxController, -1);

  public RobotContainer() {
    driveTrain.setInverted(true);
    driveTrain.setDefaultCommand(new DriveRobot(driveTrain, controllers.xboxController0));
    intake.setDefaultCommand(new GrabBalls(intake, controllers));
    shooter.setDefaultCommand(new Shoot(shooter, controllers));
    storageSystem.setDefaultCommand(new ActivateStorage(storageSystem, controllers.xboxController1));
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    controllers.bButton0.toggleWhenPressed(new DriveRobot(driveTrain, controllers.xboxController0));
    //.bButton0.whenActive(new InvertDriveTrain(driveTrain));
    //yButton.toggleWhenPressed(new IntakeAndConveyor(intake, storageSystem));
	  //controllers.xButton.toggleWhenPressed(new Shoot(shooter, Constants.Shooter.SPEED));
    // controllers.yButton.whenHeld(new IntakeAndConveyor(intake, storageSystem, 0.7));s
    // controllers.aButton.whenHeld(new IntakeAndConveyor(intake, storageSystem, -0.7));
    controllers.yButton0.whenHeld(new ExtendClimber(climber, 0.9));
    controllers.xButton0.whenHeld(new RetractClimber(climber, 0.3, 0.9));
    controllers.aButton0.whenHeld(new RetractClimber(climber, 0, -0.4));
    controllers.lBumper0.whenHeld(new ExtendClimber(climber, -0.7));
  }

  public ParallelCommandGroup getAutonomousCommand() {
    return new AutoRoutine(storageSystem, shooter, driveTrain, intake, 15, AutoRoutine.AutoMode.ONE_BALL);
  }
}
