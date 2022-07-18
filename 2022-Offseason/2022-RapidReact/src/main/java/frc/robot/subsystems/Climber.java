// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Climber extends SubsystemBase {


  private final VictorSP upMaster = new VictorSP(Constants.Climber.UP_MASTER_ID);
  private final VictorSP downMaster = new VictorSP(Constants.Climber.DOWN_MASTER_ID);
  private final VictorSP downSlave = new VictorSP(Constants.Climber.DOWN_SLAVE_ID);

  private final SpeedControllerGroup downGroup = new SpeedControllerGroup(downSlave, downMaster);

  public Climber() {
  }

  public void setClimberSpeed(double speed){
    downGroup.set(speed);
    upMaster.set(speed);
  }

  public void extendClimber(double speed){
    upMaster.set(speed);
    downGroup.set(0);
  }

  public void retractClimber(double speedUp, double speedDown){
    upMaster.set(-speedUp);
    downGroup.set(speedDown);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
