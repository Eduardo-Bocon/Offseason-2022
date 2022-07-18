// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class StorageSystem extends SubsystemBase {

  private final VictorSPX motor = new VictorSPX(Constants.Storage.STRG_ID);

  public StorageSystem() {
    motor.setInverted(false);
  }

  @Override
  public void periodic() {}

  public void activate(double speed){
    motor.set(ControlMode.PercentOutput, speed);
  }

  public void stop(){
    motor.set(ControlMode.PercentOutput, 0.0);
  }

}
