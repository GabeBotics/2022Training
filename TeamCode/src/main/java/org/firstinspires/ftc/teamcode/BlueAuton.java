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
        int degTicks = 440
                ; //45 degree turn
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

        robot.servoClose();
        sleep(restTicks);
        robot.moveBackwardFT(cmTicks * 120, 0.5);
        sleep(restTicks);
        robot.turnRightFT(degTicks * 3, 0.5);
        sleep(restTicks);
        robot.armHigh();
        sleep(restTicks);
        robot.moveForwardFT(cmTicks * 25, 0.5);
        sleep(restTicks);
        robot.servoOpen();
        sleep(restTicks);
        robot.moveBackwardFT(cmTicks * 25, 0.5);
        sleep(restTicks);
        robot.armPrimed();
        sleep(restTicks);
        robot.turnRightFT(degTicks * 7/2, 0.5);
        sleep(restTicks);
        robot.moveForwardFT(cmTicks * 35, 0.5);
        sleep(restTicks);
        robot.armLoad();
        sleep(restTicks);
        robot.moveBackwardFT(cmTicks * 35, 0.5);
        sleep(restTicks);
        robot.turnLeftFT(degTicks * 7/2, 0.5);
        sleep(restTicks);
        robot.armHigh();
        sleep(restTicks);
        robot.moveForwardFT(cmTicks * 25, 0.5);
        sleep(restTicks);
        robot.servoOpen();
        sleep(restTicks);
        robot.moveBackwardFT(cmTicks * 25, 0.5);
        sleep(restTicks);
        robot.armPrimed();


        switch (originalDetected){
            case "Circle":
                telemetry.addLine("Bolt running");
                telemetry.update();
                //Code here for circle movement


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
                break;
                //Square is three dots
            case "Square":
                telemetry.addLine("Bulb running");
                telemetry.update();

                //Code here for square movement




                break;
                //Triangle is two dots
            case "Triangle" :
                telemetry.addLine("Panel running");
                telemetry.update();

                //Code here for triangle movement



                break;
            default:
                telemetry.addLine("No signal detected running");
                telemetry.update();

        }

        sleep(1000); //Unecessary, can delete
        //robot moves to high junction
        
        /*
        for (int a = 0; a < 2; a++) {
            
            //MEDIUM JUNCTION SECTION

                //robot moves to  cone
                robot.moveLeftFT(cmTicks * 90, 0.5);
                sleep(restTicks);
                robot.turnLeftFT(degTicks, 0.5);
                sleep(restTicks);
            
                //claw close to grab cone
                robot.servoClose();

                //robot moves to medium junction
                robot.turnLeftFT(degTicks * 3, 0.5);
                sleep(restTicks);
                robot.moveRightFT(cmTicks * 90, 0.5);
            
                //raises arm to medium junction height
                robot.armUpFT(145, 0.5);

                //claw open to release cone
                robot.servoOpen();


            //TALL JUNCTION SECTION
                //robot moves to cone
                robot.moveLeftFT(cmTicks * 90, 0.5);
                sleep(restTicks);
                robot.turnRightFT(degTicks * 3, 0.5);
                
                //claw close to get cone 
                robot.servoClose();
           
                //Robot moves to high junction
                robot.turnRightFT(degTicks * 3, 0.5);
                sleep(restTicks);
                robot.moveLeftFT(cmTicks * 90, 0.5);

                //arm goes to high junction height
                robot.armUpFT(180, 0.5);
                //claw open to deposit cone
                robot.servoOpen();
        }
        */
        
        //Park wherever initially indicated by the signal sleeve

        //Inside of the while statement below is any code that you want to run in loop during autonomous.
        while (opModeIsActive() && runtime.milliseconds() < 30000) {

        }
    }
}

//e