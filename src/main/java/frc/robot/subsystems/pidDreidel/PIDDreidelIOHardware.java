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

    public void updateInputs(PIDDreidelIOInputs inputs) {}

    public void setMotor(double speed) {}

    public void resetGyro() {}
}
