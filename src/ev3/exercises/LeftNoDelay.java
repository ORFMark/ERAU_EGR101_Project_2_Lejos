package ev3.exercises;
 import ev3.liabraries.*;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
public class LeftNoDelay {
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
        System.out.println("Turn 1");
		drive.turn(constant.turn1,false,false,false);
		drive.motorReset();
		drive.straight(constant.strightSpeed1, true);
		while (!isTouched(touchSP)) {System.out.println("Straight 1"+isTouched(touchSP));}
		drive.motorReset();
		System.out.println("Back up");
		drive.motorA.forward();
		drive.motorB.forward();
		drive.motorA.rotate(75,true);
		drive.motorB.rotate(75,false);
		drive.motorReset();
		System.out.println("Turn 2");
		drive.turn(constant.turnSpeed2, true, true, false);
		while (light.getRed() >= constant.lightThresh){}
		drive.motorReset();
		System.out.println("Straight 2");
		drive.straight(constant.straight2,false);
		drive.motorReset();
		System.out.println("Straight 6");
		drive.straight(constant.straight6, false);
		drive.motorReset();
		System.out.println("Turn 3");
		drive.turn(constant.turn3, true, false, false);
		drive.motorReset();
		System.out.println("Straight 3/4");
		drive.straight(constant.straight3, false);
		drive.motorReset();
		drive.straight(constant.straight4, false);
		drive.motorReset();
		System.out.println("Turn 4");
		drive.turn(constant.turn4, false, false, true);
		drive.motorReset();
		System.out.println("straight 5");
		drive.straight(constant.straight5, false);
		drive.end();
		sensor1.close();
		light.close();
	}

}
