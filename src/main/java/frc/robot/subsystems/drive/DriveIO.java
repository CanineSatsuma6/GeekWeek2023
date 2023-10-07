package frc.robot.subsystems.drive;

import org.littletonrobotics.junction.AutoLog;

public interface DriveIO
{
    @AutoLog
    public static class DriveIOInputs
    {
        public double frontLeftCommand;
        public double frontRightCommand;
        public double backLeftCommand;
        public double backRightCommand;
    }

    public default void updateInputs(DriveIOInputs inputs) {}

    public default void arcadeDrive(double drive, double rotate) {}
}
