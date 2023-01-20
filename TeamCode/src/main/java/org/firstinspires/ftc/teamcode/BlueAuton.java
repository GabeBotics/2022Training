package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


import static org.firstinspires.ftc.teamcode.Spark.Drivetrain.MECHANUM; 

//import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;
//import javax.swing.text.html.parser.ContentModel;
//import javax.xml.catalog.GroupEntry.ResolveType;


@Autonomous(name="BlueAuton", group="Template")
//@Disabled

public class BlueAuton extends LinearOpMode {
    private Spark robot;
    private Tracker tracker;
    private ElapsedTime runtime = new ElapsedTime();
    String originalSignal = "0";
    @Override
    public void runOpMode() {
        robot = new Spark(this, MECHANUM);
        tracker = new Tracker(this, robot);
        telemetry.addData("Status", "Initialized");
        runtime.reset();
        telemetry.update();

        tracker.loadTracker();
        waitForStart(); //Below this point is where you place the linear code for your autonomous.
        //Any code that goes in this space is only run once, and after it is finished the program ends.


        int restTicks = 250; //sleep for 250 milliseconds
        int cmTicks = 20; //1 cm
        int degTicks = 1300; //90 degree turn
        int secondsToWaitForSignal = 10;

        //robot code for bottom left and top right   
        //place the robot so that the camera is on the opposite side of the robot from the side closest to the 
        //wall

        //PLACE IMAGE RECOGNITION CODE HERE
        tracker.run();

        while ((tracker.signalDetected == "0") && runtime.milliseconds() < secondsToWaitForSignal*1000){
            //Here you can move around, or whatever you want to do to try and detect the object. I suggest putting
            //a time limit so you can do the rest of the auton if you do not detect it.
            //If the signal is found, signalDetected will be the String object
        }
        originalSignal = tracker.signalDetected;

        //Code here to do cones
        sleep(restTicks);
        robot.moveRightFT(cmTicks * 60, 0.5);
        //robot moves to the right to position itself towards the tall junction.
        sleep(restTicks);
        robot.moveBackwardFT(cmTicks * 90, 0.5);
        //robot moves backward to arrive at the tall junction.
        sleep(restTicks);                                   
        robot.turnRightFT(degTicks, 0.5);
        //robot turns to face the tall junction.
        sleep(restTicks);
        robot.armUpFT(180, 0.5);
        //robot moves its arm to high junction.
        sleep(restTicks);
        robot.servoOpen();
        //open claw to deposit cone.
        sleep(restTicks);
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
        switch (originalSignal){
            case "1 Bolt":
                telemetry.addLine("Bolt running");
                telemetry.update();                
               
                robot.moveLeftFT(cmTicks * 30, 0.5);
                //robot moves a little left to park in the first position.
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
                break;
            case "2 Bulb":
                telemetry.addLine("Bulb running");
                telemetry.update();

                robot.moveLeftFT(cmTicks * 30, 0.5);
                //robot moves a little left for parking.
                sleep(restTicks);
                robot.moveBackwardFT(cmTicks * 60, 0.5);
                //robot moves backwards to park in the second position.

                break;
            case "3 Panel" :
                telemetry.addLine("Panel running");
                telemetry.update();

                robot.moveLeftFT(cmTicks * 30, 0.5);
                //robot moves a little left for parking.
                sleep(restTicks);
                robot.moveBackwardFT(cmTicks * 120, 0.5);
                //robot moves backwards to park in the third position.

                break;
            default:
                telemetry.addLine("No signal detected running");
                telemetry.update();

                robot.moveLeftFT(cmTicks * 30, 0.5);
                //robot moves a little left for parking.
                sleep(restTicks);
                robot.moveBackwardFT(cmTicks * 120, 0.5);
                //robot moves backwards to park in the third position.
                
                break;
        }

        sleep(1000); //Unecessary, can delete
        //robot moves to high junction
        
        
        //Park wherever initially indicated by the signal sleeve

        //Inside of the while statement below is any code that you want to run in loop during autonomous.
        while (opModeIsActive() && runtime.milliseconds() < 30000) {

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        

        }
    }
}
