package ev3.liabraries;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class RobotCommon {

	public RobotCommon()
	{

	}
	static EV3LargeRegulatedMotor motorA = new EV3LargeRegulatedMotor(MotorPort.A);
	static EV3LargeRegulatedMotor motorB = new EV3LargeRegulatedMotor(MotorPort.B);

	public  void motorReset()
	{
		motorA.stop();
		motorB.stop();
		motorA.resetTachoCount();
		motorB.resetTachoCount();
	}
	public void straight(int value, boolean speed)
	{
		if(speed==true)
		{
			motorA.setSpeed(value);
			motorB.setSpeed(value);
			motorA.backward();
			motorB.backward();
		}
		if(speed==false)
		{
			motorA.backward();
			motorB.backward();
			motorA.rotate(value, true);
			motorB.rotate(value, true);
		}
	}
	public  void turn(int value, boolean left, boolean speed, boolean point)
	{
		if (left==true)
		{
			if (point==false)
			{
				if(speed==true)
				{
					motorA.setSpeed(value);
					motorA.backward();
					motorB.backward();
				}
				if(speed==false)
				{
					motorA.backward();
					motorB.backward();
					motorA.rotate(value, true);
				}
			}
			if (point==true)
			{
				if(speed==true)
				{
					motorA.setSpeed(value);
					motorB.setSpeed(value);
					motorA.backward();
					motorB.forward();
				}
				if(speed==false)
				{
					motorA.backward();
					motorB.forward();
					motorA.rotate(value, true);
					motorB.rotate(value,true);
				}
			}
		}
		if(left==false)
		{
			if (point==false)
			{
				if(speed==true)
				{
					motorB.setSpeed(value);
					motorA.backward();
					motorB.backward();
				}
				if(speed==false)
				{
					motorA.backward();
					motorB.backward();
					motorB.rotate(value, true);
				}
			}
			if (point==true)
			{
				if(speed==true)
				{
					motorA.setSpeed(value);
					motorB.setSpeed(value);
					motorB.backward();
					motorA.forward();
				}
				if(speed==false)
				{
					motorB.backward();
					motorA.forward();
					motorA.rotate(value, true);
					motorB.rotate(value,true);
				}
			}
		}
	}
}
