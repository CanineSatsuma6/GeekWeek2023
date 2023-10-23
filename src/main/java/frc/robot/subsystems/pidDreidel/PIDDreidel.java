package frc.robot.subsystems.pidDreidel;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PIDDreidel extends SubsystemBase
{
    private PIDDreidelIO io;
    private PIDDreidelIOInputsAutoLogged inputs;

    private PIDController pid;

    /** Creates a new PIDDreidel. */
    public PIDDreidel(PIDDreidelIO hardwareIO)
    {
        io = hardwareIO;
        inputs = new PIDDreidelIOInputsAutoLogged();

        pid = new PIDController(0.01, 0, 0);

        pid.enableContinuousInput(0, 360);
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
        double motorSpeed = pid.calculate(inputs.currentPotRotation, getTarget());

        io.setMotor(motorSpeed);

        Logger.getInstance().recordOutput("dreidel speed", motorSpeed);
    }

    public double getTarget()
    {
        return -inputs.currentRobotRotation + 360;
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
