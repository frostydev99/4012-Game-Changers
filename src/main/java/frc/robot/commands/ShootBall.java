/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Shooter;

public class ShootBall extends CommandBase {
  private Shooter shooter;
  private final Hopper hopper;

  private final int setpoint = 3000;
  private final int acceptance = 650;

  public ShootBall(Shooter subsystem1, Hopper subsystem2) {
    shooter = subsystem1;
    hopper = subsystem2;
    
    addRequirements(hopper);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //shooter.activateClosedLoopControl();
    shooter.setSpeed(.65);

    double error = (shooter.getRpm()+setpoint);
    SmartDashboard.putNumber("Error", error);

    System.out.println(error);
    if(error < acceptance) {
      hopper.feedBall();
    }




  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    hopper.stop();
    shooter.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
