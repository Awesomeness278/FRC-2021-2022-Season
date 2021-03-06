// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.misc.OdometryCommand;
import frc.robot.subsystems.DrivetrainSubsystem;

public class AutonomousCommand extends CommandBase {
  /** Creates a new AutonomousCommand. */
  DrivetrainSubsystem drivetrain;

  OdometryCommand odometry = new OdometryCommand();

  public AutonomousCommand(DrivetrainSubsystem drivetrain_arg) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain_arg);
    drivetrain = drivetrain_arg;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    odometry.execute(drivetrain);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
