package ev3.exercises;
import lejos.hardware.*;

public class HelloWorld
{
    public static void main(String[] args)
    {
        System.out.println("Hello World!!");

        Button.waitForAnyPress();
    }
}