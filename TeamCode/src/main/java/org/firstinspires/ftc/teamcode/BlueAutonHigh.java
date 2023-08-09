package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Spark.Drivetrain.MECHANUM;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

//import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;
//import javax.swing.text.html.parser.ContentModel;
//import javax.xml.catalog.GroupEntry.ResolveType;


@Autonomous(name="BlueAutonHigh", group="Template")
//@Disabled

public class BlueAutonHigh extends LinearOpMode {
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
        robot.armSet();
        robot.moveBackwardFT(cmTicks * 5, 0.5);
        sleep(restTicks);
        robot.moveRightFT(cmTicks * 48,0.5);
        sleep(restTicks);
        robot.moveBackwardFT(cmTicks * 55, 0.5);
        sleep(restTicks);
        robot.armHighTele();
        robot.turnRightFT(degTicks * 3, 0.5);
        sleep(restTicks);
        robot.armHigh();
        sleep(restTicks);
        robot.moveForwardFT(cmTicks * 35, 0.5);
        sleep(restTicks);
        robot.servoOpen();
        sleep(restTicks * 2);
        robot.moveBackwardFT(cmTicks * 35, 0.5);
        sleep(restTicks);
        robot.turnLeftFT(degTicks, 0.5);
        sleep(restTicks);

        //parking

        switch (originalDetected){
            case "Circle":
                telemetry.addLine("Bolt running");
                telemetry.update();
                //Code here for circle movement
                sleep(restTicks);
                //robot.moveForwardFT(cmTicks * 60, 0.5);
                robot.turnLeftFT(degTicks * 2, 0.5);

                break;

            case "Triangle":
                telemetry.addLine("Bulb running");
                telemetry.update();
                //Code here for triangle movement
                robot.moveBackwardFT(cmTicks * 51, 0.5);

                break;

            case "Square":
                telemetry.addLine("Panel running");
                telemetry.update();
                //Code here for square movement
                sleep(restTicks);
                robot.moveBackwardFT(cmTicks * 105, 0.5);

                break;
            default:
                telemetry.addLine("No signal detected running");
                telemetry.update();
                sleep(restTicks);
                //robot.moveForwardFT(cmTicks * 60, 0.5);
                robot.turnLeftFT(degTicks * 2, 0.5);


        }

        //Inside of the while statement below is any code that you want to run in loop during autonomous.
        while (opModeIsActive() && runtime.milliseconds() < 30000) {

        }
    }
}

