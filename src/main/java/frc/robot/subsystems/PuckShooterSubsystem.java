package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkRelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PuckShooterConstants;

public class PuckShooterSubsystem extends SubsystemBase {

    // Declare and Assign IDs to Motors and Encoders
    public static final CANSparkMax leftShooter = PuckShooterConstants.leftShooter;
    public static final CANSparkMax rightShooter = PuckShooterConstants.rightShooter;

    public static final CANSparkMax spinner = PuckShooterConstants.spinner;

    public static final SparkRelativeEncoder leftShooterEncoder = PuckShooterConstants.leftShooterEncoder;
    public static final SparkRelativeEncoder rightShooterEncoder = PuckShooterConstants.rightShooterEncoder;

    /**
     * Puck Shooter Subsystem Constructor
     * @return Connect leftShooter and rightShooter ouputs, inverting the left side
     */
    public PuckShooterSubsystem () {
        spinner.setIdleMode(IdleMode.kBrake);
        leftShooter.follow(rightShooter, true);
    }

    /**
     * Run both shooter motors
     * @param speed Motor speed from -1 to 1
     */
    public void runShooter(double speed) {
        rightShooter.set(speed);
    }

    /**
     * Stop both shooter motors
     * @return Sets shooter motor speeds to zero
     */
    public void stopShooter() {
        rightShooter.set(0);
    }

    /**
     * Run spinner wheel motor
     * @param speed Motor speed from -1 to 1
     */
    //TODO Implement with new motor
    public void runSpinnerWheel(double speed) {
        spinner.set(speed);
    }

    /**
     * Stop spinner wheel motor
     * @return Sets spinner wheel motor speed to zero
     */
    //TODO Implement with new motor
    public void stopSpinnerWheel() {
        spinner.set(0);
    }

    /**
     * Checks absolute value of the average velocity from each shooting motor's controller
     * @return boolean of average encoder velocity greater than 5500 RPMs
     */
    //TODO optimize so it shoots at maximum speed
    public boolean IsAtShootingSpeed()
    {   
        // Shooter encoders use different measurement systems currently
        return Math.abs(leftShooterEncoder.getVelocity() + rightShooterEncoder.getVelocity()*1000)/2 > 4000;
    }
}
