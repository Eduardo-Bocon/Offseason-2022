// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.commandgroups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.auto.AutoStorage;
import frc.robot.commands.TurnDeg;
import frc.robot.commands.auto.AutoIntake;
import frc.robot.commands.auto.AutoShoot;
import frc.robot.commands.auto.DriveStraight;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.StorageSystem;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoRoutine extends ParallelCommandGroup {

  public enum AutoMode {
	TWO_BALLS,
	ONE_BALL_TARMAC,
	ONE_BALL,
	PICK_TWO_BALLS,
	THREE_BALLS,
  }

  public AutoRoutine(StorageSystem storageSystem, Shooter shooter, DriveTrain driveTrain, Intake intake, double time, AutoMode autoMode) {
	if (autoMode == AutoMode.ONE_BALL) {
	  addCommands(
		new AutoStorage(storageSystem, Constants.Storage.SPEED, 4),
	    new AutoShoot(shooter, -0.5, time)
	  );
	} else if (autoMode == AutoMode.ONE_BALL_TARMAC) {
	  addCommands(
	    new SequentialCommandGroup(
		  new AutoStorage(storageSystem, -1, 3),
		  new WaitCommand(2.0),
		  new DriveStraight(driveTrain, Constants.DriveTrain.SPEED, 3.5)
		),
	    new AutoShoot(shooter, -0.5, 10)
	  );
	} else if (autoMode == AutoMode.TWO_BALLS) {
	  addCommands(
	    new SequentialCommandGroup(
		  new AutoStorage(storageSystem, Constants.Storage.SPEED, 2.5),
		  new ParallelCommandGroup(
		    new AutoIntake(intake, 5),
		    new DriveStraight(driveTrain, Constants.DriveTrain.SPEED, 3)
		  ),
		  new DriveStraight(driveTrain, -Constants.DriveTrain.SPEED, 3),
		  new AutoStorage(storageSystem, Constants.Storage.SPEED, 3)
		),
	    new AutoShoot(shooter, -0.55, 13)
	  );
	} else if (autoMode == AutoMode.PICK_TWO_BALLS) {
		addCommands(
		  new SequentialCommandGroup(
			new AutoStorage(storageSystem, Constants.Storage.SPEED, 4),
			new ParallelCommandGroup(
			  new AutoIntake(intake, 6),
			  new DriveStraight(driveTrain, Constants.DriveTrain.SPEED, 5.75)
			)
		  ),
		  new AutoShoot(shooter, -Constants.Shooter.SPEED, 13)
		);

	  } else if (autoMode == AutoMode.THREE_BALLS) {
		addCommands(
		  new SequentialCommandGroup(
			new AutoStorage(storageSystem, Constants.Storage.SPEED, 2),
			new ParallelCommandGroup(
			  new AutoIntake(intake, 2),
			  new DriveStraight(driveTrain,  Constants.DriveTrain.SPEED, 2)
			),
			new DriveStraight(driveTrain, -Constants.DriveTrain.SPEED, 3),
			new AutoStorage(storageSystem, Constants.Storage.SPEED, 2),
			new TurnDeg(driveTrain, 50, 0.6),
			new ParallelCommandGroup(
			  new AutoIntake(intake, 2),
			  new DriveStraight(driveTrain, Constants.DriveTrain.SPEED, 2)
			),
			new DriveStraight(driveTrain, -Constants.DriveTrain.SPEED, 3),
			new TurnDeg(driveTrain, -50, 0.6),
			new AutoStorage(storageSystem, Constants.Storage.SPEED, 2)
		  ),
		  new AutoShoot(shooter, -Constants.Shooter.SPEED, 15)
		);
	  }
  }

}
