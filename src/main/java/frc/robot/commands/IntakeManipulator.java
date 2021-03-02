/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Intake;

public class IntakeManipulator extends CommandBase {

  Intake intake;
  Hopper myHopper;

  public IntakeManipulator(Intake subsystem, Hopper hopper) {
    intake = subsystem;
    myHopper = hopper;

    addRequirements(intake, myHopper);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    intake.retractIntake();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intake.deployIntake();
    myHopper.carryBall();
  }

  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.retractIntake();
    myHopper.stop();
  }



  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
