package org.usfirst.frc.team4028.robot.subsystemSequences;

import java.util.Date;

import org.usfirst.frc.team4028.robot.constants.RobotMap;
import org.usfirst.frc.team4028.robot.subsystems.Infeed;
import org.usfirst.frc.team4028.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.DriverStation;

/*
 * This is a "sequence" class for shooting the ball
 * This pattern is used when we need to coordinate >1 subsystem to implement a robot function
 * In this case we need to coordinate the Infeed & Shooter subsystems in order to shoot the ball.
 */
public class ShootBallSeq 
{
	// constants
	private static final double TIME_TO_INFEED_IN_MS = 0.5 * 1000;
	private static final double TIME_TO_SPIN_SHOOTER_WHEELS_IN_MS = 1 * 1000;
	private static final double TIME_TO_INFEED_OUT_IN_MS = 0.5 * 1000;
	
	// enum for state machine
	public enum SHOOT_BALL_STATE
	{
		UNDEFINED,
		SET_VARIABLES,
		INFEED_IN,
		SPIN_SHOOTER_WHEELS,
		INFEED_OUT,
		SEQUENCE_COMPLETE,
	}
	
	// member variables for robot objects
	private Infeed _infeedSubsystem;
	private Shooter _shooterSubsystem;
	
	// working variables
	public long _shootBallSequenceStartTime;
	public boolean _isShootBallStillRunning;
	private SHOOT_BALL_STATE _shootBallState;
		
	// constructor
	public ShootBallSeq(Infeed infeedSubsystem, Shooter shooterSubsystem)
	{
		_infeedSubsystem = infeedSubsystem;
		_shooterSubsystem = shooterSubsystem;
		
		_shootBallState = SHOOT_BALL_STATE.UNDEFINED;
		_isShootBallStillRunning = false;
	}
	
	// main method called during periodic mode, implements state machine
	public void Execute()
	{
		long sequenceElapsedTime = 0;
		
		switch(_shootBallState)
		{
			case UNDEFINED:
				_shootBallState = SHOOT_BALL_STATE.SET_VARIABLES;
				_isShootBallStillRunning = true;
				break;
				
			case SET_VARIABLES:
				_shootBallSequenceStartTime = new Date().getTime();
				DriverStation.reportError("IN SET VARIABLES MODE", false);
				_shootBallState = SHOOT_BALL_STATE.INFEED_IN;
				break;
				
			case INFEED_IN:
				sequenceElapsedTime = (new Date().getTime() - _shootBallSequenceStartTime);
				
				_infeedSubsystem.RunInfeedAcquireMotorIn();
				
				if (sequenceElapsedTime > TIME_TO_INFEED_IN_MS)
				{
					DriverStation.reportError("Changing to Spin Shooter Wheels State", false);
					_shootBallState = SHOOT_BALL_STATE.SPIN_SHOOTER_WHEELS;
				}
				break;
				
			case SPIN_SHOOTER_WHEELS:
				sequenceElapsedTime = (new Date().getTime() - _shootBallSequenceStartTime);
				
				_shooterSubsystem.RunShooterMotors(-1.0);
				
				if (sequenceElapsedTime > (TIME_TO_INFEED_IN_MS + TIME_TO_SPIN_SHOOTER_WHEELS_IN_MS))
				{
					_shootBallState = SHOOT_BALL_STATE.INFEED_OUT;
					DriverStation.reportError("Changing to Infeed Out State", false);
				}
				break;
				
			case INFEED_OUT:
				sequenceElapsedTime = (new Date().getTime() - _shootBallSequenceStartTime);
				
				_infeedSubsystem.RunInfeedAcquireMotorOut();
				
				if (sequenceElapsedTime > (TIME_TO_INFEED_IN_MS + TIME_TO_SPIN_SHOOTER_WHEELS_IN_MS + TIME_TO_INFEED_OUT_IN_MS))
				{
					_shootBallState = SHOOT_BALL_STATE.SEQUENCE_COMPLETE;
					DriverStation.reportError("Shoot Ball Sequence Completed", false);
				}
				break;
			
			case SEQUENCE_COMPLETE:
				_infeedSubsystem.RunInfeedAcquireMotor(0.0);
				_shooterSubsystem.RunShooterMotors(0.0);
				_isShootBallStillRunning = false;
				_shootBallState = SHOOT_BALL_STATE.UNDEFINED;
				break;
		}
	}
	
	// Property Accessors Follow (these DO NOT change the state of the robot)
	
	// TODO: Sebas: I do not understand what the following methods do???
	
	//public void setIsShootBallStillRunning(boolean bool)
	//{
	//	 bool = _isShootBallStillRunning; 
	//}
	
	//public void setShootBallSequenceStartTime(long startTime)
	//{
	//	startTime = _shootBallSequenceStartTime;
	//}
	
	//public void setShootBallSequenceState(SHOOT_BALL_STATE shootBallState)
	//{
	//	shootBallState = _shootBallState;
	//}
	
	public boolean getIsShootBallStillRunning()
	{
		return _isShootBallStillRunning;
	}
}
