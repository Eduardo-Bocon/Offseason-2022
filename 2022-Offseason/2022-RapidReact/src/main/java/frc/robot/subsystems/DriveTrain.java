// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.simulation.ADXRS450_GyroSim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotGearing;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotMotor;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim.KitbotWheelSize;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  private DifferentialDrive differentialDrive;
  private final WPI_TalonSRX leftMaster = new WPI_TalonSRX(Constants.DriveTrain.LEFT_MASTER_ID);
  private final WPI_TalonSRX leftSlave = new WPI_TalonSRX(Constants.DriveTrain.LEFT_SLAVE_ID);
  private final WPI_TalonSRX rightMaster = new WPI_TalonSRX(Constants.DriveTrain.RIGHT_MASTER_ID);
  private final WPI_TalonSRX rightSlave = new WPI_TalonSRX(Constants.DriveTrain.RIGHT_SLAVE_ID);


  private final ADXRS450_Gyro gyro = new ADXRS450_Gyro();
  private final ADXRS450_GyroSim gyroSim = new ADXRS450_GyroSim(gyro);

  private boolean isInverted = true;

  public final Encoder rightEncoder = new Encoder(
    Constants.A_CHANNEL,
    Constants.B_CHANNEL,
    false);

  public final Encoder leftEncoder = new Encoder(
    Constants.C_CHANNEL,
    Constants.D_CHANNEL,
    false);

  private DifferentialDrivetrainSim driveSim = DifferentialDrivetrainSim.createKitbotSim(
    KitbotMotor.kDualCIMPerSide,
    KitbotGearing.k10p71,
    KitbotWheelSize.kSixInch,
    null
  );

  private Field2d field = new Field2d();
  private DifferentialDriveOdometry odometry;

  /** Creates a new DriveTrain. */
  public DriveTrain() {

    leftMaster.configFactoryDefault();
    leftSlave.configFactoryDefault();
    rightMaster.configFactoryDefault();
    rightSlave.configFactoryDefault();

    leftMaster.setVoltage(12);
    leftSlave.setVoltage(12);
    MotorControllerGroup RightGroup = new MotorControllerGroup(rightMaster, rightSlave);
    MotorControllerGroup LeftGroup = new MotorControllerGroup(leftMaster, leftSlave);

    differentialDrive = new DifferentialDrive(LeftGroup, RightGroup);
   
    leftEncoder.setDistancePerPulse(Constants.CONVERT_TO_DISTANCE);
    rightEncoder.setDistancePerPulse(Constants.CONVERT_TO_DISTANCE);

    SmartDashboard.putData("Field", field);
    odometry = new DifferentialDriveOdometry(driveSim.getHeading());

  }

    public void arcadeDrive(double moveSpeed, double rotateSpeed){
      differentialDrive.arcadeDrive(moveSpeed, rotateSpeed);
    }


  public static double deadZone (double input){
    if(Math.abs(input) < 0.2){
      return 0;
    }else if(input > 0.2){
      //SmartDashboard.putNumber("Input pos deadzone", 1.25 * (input + 0.25));
      return 1.25 * (input - 0.2);
    } else {
      //SmartDashboard.putNumber("Input neg deadzone", 1.25 * (input - 0.25));
      return 1.25* (input + 0.2);
    }
  }
  


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    odometry.update(driveSim.getHeading(), driveSim.getLeftPositionMeters(), driveSim.getRightPositionMeters());
    field.setRobotPose(odometry.getPoseMeters());
  }

  @Override
  public void simulationPeriodic() {
    driveSim.setInputs(
      leftMaster.get() * RobotController.getInputVoltage(),
      -rightMaster.get() * RobotController.getInputVoltage()
    );

    driveSim.update(0.02);
    gyroSim.setAngle(-driveSim.getHeading().getDegrees());
  }

  public ADXRS450_Gyro getGyro() {
    return gyro;
  }

  public boolean isInverted() {
	return isInverted;
  }

  public void setInverted(boolean isInverted) {
	this.isInverted = isInverted;
  }

  public void invert() {
	isInverted = !isInverted;
  }
}
