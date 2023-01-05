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
        //place the robot so that the camera is on the opposite side of the robot from the side closest to the 
        //wall

        //PLACE IMAGE RECOGNITION CODE HERE

        //robot moves to high junction
        robot.moveLeftFT(cmTicks*60, 0.5);
        sleep(restTicks);
        robot.moveBackwardFT(cmTicks*90, 0.5);
        sleep(restTicks);
        robot.turnLeftFT(degTicks*2, 0.5);
        sleep(restTicks);

        //arm goes up to high junction height
        robot.armUpFT(180, 0.5);

        //open claw to deposit cone
        robot.servoClose();
       
        for (int b = 0; b < 2; b++) {
               
            //MEDIUM JUNCTION SECTION

                //robot moves to  cone
                robot.moveRightFT(cmTicks*90, 0.5);       
                sleep(restTicks);
                robot.turnRightFT(degTicks, 0.5);
                sleep(restTicks);

                //claw close to grab cone
                robot.servoClose();

                //robot moves to medium junction
                robot.turnRightFT(degTicks*3, 0.5);
                sleep(restTicks);
                robot.moveRightFT(cmTicks*90, 0.5);
                sleep(restTicks);

                //arm raises to medium junction height
                robot.armUpFT(145, 0.5);
                sleep(restTicks);

                //claw servo opens to deposit cone
                robot.servoOpen();
            
            //TALL JUNCTION SECTION
                //robot moves to cone
                robot.moveLeftFT(cmTicks*90, 0.5);
                sleep(restTicks);
                robot.turnLeftFT(degTicks*3, 0.5);

                //claw close to get cone 
                robot.servoClose();

                //robot moves to high junction
                robot.turnLeftFT(degTicks, 0.5);
                sleep(restTicks);
                robot.moveLeftFT(cmTicks*90, 0.5);  

                //arm goes to high junction height
                robot.armUpFT(180, 0.5);
                //claw open to deposit cone
                robot.servoOpen();
        }

        //Park wherever initially indicated by the signal sleeve

        //Inside of the while statement below is any code that you want to run in loop during autonomous.
        while (opModeIsActive() && runtime.milliseconds() < 30000) {

        }
    }
}
