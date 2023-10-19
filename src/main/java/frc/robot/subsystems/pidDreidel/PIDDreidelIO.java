package frc.robot.subsystems.pidDreidel;

import org.littletonrobotics.junction.AutoLog;

public interface PIDDreidelIO
{
    @AutoLog
    public static class PIDDreidelIOInputs
    {
        public double currentPotRotation;
        public double currentRobotRotation;
    }

    public default void updateInputs(PIDDreidelIOInputs inputs) {}

    public default void setMotor(double speed) {}

    public default void resetGyro() {}
}
