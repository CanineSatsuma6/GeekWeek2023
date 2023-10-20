package frc.robot.subsystems.pidDreidel;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PIDDreidel extends SubsystemBase
{
    private PIDDreidelIO io;
    private PIDDreidelIOInputsAutoLogged inputs;

    /** Creates a new PIDDreidel. */
    public PIDDreidel(PIDDreidelIO hardwareIO)
    {
        io = hardwareIO;
        inputs = new PIDDreidelIOInputsAutoLogged();
    }

    @Override
    public void periodic()
    {
        // This method will be called once per scheduler run
        io.updateInputs(inputs);

        Logger.getInstance().processInputs("PIDDreidel", inputs);
    }

    public void spin()
    {
        motor.set(pid.calculate(getCurrentPotRotation(), getTarget()));
    }

    public double getTarget()
    {
        return -inputs.currentRobotRotation;
    }

    public void gyroReset()
    {
        io.resetGyro();
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
