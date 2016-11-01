package org.usfirst.frc.team4028.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;

/**
 * This class defines the behavior of the And1 Subsystem
 */
public class And1 
{
	// ======================================
	// Define constants & enums
	// ======================================	
	private static final double AND1_UP_POSITION = 1.0;
	private static final double AND1_DEPLOY_POSITION = 0.0;
	
	// ======================================
	// Define objects that reference the physical robot objects
	// ======================================	
	private Servo _and1Servo;
	
	// ======================================
	// Define working variables 
	// ======================================
	
	// ===================================
	// Constructors follow
	// ===================================
	public And1 (int servoPWMPort)
	{
		_and1Servo = new Servo(servoPWMPort);
	}
	
	//============================================================================================
	// Methods follow (methods make the robot do something (ie: push changes to the robot hardware)
	//============================================================================================
	public void StoreAnd1()
	{
		_and1Servo.setPosition(AND1_UP_POSITION);
	}
	
	public void DeployAnd1()
	{
		_and1Servo.setPosition(AND1_DEPLOY_POSITION);
	}
	
	//============================================================================================
	// Property Accessors follow (properties only change internal state) (ie: DO NOT push changes to the robot hardware)
	//============================================================================================
}

