// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkRelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import frc.robot.subsystems.DrivebaseSubsystem;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int driverControllerPort = 0;
  }

  public static final class DrivebaseConstants {
    public static final CANSparkMax frontLeftMotor = new CANSparkMax(3, MotorType.kBrushless);
    public static final CANSparkMax frontRightMotor = new CANSparkMax(4, MotorType.kBrushless);
    public static final CANSparkMax rearLeftMotor = new CANSparkMax(5, MotorType.kBrushless);
    public static final CANSparkMax rearRightMotor = new CANSparkMax(7, MotorType.kBrushless);

    public static final IdleMode driveBaseIdleMode = IdleMode.kBrake;
    
    public static final DrivebaseSubsystem.DriveMode defaultDriveMode = DrivebaseSubsystem.DriveMode.TANK;
  }

  public static final class PuckShooterConstants {
    public static final CANSparkMax leftShooter = new CANSparkMax(2, MotorType.kBrushless);
    public static final CANSparkMax rightShooter = new CANSparkMax(6, MotorType.kBrushless);

    public static final CANSparkMax spinner = new CANSparkMax(1, MotorType.kBrushed);

    public static final SparkRelativeEncoder leftShooterEncoder = (SparkRelativeEncoder) leftShooter.getEncoder();
    public static final SparkRelativeEncoder rightShooterEncoder = (SparkRelativeEncoder) rightShooter.getEncoder();
  }
}
