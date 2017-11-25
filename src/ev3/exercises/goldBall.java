package ev3.exercises;


import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.robotics.Color;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;

import java.util.Timer;

import ev3.liabraries.*;


public class goldBall 
{ 
	 static EV3LargeRegulatedMotor motorA = new EV3LargeRegulatedMotor(MotorPort.A);
     static EV3LargeRegulatedMotor motorB = new EV3LargeRegulatedMotor(MotorPort.B);
	public static void motorReset()
	{
		motorA.stop();
		motorB.stop();
		motorA.resetTachoCount();
		motorB.resetTachoCount();
	}
    public static void main(String[] args)
    {
    	Constants constant =new Constants();
        EV3TouchSensor sensor1 = new EV3TouchSensor(SensorPort.S1);
        SampleProvider touchSP = sensor1.getTouchMode();
        ColorSensor light= new ColorSensor(SensorPort.S2);
        light.setRedMode();
        light.setFloodLight(Color.RED);
        light.setFloodLight(true);
        System.out.println("Rightside\n No Delay\n");
        System.out.println("Press any key to start");
       
        Button.LEDPattern(4);    // flash green led and 
        Sound.beepSequenceUp();  // make sound when ready.
     
        Button.waitForAnyPress();
   
       // create two motor objects to control the motors.
        EV3LargeRegulatedMotor motorA = new EV3LargeRegulatedMotor(MotorPort.A);
        EV3LargeRegulatedMotor motorB = new EV3LargeRegulatedMotor(MotorPort.B);
        

       // set motors to different power levels. Adjust to get a circle.
        motorA.backward();
        motorB.backward();
        motorB.rotate(constant.turn1);
        motorReset();
        motorA.setSpeed(constant.strightSpeed1);
        motorB.setSpeed(constant.strightSpeed1);
        motorA.backward();
        motorB.backward();
       // wait doing nothing for touch sensor to stop driving.
       while (!isTouched(touchSP)) {}

       // stop motors with brakes on.
       motorA.stop();
       motorB.stop();
       Sound.beepSequenceUp();
       motorA.setSpeed(constant.turnSpeed2);
       motorB.setSpeed(constant.turnSpeed2);
       motorA.backward();
       motorB.forward();
       while (light.getRed() >= constant.lightThresh){}
       motorReset();
       Sound.beepSequenceUp();
       motorA.forward();
       motorB.forward();
       motorA.rotate(constant.straight2,true);
       motorB.rotate(constant.straight2,true);
       Sound.beepSequenceUp();
       motorReset();
       motorA.forward();
       motorB.backward();
       motorA.rotate(constant.turn3,true);
       motorB.rotate(constant.turn3,true);
       motorReset();
       motorA.backward();
       
       
       
       // free up resources.
       motorA.close();
       motorB.close();
       sensor1.close();

       Sound.beepSequence(); // we are done.
   }
   
   // method to read touch sensor and return true or false if touched.
   private static boolean isTouched(SampleProvider sp)
   {
       float [] sample = new float[sp.sampleSize()];
    
       sp.fetchSample(sample, 0);

       if (sample[0] == 0)
           return false;
       else
           return true;
   }
 }
