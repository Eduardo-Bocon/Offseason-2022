// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climber;
import frc.robot.commands.ActivateStorage;
import frc.robot.commands.DriveRobot;
import frc.robot.commands.ExtendClimber;
import frc.robot.commands.GrabBalls;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.StorageSystem;
import frc.robot.commands.commandgroups.AutoRoutine;
import frc.robot.commands.commandgroups.ConveyorAndShooter;
import frc.robot.commands.commandgroups.IntakeAndConveyor;
import frc.robot.commands.commandgroups.AutoRoutine.AutoMode;
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
  //public UsbCamera camera = new UsbCamera("front Camera", 0);
  // private POVButton povButton = new POVButton(controllers.xboxController, -1);

  //Auto Commands
  private final Command OneBall = new AutoRoutine(
    storageSystem, shooter, driveTrain, intake, 5, AutoMode.ONE_BALL);

  private final Command OneBallTarmac = new AutoRoutine(
    storageSystem, shooter, driveTrain, intake, 5, AutoMode.ONE_BALL_TARMAC);

  private final Command TwoBalls = new AutoRoutine(
    storageSystem, shooter, driveTrain, intake, 10, AutoMode.TWO_BALLS);

  private final Command PickTwoBalls = new AutoRoutine(
    storageSystem, shooter, driveTrain, intake, 5, AutoMode.PICK_TWO_BALLS);

  private final Command ThreeBalls = new AutoRoutine(
    storageSystem, shooter, driveTrain, intake, 5, AutoMode.THREE_BALLS);
  
  //Chooser
  SendableChooser<Command> chooser = new SendableChooser<>();


  
  public RobotContainer() {
    driveTrain.setDefaultCommand(new DriveRobot(driveTrain, controllers.xboxController0));
    intake.setDefaultCommand(new GrabBalls(intake, controllers));
    shooter.setDefaultCommand(new Shoot(shooter, controllers, 0));
    storageSystem.setDefaultCommand(new ActivateStorage(storageSystem, controllers.xboxController1));
    configureButtonBindings();

    chooser.setDefaultOption("One ball", OneBall);
    chooser.addOption("One ball & tarmac", OneBallTarmac);
    chooser.addOption("Two balls", TwoBalls);
    chooser.addOption("Pick two balls", PickTwoBalls);
    chooser.addOption("Three Balls", ThreeBalls);

    SmartDashboard.putData(chooser);
  }

  private void configureButtonBindings() {
    //PILOTO BOTÕES

    //BOTAO Y - Subir motor de janela
    controllers.yButton0.whenHeld(new ExtendClimber(climber, 0.7));
    //BOTAO A - Descer o downgroup e subir o de janela
    controllers.aButton0.whenHeld(new RetractClimber(climber, 0.3, 0.9));
    //BOTAO X - Subir downgroup sem o de janela
    controllers.xButton0.whenHeld(new RetractClimber(climber, 0, -0.4));
    //BOTAO B - Descer motor de janela
    controllers.bButton0.whenHeld(new ExtendClimber(climber, -0.7));

    //controllers.bButton0.toggleWhenPressed(new DriveRobot(driveTrain, controllers.xboxController0));

    //COPILOTO BOTÕES
    controllers.yButton1.whenHeld(new ConveyorAndShooter(storageSystem, shooter));
    controllers.aButton1.toggleWhenPressed(new Shoot(shooter, controllers, Constants.Shooter.SPEED));
    controllers.xButton1.whenHeld(new IntakeAndConveyor(storageSystem, intake));
    controllers.bButton1.toggleWhenPressed(new Shoot(shooter, controllers, 0.5));
  }

  public Command getAutonomousCommand() {
  return chooser.getSelected();
  }
}
