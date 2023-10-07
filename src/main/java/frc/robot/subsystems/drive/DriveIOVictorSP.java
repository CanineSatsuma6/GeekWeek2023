package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

public class DriveIOVictorSP implements DriveIO
{
    private VictorSP _flMotor;
    private VictorSP _frMotor;
    private VictorSP _blMotor;
    private VictorSP _brMotor;

    private MotorControllerGroup _leftMotors;
    private MotorControllerGroup _rightMotors;

    private DifferentialDrive _drive;

    public DriveIOVictorSP()
    {
        _flMotor = new VictorSP(0);
        _frMotor = new VictorSP(1);
        _blMotor = new VictorSP(2);
        _brMotor = new VictorSP(3);

        _flMotor.setInverted(false);
        _frMotor.setInverted(true);
        _blMotor.setInverted(false);
        _brMotor.setInverted(true);

        _leftMotors = new MotorControllerGroup(_flMotor, _blMotor);
        _rightMotors = new MotorControllerGroup(_frMotor, _brMotor);

        _drive = new DifferentialDrive(_leftMotors, _rightMotors);
    }

    public void updateInputs(DriveIOInputs inputs)
    {
        inputs.frontLeftCommand  = _flMotor.get();
        inputs.frontRightCommand = _frMotor.get();
        inputs.backLeftCommand   = _blMotor.get();
        inputs.backRightCommand  = _brMotor.get();
    }

    public void arcadeDrive(double drive, double rotate)
    {
        _drive.arcadeDrive(drive, rotate);
    }
}
