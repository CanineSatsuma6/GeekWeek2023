package frc.robot.subsystems.pointer;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

public class PointerIOGyroPot implements PointerIO
{
    private static final double MIN_POT = 2;
    private static final double MAX_POT = 3996;
    private static final double POT_RANGE = MAX_POT - MIN_POT;

    private final ADXRS450_Gyro _gyro;
    private final AnalogInput   _pot;
    private final VictorSP      _motor;

    public PointerIOGyroPot()
    {
        _gyro  = new ADXRS450_Gyro();
        _pot   = new AnalogInput(0);
        _motor = new VictorSP(4);

        _pot.setAverageBits(2);
        _pot.setOversampleBits(0);

        _motor.setInverted(true);
    }

    @Override
    public void updateInputs(PointerIOInputs inputs)
    {
        inputs.gyroHeading  = getGyroHeading();
        inputs.potDirection = getPotDirection();
        inputs.motorCommand = _motor.get();
    }

    @Override
    public void resetGyro()
    {
        _gyro.reset();
    }

    @Override
    public void setMotor(double speed)
    {
        _motor.set(speed);
    }

    private double getGyroHeading()
    {
        return Math.IEEEremainder(-_gyro.getAngle(), 360.0);
    }

    private final double getPotDirection()
    {
        return (_pot.getAverageValue() - MIN_POT)  * 360.0 / POT_RANGE;
    }
}
