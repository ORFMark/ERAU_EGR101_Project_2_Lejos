package ev3.exercises;
 import ev3.liabraries.*;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
public class RightNoDelay {
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
		System.out.println("Rightside\n Without Delay\n");
        System.out.println("Press any key to start");
       
        Button.LEDPattern(4);    // flash green led and 
        Sound.beepSequenceUp();  // make sound when ready.
        Button.waitForAnyPress();
        drive.motorReset();
		drive.turn(constant.turn1,false,false,false);
		drive.motorReset();
		drive.straight(constant.strightSpeed1, true);
		while (!isTouched(touchSP)) {}
		drive.motorReset();
		drive.turn(constant.turnSpeed2, false, true, true);
		while (light.getRed() >= constant.lightThresh){}
		drive.motorReset();
		drive.straight(constant.straight2, false);
		drive.motorReset();
		drive.turn(constant.turn3, false, false, true);
		drive.motorReset();
		drive.straight(constant.straight3, false);
		drive.motorReset();
		drive.straight(constant.straight4, false);
		drive.motorReset();
		drive.turn(constant.turn4, false, false, true);
		drive.motorReset();
		drive.straight(constant.straight5, false);
	}

}
