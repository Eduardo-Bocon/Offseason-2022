// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class AutoShoot extends CommandBase {

    private final Shooter shooter;
    private double speed;
    private double time;
    private Timer timer = new Timer();

    public AutoShoot(Shooter shooter, double speed, double time) {
        addRequirements(shooter);
        this.shooter = shooter;
        this.speed = speed;
        this.time = time;
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        shooter.activate(-speed);
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stop();
        timer.stop();
    }

    @Override
    public boolean isFinished() {
        return timer.get() >= time;
    }
}
