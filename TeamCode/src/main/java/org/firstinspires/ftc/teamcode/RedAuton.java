package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


import static org.firstinspires.ftc.teamcode.Spark.Drivetrain.MECHANUM;


@Autonomous(name="RedAuton", group="Template")
@Disabled 

public class RedAuton extends LinearOpMode {
    private Spark robot;
    private Wayfinder finder;
    private ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() {
        robot = new Spark(this, MECHANUM);
        telemetry.addData("Status", "Initialized");
        runtime.reset();
        telemetry.update();
        Wayfinder finder = new Wayfinder(this, Wayfinder.CameraPlacement.FRONT, robot);

        finder.initVuforia();

        waitForStart(); //Below this point is where you place the linear code for your autonomous.
        finder.run(); //Starts tracking targets, runs in background for duration of opmode
        //Any code that goes in this space is only run once, and after it is finished the program ends.



        int restTicks = 250; //(rest for 250 milliseconds)
        int degTicks = 650; //(45 degree turn)
        int cmTicks = 20; //(1 cm)
            

        
        //robot code for top left and bottom right
        robot.moveLeftFT(cmTicks*60, 0.5);
        sleep(restTicks);
        robot.moveBackwardFT(cmTicks*90, 0.5);
        sleep(restTicks);
        robot.turnLeftFT(degTicks*2, 0.5);
        sleep(restTicks);
        robot.armUpFT(180, 0.5);
       
        for (int b = 0; b < 2; b++) {
               
            robot.moveRightFT(cmTicks*90, 0.5);       
            sleep(restTicks);
            robot.turnRightFT(degTicks, 0.5);
            sleep(restTicks);
            //robot.clawServo(0);

            robot.turnRightFT(degTicks*3, 0.5);
            sleep(restTicks);
            robot.moveRightFT(cmTicks*90, 0.5);
            sleep(restTicks);
            robot.armUpFT(145, 0.5);
            sleep(restTicks);
            //robot.clawServo(180);
        
            robot.moveLeftFT(cmTicks*90, 0.5);
            sleep(restTicks);
            robot.turnLeftFT(degTicks*3, 0.5);

            //robot.clawServo(180);

            robot.turnLeftFT(degTicks, 0.5);
            sleep(restTicks);
            robot.moveLeftFT(cmTicks*90, 0.5);  

            robot.armUpFT(180, 0.5);
    


            //Inside of the while statement below is any code that you want to run in loop during autonomous.
            while (opModeIsActive() && runtime.milliseconds() < 30000) {

            }
        }
    }
}
