// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.PIDDreidel;

public class RobotContainer 
{
    Joystick joystick;
    JoystickButton buttonY;

    Drive drive;
    PIDDreidel pidDreidel;

    DriveWithJoystick driveWithJoystick;

    public RobotContainer() 
    {
        joystick = new Joystick(0);
        buttonY = new JoystickButton(joystick, 4);

        drive = new Drive();
        pidDreidel = new PIDDreidel();

        driveWithJoystick = new DriveWithJoystick
        (
            drive,
            () -> -joystick.getY(),
            () -> -joystick.getX()
        );

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
        drive.setDefaultCommand(driveWithJoystick);
        pidDreidel.setDefaultCommand(pidDreidel.pointWithDreidel());

        buttonY.onTrue(pidDreidel.resetDreidel());
    }

    public Command getAutonomousCommand() 
    {
        return null;
    }
}
