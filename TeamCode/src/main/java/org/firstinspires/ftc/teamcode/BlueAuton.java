package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


import static org.firstinspires.ftc.teamcode.Spark.Drivetrain.MECHANUM;

import android.speech.RecognitionService;

//import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;
//import javax.swing.text.html.parser.ContentModel;
//import javax.xml.catalog.GroupEntry.ResolveType;


@Autonomous(name="BlueAuton", group="Template")
//@Disabled

public class BlueAuton extends LinearOpMode {
    private Spark robot;
    private Tracker tracker;
    private ElapsedTime runtime = new ElapsedTime();
    private String originalDetected = "0";
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


        int restTicks = 100; //sleep for 200 milliseconds
        int cmTicks = 19; //1 cm
        int degTicks = 466; //45 degree turn
        int secondsToWaitForSignal = 5; //Note, the max in Tracker is 10 seconds.

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
        originalDetected = tracker.signalDetected;



        //Code here to do cones

        robot.armLoad();
        sleep(restTicks);
        robot.armPrimed();
        robot.moveLeftFT(cmTicks * 9/2, 0.25);
        robot.moveBackwardFT(cmTicks * 83,0.75);
        robot.moveForwardFT(cmTicks * 20,0.5);

        //robot moves in position to place a cone
        robot.armMediumTele();
        robot.turnRightFT(degTicks * 77/25,0.5);
        robot.armMedium();
        robot.moveForwardFT(cmTicks * 2394/100,0.5);
        robot.armUpFT(90, 0.5);
        sleep(restTicks);

        //robot places a cone on a medium junction
        sleep(restTicks * 10);
        robot.servoOpen();
        sleep(restTicks);
        robot.armUpFT(280, 0.5);
        robot.moveBackwardFT(cmTicks * 25,0.5);
        sleep(restTicks);

        robot.turnLeftFT(degTicks * 21/20, 0.5);
        sleep(restTicks);
        robot.moveBackwardFT(cmTicks * 9/2, 0.5);

        /*
        sleep(restTicks);
        robot.armPrimed();
        robot.turnRightFT(degTicks * 31/10,0.5);
        sleep(restTicks);

        //robot moves in line with the cone stack
        robot.moveLeftFT(cmTicks * 68,0.5);
        sleep(restTicks);
        robot.moveRightFT(cmTicks * 10,0.5);
        robot.armUpFT(500, 0.5);

        //robot moves into the cone stack and gets a cone
        robot.moveForwardFT(cmTicks * 67,0.4);
        sleep(restTicks);
        robot.armDownFT(680, 0.5); //Goes in the cone
        sleep(restTicks);
        robot.servoClose();
        sleep(restTicks);
        robot.armUpFT(680,0.5);

        //robot moves to low junction and drops cone
        robot.moveBackwardFT(cmTicks * 60,0.5);
        robot.armLowTele();
        robot.turnRightFT(degTicks,0.5);
        robot.armLow();
        sleep(restTicks);
        robot.moveForwardFT(cmTicks * 5,0.5);
        robot.servoOpen();
        sleep(restTicks);
        robot.moveBackwardFT(cmTicks * 5,0.5);


        //temp
        robot.armPrimed();
        robot.turnLeftFT(degTicks * 2, 0.5);
        sleep(restTicks);
        robot.moveForwardFT(cmTicks * 30, 0.5);
        robot.armDownFT(725, 0.5); //Goes in the cone
        sleep(restTicks);
        robot.servoClose();
        sleep(restTicks);
        robot.armUpFT(200,0.5);
        robot.moveBackwardFT(cmTicks * 60,0.5);
        sleep(restTicks);
        robot.turnLeftFT(degTicks * 2, 0.5);
        robot.armHighTele();
        sleep(restTicks);
        robot.moveLeftFT(cmTicks * 30, 0.5);
        robot.armHigh();
        robot.moveForwardFT(cmTicks * 5, 0.5);
        sleep(restTicks);
        robot.servoOpen();
        sleep(restTicks);
        robot.moveBackwardFT(cmTicks * 5, 0.5);
        sleep(restTicks);
        robot.armPrimed();

        */


        switch (originalDetected){
            case "Circle":
                telemetry.addLine("Bolt running");
                telemetry.update();
                //Code here for circle movement

                sleep(restTicks);
                robot.moveForwardFT(cmTicks * 60, 0.5);




                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
                break;

            case "Triangle":
                telemetry.addLine("Bulb running");
                telemetry.update();
                //Code here for triangle movement
                robot.moveLeftFT(cmTicks * 6, 0.5);


                break;

            case "Square":
                telemetry.addLine("Panel running");
                telemetry.update();
                //Code here for square movement
                sleep(restTicks);
                robot.moveLeftFT(cmTicks * 5, 0.5);
                robot.moveBackwardFT(cmTicks * 60, 0.5);


                break;
            default:
                telemetry.addLine("No signal detected running");
                telemetry.update();
                sleep(restTicks);
                robot.moveForwardFT(cmTicks * 60, 0.5);


        }

        //Inside of the while statement below is any code that you want to run in loop during autonomous.
        while (opModeIsActive() && runtime.milliseconds() < 30000) {

        }
    }
}

