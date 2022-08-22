// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.StorageSystem;

public class TeleStorage extends CommandBase {

  private final StorageSystem storageSystem;

  public TeleStorage(StorageSystem storageSystem) {
    this.storageSystem = storageSystem;
    addRequirements(storageSystem);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    storageSystem.activate(Constants.Storage.SPEED);
  }

  @Override
  public void end(boolean interrupted) {
    storageSystem.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
