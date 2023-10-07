package frc.robot.subsystems.drive;

import java.util.function.DoubleSupplier;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase
{
    private final DriveIO _io;
    private final DriveIOInputsAutoLogged _inputs;

    private double _speedMultiplier;

    public Drive(DriveIO io)
    {
        _io = io;
        _inputs = new DriveIOInputsAutoLogged();

        _speedMultiplier = 1.0;
    }

    @Override
    public void periodic()
    {
        _io.updateInputs(_inputs);

        Logger.getInstance().processInputs("Drive", _inputs);
        Logger.getInstance().recordOutput("Speed Multiplier", _speedMultiplier);
    }

    public void setSpeedMultiplier(double speedMultiplier)
    {
        _speedMultiplier = Math.abs(speedMultiplier);
    }

    public void arcadeDrive(double drive, double rotate)
    {
        _io.arcadeDrive(drive * _speedMultiplier, rotate * _speedMultiplier);
    }

    public void stop()
    {
        _io.arcadeDrive(0, 0);
    }

    public Command driveWithJoystick(DoubleSupplier driveCommand, DoubleSupplier rotateCommand)
    {
        return this.runOnce(() -> arcadeDrive(driveCommand.getAsDouble(), rotateCommand.getAsDouble()))
                   .repeatedly()
                   .finallyDo(interrupted -> stop());
    }

    public Command setDriveSpeedMultiplier(double speedMultiplier)
    {
        return new InstantCommand(() -> setSpeedMultiplier(speedMultiplier)).ignoringDisable(true);
    }
}
