// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.misc;

import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** An example command that uses an example subsystem. */
public class OdometryCommand {
    @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    public double x_position = 0;
    public double y_position = 0;
    private double p_leftInches = 0;
    private double p_rightInches = 0;
    public double rotation = 0;
    public long pTime = 0;
    public long cTime = 0;
    double gearRatio = 1 / 10.71;

    public OdometryCommand() {
        pTime = System.currentTimeMillis();
        cTime = System.currentTimeMillis();
    }

    // Called every time the scheduler runs while the command is scheduled.
    public void execute(DrivetrainSubsystem drivetrain) {
        pTime = cTime;
        cTime = System.currentTimeMillis();
        SmartDashboard.putNumber("x_position", x_position);
        SmartDashboard.putNumber("y_position", y_position);
        SmartDashboard.putNumber("leftTicks", drivetrain.getLeftDistanceTicks());
        SmartDashboard.putNumber("rightTicks", drivetrain.getRightDistanceTicks());
        SmartDashboard.putNumber("rotation", rotation * 180 / Math.PI);
        double l = 21.8125 / 2;
        double leftInches = drivetrain.getLeftDistanceInch() * gearRatio - p_leftInches;
        double rightInches = drivetrain.getRightDistanceInch() * gearRatio - p_rightInches;
        p_leftInches = drivetrain.getLeftDistanceInch() * gearRatio;
        p_rightInches = drivetrain.getRightDistanceInch() * gearRatio;
        double deltaAngle = (rightInches - leftInches) / (2 * l);
        rotation += deltaAngle;
        double deltaS = (rightInches + leftInches) / 2;
        double deltaX = deltaS * Math.cos(rotation + deltaAngle / 2);
        double deltaY = deltaS * Math.sin(rotation + deltaAngle / 2);
        x_position += deltaX;
        y_position += deltaY;
        double delta = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        SmartDashboard.putNumber("velocity(i/s)", (delta / cTime) * 1000);
    }
}
