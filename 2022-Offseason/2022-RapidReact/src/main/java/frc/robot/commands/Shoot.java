// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Controllers;
import frc.robot.subsystems.Shooter;

public class Shoot extends CommandBase {

    private final Shooter shooter;
    private Controllers controllers;
    private boolean isActive = false;
    private boolean justPressed = false;

    public Shoot(Shooter shooter, Controllers controllers) {
        addRequirements(shooter);
        this.shooter = shooter;
        this.controllers = controllers;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        if (controllers.xboxController1.getRawAxis(3) > 0.2) {
            if (!justPressed) {
                isActive = !isActive;
                justPressed = true;
            }
        } else {
            justPressed = false;
        }
        
        if (isActive) {
            shooter.activate(0.8);
        } else {
            shooter.stop();
        }
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
