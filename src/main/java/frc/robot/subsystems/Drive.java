package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase
{
    VictorSP frontLeftMotor;
    VictorSP frontRightMotor;
    VictorSP backLeftMotor;
    VictorSP backRightMotor;

    MotorControllerGroup leftMotors;
    MotorControllerGroup rightMotors;

    DifferentialDrive differentialDrive;

    public Drive()
    {
        frontLeftMotor = new VictorSP(0);
        frontRightMotor = new VictorSP(1);
        backLeftMotor = new VictorSP(2);
        backRightMotor = new VictorSP(3);

        leftMotors = new MotorControllerGroup(frontLeftMotor, backLeftMotor);
        rightMotors = new MotorControllerGroup(frontRightMotor, backRightMotor);

        rightMotors.setInverted(true);

        differentialDrive = new DifferentialDrive(leftMotors, rightMotors);
    }

    public void arcadeDrive(double driveCommand, double rotateCommand)
    {
        differentialDrive.arcadeDrive(driveCommand, rotateCommand);
    }
}
