// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.StorageSystem;

public class ActivateStorage extends CommandBase {

  private final StorageSystem storageSystem;
  private XboxController xboxController;

  public ActivateStorage(StorageSystem storageSystem, XboxController xboxController) {
    this.storageSystem = storageSystem;
    this.xboxController = xboxController;
    addRequirements(storageSystem);
   
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute(){
    double leftTrigger = xboxController.getRawAxis(XboxController.Axis.kLeftTrigger.value);
    if(leftTrigger > 0.3){
      storageSystem.activate(leftTrigger * 0.7);
    }else if(xboxController.getLeftBumper()){
      storageSystem.activate(-0.7);
    }else{
      storageSystem.activate(0);
    }

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
