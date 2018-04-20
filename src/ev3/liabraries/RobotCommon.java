package ev3.liabraries;

import lejos.hardware.Sound;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class RobotCommon {

	public RobotCommon()
	{

	}
	public EV3LargeRegulatedMotor motorA = new EV3LargeRegulatedMotor(MotorPort.A);
	public EV3LargeRegulatedMotor motorB = new EV3LargeRegulatedMotor(MotorPort.B);

	public  void motorReset()
	{
		motorA.stop();
		motorB.stop();
		motorA.resetTachoCount();
		motorB.resetTachoCount();
		motorA.setSpeed(310);
		motorB.setSpeed(300);
		Sound.beepSequenceUp();
		Delay.msDelay(250);
	}
	public void straight(int value, boolean speed)
	{
		if(speed==true)
		{
			
			motorA.backward();
			motorB.backward();
			motorA.setSpeed(value);
			motorB.setSpeed(value);
		}
		if(speed==false)
		{
			motorA.backward();
			motorB.backward();
			motorA.rotate(value, true);
			motorB.rotate(value, false);
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
					motorA.backward();
					motorB.backward();
					motorA.setSpeed(value);
				}
				if(speed==false)
				{
					motorA.backward();
					motorB.backward();
					motorA.rotate(value, false);
				}
			}
			if (point==true)
			{
				if(speed==true)
				{
					motorA.backward();
					motorB.forward();
					motorA.setSpeed(value);
					motorB.setSpeed(value);
				}
				if(speed==false)
				{
					motorA.backward();
					motorB.forward();
					motorA.rotate(value, true);
					motorB.rotate(value,false);
				}
			}
		}
		if(left==false)
		{
			if (point==false)
			{
				if(speed==true)
				{
					motorA.backward();
					motorB.backward();
					motorB.setSpeed(value);
				}
				if(speed==false)
				{
					motorA.backward();
					motorB.backward();
					motorB.rotate(value, false);
				}
			}
			if (point==true)
			{
				if(speed==true)
				{
					motorB.backward();
					motorA.forward();
					motorA.setSpeed(value);
					motorB.setSpeed(value);
				}
				if(speed==false)
				{
					motorB.backward();
					motorA.forward();
					motorA.rotate(value, true);
					motorB.rotate(value,false);
				}
			}
		}
	}
	public void linefollow(float threshhold,int duration, ColorSensor li)
	{
		int count=0;
		motorA.backward();
		motorB.backward();
		while(count<duration)
		{
			if (li.getRed()<=threshhold)
				motorB.setSpeed(0);
				motorA.setSpeed(200);
			if (li.getRed()>=threshhold)
			{
				motorB.setSpeed(200);
				motorA.setSpeed(0);
			}
			count++;
		}
	}
	public void end()
	{
		motorA.close();
		motorB.close();
	}
}
