// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.drive.Drive;
import frc.robot.subsystems.drive.DriveIO;
import frc.robot.subsystems.drive.DriveIOVictorSP;
import frc.robot.subsystems.pointer.Pointer;
import frc.robot.subsystems.pointer.PointerIO;
import frc.robot.subsystems.pointer.PointerIOGyroPot;

public class RobotContainer 
{
    private final Joystick _driveJoy;

    private final Drive    _drive;
    private final Pointer  _pointer;

    public RobotContainer() 
    {
        _driveJoy = new Joystick(0);

        switch (Constants.currentMode) 
        {
            // Real robot, instantiate hardware IO implementations
            case RUN:
                _drive   = new Drive(new DriveIOVictorSP());
                _pointer = new Pointer(new PointerIOGyroPot());
                break;

            // Replayed robot, disable IO implementations
            default:
                _drive = new Drive(new DriveIO(){});
                _pointer = new Pointer(new PointerIO(){});
                break;
        }

        configureButtonBindings();
    }

    private void configureButtonBindings() 
    {
        _drive.setDefaultCommand(_drive.driveWithJoystick(() -> -_driveJoy.getY(), () -> -_driveJoy.getX()));
        _pointer.setDefaultCommand(_pointer.havePotFollowGyro());

        new JoystickButton(_driveJoy,  2).onTrue(_drive.setDriveSpeedMultiplier(0.25));
        new JoystickButton(_driveJoy,  1).onTrue(_drive.setDriveSpeedMultiplier(0.50));
        new JoystickButton(_driveJoy,  3).onTrue(_drive.setDriveSpeedMultiplier(0.75));
        new JoystickButton(_driveJoy,  4).onTrue(_drive.setDriveSpeedMultiplier(1.00));

        new JoystickButton(_driveJoy, 10).onTrue(_pointer.zeroHeading());
    }

    public Command getAutonomousCommand() 
    {
        return null;
    }
}
