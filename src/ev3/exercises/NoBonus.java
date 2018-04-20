package ev3.exercises;
 import ev3.liabraries.*;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
public class NoBonus {
	 private static boolean isTouched(SampleProvider sp)
	   {
	       float [] sample = new float[sp.sampleSize()];
	    
	       sp.fetchSample(sample, 0);

	       if (sample[0] == 0)
	           return false;
	       else
	           return true;
	   }
	public static void main(String[] args) {
		Constants constant = new Constants();
		RobotCommon drive= new RobotCommon();
		EV3TouchSensor sensor1 = new EV3TouchSensor(SensorPort.S1);
        SampleProvider touchSP = sensor1.getTouchMode();
        ColorSensor light= new ColorSensor(SensorPort.S2);
        light.setRedMode();
        light.setFloodLight(Color.RED);
        light.setFloodLight(true);
		drive.motorReset();
		System.out.println("Leftside\n Without Delay\n");
        System.out.println("Press any key to start");
       
        Button.LEDPattern(4);    // flash green led and 
        Sound.beepSequenceUp();  // make sound when ready.
        Button.waitForAnyPress();
        drive.motorReset();
        Delay.msDelay(5000);
        System.out.println("Line Up to Wallrun");
		drive.turn(constant.turn1,false,false,false);
		drive.motorReset();
		drive.motorA.backward();
		drive.motorB.backward();
		drive.motorA.rotate(3000,true);
		drive.motorB.rotate(3000,false);
		drive.motorReset();/*
		drive.motorA.forward();
		drive.motorB.backward();
		drive.motorA.rotate(100,true);
		drive.motorB.rotate(100,false);
		drive.motorReset(); */
		System.out.println("Back up");
		drive.motorA.forward();
		drive.motorB.forward();
		drive.motorA.rotate(80,true);
		drive.motorB.rotate(80,false);
		drive.motorReset();
		System.out.println("Turn towards hole");
		drive.motorA.forward();
		drive.motorB.backward();
		drive.motorA.setSpeed(200);
		drive.motorB.setSpeed(200);
		while (light.getRed() >= constant.lightThresh){System.out.println(light.getRed());}
		drive.motorReset();
		System.out.println("Deposit in hole");
		drive.straight(constant.straight2,false);
		drive.motorReset();
		System.out.println("Ensure they are there");
		drive.straight(constant.straight6, false);
		drive.motorReset();
		System.out.println("Align towards main area");
		drive.motorA.forward();
		drive.motorB.backward();
		drive.motorA.rotate(300,true);
		drive.motorB.rotate(300,false);
		drive.motorReset();
		System.out.println("return to start");
		drive.straight(constant.straight5, false);
		drive.end();
		sensor1.close();
		light.close();
		System.out.println("Done");
		Button.waitForAnyPress();
	}

}
