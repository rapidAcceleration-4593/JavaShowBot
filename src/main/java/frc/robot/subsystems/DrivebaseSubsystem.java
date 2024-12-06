package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.DrivebaseConstants;
import frc.robot.controllers.IMultiController;

public class DrivebaseSubsystem extends SubsystemBase {

    public enum DriveMode {
        TANK,
        ARCADE,
        CURVE
    }

    private DriveMode driveMode = DrivebaseConstants.defaultDriveMode;

    // Declare and Assign IDs to CANSparkMax
    private final CANSparkMax frontLeftMotor = DrivebaseConstants.frontLeftMotor;
    private final CANSparkMax frontRightMotor = DrivebaseConstants.frontRightMotor;
    private final CANSparkMax rearLeftMotor = DrivebaseConstants.rearLeftMotor;
    private final CANSparkMax rearRightMotor = DrivebaseConstants.rearRightMotor;

    // Construct a Differential Drive object
    private final DifferentialDrive drive = new DifferentialDrive(frontLeftMotor, frontRightMotor);

    /**
     * Drivebase Subsystem Constructor
     * @return Establishes motor controller groups between left and right side.
     */
    public DrivebaseSubsystem() {
        frontLeftMotor.setIdleMode(Constants.DrivebaseConstants.driveBaseIdleMode);
        frontRightMotor.setIdleMode(Constants.DrivebaseConstants.driveBaseIdleMode);
        rearLeftMotor.setIdleMode(Constants.DrivebaseConstants.driveBaseIdleMode);
        rearRightMotor.setIdleMode(Constants.DrivebaseConstants.driveBaseIdleMode);

        rearLeftMotor.follow(frontLeftMotor);
        rearRightMotor.follow(frontRightMotor);
    }


    public Command getToNextDriveModeCommand() {
        return runOnce(() -> toNextDriveMode());
    }

    /**
     * Arcade Drive Command to control binding inputs
     * @param translation Forward/Backward Movement with Left Joystick
     * @param rotation Rotational Movement with Right Joystick
     */
    public void arcadeDrive(double translation, double rotation) {
        drive.arcadeDrive(-rotation, -translation);
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        drive.tankDrive(leftSpeed, -rightSpeed);
    }

    public void curveDrive(double translation, double rotation) {
        drive.curvatureDrive(-translation, -rotation, false);
    }

    public void multiDrive(IMultiController control) {
        
        switch (driveMode) {
            case TANK:
                tankDrive(control.getLeftRightSpeeds().getX(), control.getLeftRightSpeeds().getY());
                break;

            case CURVE:
                curveDrive(control.getTransRot().getX(), control.getTransRot().getY());
                break;

            case ARCADE:
                arcadeDrive(control.getTransRot().getX(), control.getTransRot().getY());
                break;
        
            default:
                System.err.println("Attempted to use unsupported DriveMode!");
                tankDrive(control.getLeftRightSpeeds().getX(), control.getLeftRightSpeeds().getY());
                break;
        }
    }

    public void setDriveMode(DriveMode mode) {
        driveMode = mode;
    }

    public void toNextDriveMode() {
        driveMode = DriveMode.values()[(driveMode.ordinal() + 1) % DriveMode.values().length];
    }

    public DriveMode getDriveMode() {
        return driveMode;
    }

    /**
     * Stop all drivebase motors
     */
    public void stop() {
        drive.stopMotor();
    }
}
