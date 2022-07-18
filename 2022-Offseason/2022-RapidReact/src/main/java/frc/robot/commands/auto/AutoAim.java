// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;

public class AutoAim extends CommandBase {

    private final DriveTrain dt;
    private Timer timer = new Timer();
    private double tx;
    private double ty;

    public AutoAim(DriveTrain dt, double tx, double ty) {
        addRequirements(dt);
        this.dt = dt;
        this.tx = tx;
        this.ty = ty;
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        if(tx > 2){
            dt.arcadeDrive(0.5, -0.5);
        } else if(tx < -2){
            dt.arcadeDrive(-0.5, 0.5);
        }
    }

    @Override
    public void end(boolean interrupted) {
        dt.arcadeDrive(0, 0);
        timer.stop();
    }

    @Override
    public boolean isFinished() {
        return timer.get() >= 3;
    }
}
