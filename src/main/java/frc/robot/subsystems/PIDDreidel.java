package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PIDDreidel extends SubsystemBase
{
    // Gyro
    // Motor Controller
    // Potentiometer
    // PID Controller
    ADXRS450_Gyro gyro;
    VictorSP motor;
    AnalogInput pot;
    PIDController pid;

    public PIDDreidel()
    {
        gyro = new ADXRS450_Gyro();
        motor = new VictorSP(4);
        pot = new AnalogInput(0);
        pid = new PIDController(0.01, 0, 0);

        motor.setInverted(true);

        pot.setAverageBits(2);
        pot.setOversampleBits(0);

        pid.enableContinuousInput(0, 360);
    }

    public void spin()
    {
        motor.set(pid.calculate(getCurrentPotRotation(), getTarget()));
    }

    public double getTarget()
    {
        return -getCurrentRobotRotation();
    }

    public double getCurrentRobotRotation()
    {
        return Math.IEEEremainder(-gyro.getAngle(), 360);
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

    public void gyroReset()
    {
        gyro.reset();
    }

    public Command pointWithDreidel()
    {
        return this.run(this::spin);
    }

    public Command resetDreidel()
    {
        return this.runOnce(this::gyroReset);
    }
}
