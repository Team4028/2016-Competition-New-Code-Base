package org.usfirst.frc.team4028.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;

/**
 * This class defines the behavior of the Puma Subsystem
 */
public class Cupid 
{
	// ======================================
	// Define constants & enums
	// ======================================		
	private static final double CUPID_LOADED_POSITION = 1.0;
	private static final double CUPID_FIRE_POSITION = 0.0;
	
	// ======================================
	// Define object that reference the physical robot objects
	// ======================================	
	private Servo _cupidServo;
	
	// ======================================
	// Define working variables 
	// ======================================
	private double _cupidPosition;
	
	// ===================================
	// Constructors follow
	// ===================================
	public Cupid(int cupidPWMPort)
	{
		_cupidServo = new Servo(cupidPWMPort);
	}
	
	//============================================================================================
	// Methods follow (methods make the robot do something (ie: push changes to the robot hardware)
	//============================================================================================
	public void LoadCupid()
	{
		_cupidPosition = CUPID_LOADED_POSITION;
		_cupidServo.setPosition(_cupidPosition);
	}
	
	public void FireCupid()
	{
		_cupidPosition = CUPID_FIRE_POSITION;
		_cupidServo.setPosition(_cupidPosition);
	}
	
	public void ToggleCupidPosition()
	{
		if(_cupidPosition == CUPID_FIRE_POSITION)
		{
			LoadCupid();
		}
		else
		{
			FireCupid();
		}
	}
	
	//============================================================================================
	// Property Accessors follow (properties only change internal state) (ie: DO NOT push changes to the robot hardware)
	//============================================================================================

	

	

	

	

}