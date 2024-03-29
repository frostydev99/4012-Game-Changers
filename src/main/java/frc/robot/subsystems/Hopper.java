package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.Constants;

/**
 * @author Daniel Pearson
 * @version 2/18/2020
 */
public class Hopper extends SubsystemBase {
  private final VictorSPX m_conveyor;
  private final VictorSPX m_feeder;

  public Hopper(){
    m_conveyor = new VictorSPX(Constants.CONVEYOR_MOTOR);
    m_feeder = new VictorSPX(Constants.FEEDER_MOTOR);

    m_conveyor.setInverted(InvertType.InvertMotorOutput);

    m_conveyor.setNeutralMode(NeutralMode.Coast);
    m_feeder.setNeutralMode(NeutralMode.Coast);

    m_conveyor.clearStickyFaults();
    m_feeder.clearStickyFaults();

  }
  
  @Override
  public void periodic(){
  }

  public synchronized void carryBall(){
    m_conveyor.set(ControlMode.PercentOutput, .8);
    m_feeder.set(ControlMode.PercentOutput, 1);
  }

  public synchronized void feedBall() {
    m_conveyor.set(ControlMode.PercentOutput, .8);
    m_feeder.set(ControlMode.PercentOutput, .8);
  }

  public synchronized void stop(){
    m_feeder.set(ControlMode.PercentOutput, 0);
    m_conveyor.set(ControlMode.PercentOutput, 0);
  }
}
