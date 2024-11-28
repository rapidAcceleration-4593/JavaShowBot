package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.DrivebaseSubsystem;

public class ArcadeDriveCommand extends Command {
    private final DrivebaseSubsystem drivebase;
    private final CommandXboxController controller;

    public ArcadeDriveCommand(DrivebaseSubsystem drivebase, CommandXboxController controller) {
        this.drivebase = drivebase;
        this.controller = controller;

        addRequirements(drivebase);
    }

    @Override
    public void execute() {
        double translation = -controller.getLeftY(); // Inverse maybe?
        double rotation = controller.getRightX();
        drivebase.arcadeDrive(translation, rotation);
    }

    @Override
    public void end(boolean interrupted) {
        drivebase.stop();
    }
}
