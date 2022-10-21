package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


import static org.firstinspires.ftc.teamcode.Spark.Drivetrain.MECHANUM;


@Autonomous(name="Auton", group="Template")
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
        Wayfinder finder = new Wayfinder(this, Wayfinder.CameraPlacement.FRONT, robot);

        finder.initVuforia();

        waitForStart(); //Below this point is where you place the linear code for your autonomous.
        finder.run(); //Starts tracking targets, runs in background for duration of opmode
        //Any code that goes in this space is only run once, and after it is finished the program ends.



        int R = 250; //(rest for 250 milliseconds)
        int T = 1300; //(90 degree turn)
        int M = 20; //(1 cm)
            

        
        //robot code for top left and bottom right
        robot.moveLeftFT(M*60, 0.5);
        robot.rest(R);
        robot.moveBackwardFT(M*90, 0.5);
        robot.rest(R);
        robot.turnLeftFT(T*3);
        robot.rest(R);
        //robot places a cone on the high junction   
       
        for (int b = 0; b < 2; b++) {
               
            robot.moveRightFT(M*90);       
            robot.rest(R);
            robot.turnRightFT(T/2);
            
            //robot picks up cone

            robot.turnRightFT(T*1.5);
            robot.rest(R);
            robot.moveRightFT(M*90);

            //robot places cone on medium junction
        
            robot.moveLeftFT(M*90);
            robot.rest(R);
            robot.turnLeftFT(T*1.5);

            //robot picks up cone

            robot.turnLeftFT(T/2);
            robot.rest(R);
            robot.moveLeftFT(M*90);  

            //robot places cone on high junction
    


            //Inside of the while statement below is any code that you want to run in loop during autonomous.
            while (opModeIsActive() && runtime.milliseconds() < 30000) {

         
    

            }
        }
    }
}
