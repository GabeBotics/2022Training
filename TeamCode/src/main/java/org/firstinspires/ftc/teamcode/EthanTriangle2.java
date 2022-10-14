package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Spark.Drivetrain.MECHANUM;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="Auton", group="Template")
@Disabled

public class EthanTriangle2 extends LinearOpMode {
    private Spark robot;
    private Wayfinder finder;
    private ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() {
        robot = new Spark(this, MECHANUM);
        telemetry.addData("Status", "Initialized");
        runtime.reset();
        telemetry.update();
        //Code Above the waitForStart() is where you define variables or initialize any Vuforia
        //DO NOT PUT MOVEMENT CODE HERE - YOU WILL BE PENALIZED
        Wayfinder finder = new Wayfinder(this, Wayfinder.CameraPlacement.FRONT, robot);

        finder.initVuforia();

        waitForStart(); //Below this point is where you place the linear code for your autonomous.
        finder.run(); //Starts tracking targets, runs in background for duration of opmode
        //Any code that goes in this space is only run once, and after it is finished the program ends.

       // Example:
        // robot.moveForwardFT(1000, 0.5);

        sleep(200);
        robot.turnRightFT(600, 0.5);
        sleep(200);
        robot.moveForwardFT(500, 0.5);
        sleep(200);
        robot.turnLeftFT(600, 0.5);
        sleep(200);
        robot.moveBackwardFT(500, 0.5);
        sleep(200);
        robot.turnLeftFT(600, 0.5);
        sleep(200);
        robot.moveForwardFT(500, .5);
        sleep(200);



        //Inside of the while statement below is any code that you want to run in loop during autonomous.
        while (opModeIsActive() && runtime.milliseconds() < 30000) {


        }


    }
}
