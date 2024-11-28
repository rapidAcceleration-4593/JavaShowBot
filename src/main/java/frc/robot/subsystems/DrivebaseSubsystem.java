package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivebaseConstants;

public class DrivebaseSubsystem extends SubsystemBase {

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
        rearLeftMotor.follow(frontLeftMotor);
        rearRightMotor.follow(frontRightMotor);
    }

    /**
     * Arcade Drive Command to control binding inputs
     * @param translation Forward/Backward Movement with Left Joystick
     * @param rotation Rotational Movement with Right Joystick
     */
    public void arcadeDrive(double translation, double rotation) {
        drive.arcadeDrive(translation, rotation);
    }

    /**
     * Stop all drivebase motors
     */
    public void stop() {
        drive.stopMotor();
    }
}
