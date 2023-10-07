// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer 
{
    public RobotContainer() 
    {
        switch (Constants.currentMode) 
        {
            // Real robot, instantiate hardware IO implementations
            case RUN:
                // drive = new Drive(new DriveIOFalcon500());
                // flywheel = new Flywheel(new FlywheelIOFalcon500());
                break;

            // Replayed robot, disable IO implementations
            default:
                // drive = new Drive(new DriveIO() {});
                // flywheel = new Flywheel(new FlywheelIO() {});
                break;
        }

        configureButtonBindings();
    }

    private void configureButtonBindings() 
    {
        
    }

    public Command getAutonomousCommand() 
    {
        return null;
    }
}
