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
@Disabled 

public class BlueAuton extends LinearOpMode {
    private Spark robot;
    private ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() {
        robot = new Spark(this, MECHANUM);
        telemetry.addData("Status", "Initialized");
        runtime.reset();
        telemetry.update();

        waitForStart(); //Below this point is where you place the linear code for your autonomous.
        //Any code that goes in this space is only run once, and after it is finished the program ends.


        int restTicks = 250; //sleep for 250 milliseconds
        int cmTicks = 20; //1 cm
        int degTicks = 650; //45 degree turn

        //robot code for bottom left and top right   
   
        //place the robot so that the camera is on the opposite side of the robot from the side closest to the 
        //wall
        robot.moveRightFT(cmTicks * 60, 0.5);
        sleep(restTicks);
        robot.moveBackwardFT(cmTicks * 90, 0.5);
        sleep(restTicks);
        robot.turnRightFT(degTicks, 0.5);
        sleep(restTicks);
        //robot moves its arm to high junction
        robot.armUpFT(180, 0.5);
        //open claw to deposit cone
        robot.clawServo.setPosition(0);

        for (int a = 0; a < 2; a++) {

            //claw open
            robot.clawServo.setPosition(0);

            //robot moves to get cone
            robot.moveLeftFT(cmTicks * 90, 0.5);
            sleep(restTicks);
            robot.turnLeftFT(degTicks, 0.5);
            sleep(restTicks);
            
            //claw close to grab cone
            robot.clawServo.setPosition(1);

            robot.turnLeftFT(degTicks * 3, 0.5);
            sleep(restTicks);
            robot.moveRightFT(cmTicks * 90, 0.5);
            //robot move medium junction
            robot.armUpFT(145, 0.5);
            //claw open to release cone
            robot.clawServo.setPosition(0);

            robot.moveLeftFT(cmTicks * 90, 0.5);
            sleep(restTicks);
            robot.turnRightFT(degTicks * 3, 0.5);
            //claw open
            //robot.clawServo(0);

            robot.turnRightFT(degTicks * 3, 0.5);
            sleep(restTicks);
            robot.moveLeftFT(cmTicks * 90, 0.5);

            //high junction
            robot.armUpFT(180, 0.5);
            //claw close
            //robot.clawServo(180);
        }

        //Inside of the while statement below is any code that you want to run in loop during autonomous.
        while (opModeIsActive() && runtime.milliseconds() < 30000) {

        }
    }
}