package frc.robot.subsystems.pointer;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pointer extends SubsystemBase
{
    private final PointerIO                 _io;
    private final PointerIOInputsAutoLogged _inputs;

    private final PIDController             _pid;

    public Pointer(PointerIO io)
    {
        _io     = io;
        _inputs = new PointerIOInputsAutoLogged();
        _pid    = new PIDController(0.05, 0, 0);    // These constants can be tweaked a bit for better following

        _pid.enableContinuousInput(0, 360);
    }

    @Override
    public void periodic()
    {
        _io.updateInputs(_inputs);
        Logger.getInstance().processInputs("Pointer", _inputs);
    }

    public void resetGyro()
    {
        _io.resetGyro();
    }

    public Command havePotFollowGyro()
    {
        return this.runOnce(() -> _io.setMotor(_pid.calculate(_inputs.potDirection, -_inputs.gyroHeading)))
                   .repeatedly()
                   .finallyDo(interrupted -> _io.setMotor(0));
    }

    public Command zeroHeading()
    {
        return new InstantCommand(() -> resetGyro()).ignoringDisable(true);
    }
}
