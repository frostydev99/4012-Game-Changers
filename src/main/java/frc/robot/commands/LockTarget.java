/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Limelight.CamModes;
import frc.robot.subsystems.Limelight.LedModes;
import frc.robot.subsystems.Limelight.LimelightConstants;

public class LockTarget extends CommandBase {
  public DriveTrain drive;
  public Limelight limelight;

  private double kProt = LimelightConstants.kProt;
  private double kPdist = LimelightConstants.kPdist;

  public LockTarget(DriveTrain subsystem, Limelight vision) {
    drive = subsystem;
    limelight = vision;
    addRequirements(drive, limelight);
  }

  @Override
  public void initialize() {
    limelight.setLed(LedModes.ON);
    limelight.setCam(CamModes.VISION);
  }

  @Override
  public void execute() {
    double steeringAdjust = kProt * limelight.getTargetX();

    if(limelight.getTargetX() > .8){
      steeringAdjust += LimelightConstants.kMin;
    } else if(limelight.getTargetX() < .8){
      steeringAdjust -= LimelightConstants.kMin;
    }

    double distanceAdjust = (3 - limelight.getTargetArea()) * kPdist;
    if(distanceAdjust > .5){
      distanceAdjust = .5;
    }
    
    System.out.println("Dist: " + distanceAdjust);
    System.out.println("Rot: " + steeringAdjust);

    drive.arcadeDrive(-steeringAdjust, -distanceAdjust);
    System.out.println(steeringAdjust);
    System.out.println(distanceAdjust);
  }

  @Override
  public void end(boolean interrupted) {
    drive.manualDrive(0, 0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
