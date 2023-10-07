package frc.robot.subsystems.pointer;

import org.littletonrobotics.junction.AutoLog;

public interface PointerIO
{
    @AutoLog
    public static class PointerIOInputs
    {
        public double gyroHeading;
        public double potDirection;
        public double motorCommand;
    }

    public default void updateInputs(PointerIOInputs inputs) {}

    public default void resetGyro() {}

    public default void setMotor(double speed) {}
}
