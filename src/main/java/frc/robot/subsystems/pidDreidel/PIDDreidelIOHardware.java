package frc.robot.subsystems.pidDreidel;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

public class PIDDreidelIOHardware implements PIDDreidelIO
{
    ADXRS450_Gyro gyro;
    VictorSP motor;
    AnalogInput pot;

    public PIDDreidelIOHardware()
    {
        gyro = new ADXRS450_Gyro();
        motor = new VictorSP(4);
        pot = new AnalogInput(0);

        motor.setInverted(true);

        pot.setAverageBits(2);
        pot.setOversampleBits(0);
    }

    public void updateInputs(PIDDreidelIOInputs inputs)
    {
        inputs.currentPotRotation = getCurrentPotRotation();
        inputs.currentRobotRotation = getCurrentRobotRotation();
    }

    public void setMotor(double speed)
    {
        motor.set(speed);
    }

    public void resetGyro()
    {
        gyro.reset();
    }

    public double getCurrentRobotRotation()
    {
        return Math.IEEEremainder(-gyro.getAngle(), 360) + 180;
    }

    public double getCurrentPotRotation()
    {
        // y = m(x - x1) + y1

        double x = pot.getAverageValue(); // x

        // (x1, y1) = (3996, 360)
        // (x2, y2) = (2, 0)

        double m = 360.0 / 3994.0;

        return m * (x - 2);
    }
}
