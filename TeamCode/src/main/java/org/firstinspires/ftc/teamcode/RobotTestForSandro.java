package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.Spark.Drivetrain.MECHANUM;


@Autonomous(name="RobotTestForSandro", group="Template")
@Disabled

public class Auton extends LinearOpMode {
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
        
        
        robot.armServo.setPosition(0.50);
        sleep(100);
        robot.armServo.setPosition(0);
        
       // Example:
        // robot.moveForwardFT(1000, 0.5);

    

        //Inside of the while statement below is any code that you want to run in loop during autonomous.
        while (opModeIsActive() && runtime.milliseconds() < 30000) {
            robot.clawServo.setPosition(1);


    }
}
        
       // // // // //  //ryan's play area


            if (gamepad1.a) {
                robot.clawServo.setPosition(1);

            if (gamepad1.x);
                robot.clawServo.setPosition(0);
            }
        
    

