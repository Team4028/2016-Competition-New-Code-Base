package org.usfirst.frc.team4028.robot.autonRoutines;

import edu.wpi.first.wpilibj.DriverStation;

public class DoNothingAuton {

	public DoNothingAuton()
	{
		DriverStation.reportError("Hello! I am not doing anything...", false);
	}
	
	public void Execute()
	{
	}
}
