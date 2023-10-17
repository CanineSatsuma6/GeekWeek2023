// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

public class DriveWithJoystick extends CommandBase {
  Drive driveSubsystem;

  DoubleSupplier driveCommand;
  DoubleSupplier rotateCommand;

  /** Creates a new DriveWithJoystick. */
  public DriveWithJoystick(Drive subsystem, DoubleSupplier driveJoystickValue, DoubleSupplier rotateJoystickValue)
  {
    // Use addRequirements() here to declare subsystem dependencies.
    driveSubsystem = subsystem;
    driveCommand = driveJoystickValue;
    rotateCommand = rotateJoystickValue;

    addRequirements(driveSubsystem);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    driveSubsystem.arcadeDrive(driveCommand.getAsDouble(), rotateCommand.getAsDouble());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
