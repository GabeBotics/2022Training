package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Tele", group="Template")
//@Disabled
public class Tele extends OpMode {

    private Spark robot;

    @Override
    public void init() {

        //This code initializes the drivetrain. Make sure that you have the right drivetrain selected!
        robot = new Spark(this, Spark.Drivetrain.TANK);
    }

    @Override
    public void loop() {
        //MOVEMENT
        //First, we want to make the robot rest if the gamepad is not being touched


        //If the gamepad is NOT at rest, then we want to see what we need to do.
        if (gamepad1.atRest()) robot.rest();
        else {
            //If the gamepad is NOT at rest, then we want to see what we need to do.
            //GAMEPAD 1 CODE
            robot.mechanumMovT(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);
                
            double leftY = gamepad1.left_stick_y;
            double leftX = gamepad1.left_stick_x;
            double rightY = gamepad1.right_stick_y;
            double rightX = gamepad1.right_stick_x;
            
            
            //If left stick is held to the left, move left.
            if (gamepad1.left_stick_x < -0.2) {
                 robot.moveLeft(Math.abs(leftX));
            }
            //If left stick is held to the right, move right.
            if (gamepad1.left_stick_x > 0.2) {
                robot.moveRight(Math.abs(leftX));            
            }
            //If left stick is held forward, move forward.
            if (gamepad1.left_stick_y > 0.2) {
                robot.moveForward(Math.abs(leftY));
            }
            //If left stick is held back, move backward.
            if (gamepad1.left_stick_y < -0.2) {
                robot.moveBackward(Math.abs(leftY));
            }
            //if right stick is held left, turn left.
            if (gamepad1.right_stick_x < -0.2) {
                robot.turnLeft(Math.abs(rightX));
            }
            //if right stick is held right, turn right.
            if (gamepad1.right_stick_x > 0.2) {
                robot.turnRight(Math.abs(rightX));
            }
            
            //button mapping for quick turning
            if (gamepad1.x) {
                robot.turnRightFT(1300,0.5);
            }
            if (gamepad1.a) {
                robot.turnRightFT(2600,0.5);
            }
            if (gamepad1.b) {
                robot.turnLeftFT(1300,0.5);
            }
            if (gamepad1.y) {
                robot.turnLeftFT(2600,0.5);
            }

            //GAMEPAD 2 CODE
            double leftY2 = gamepad2.left_stick_y;
            double rightY2 = gamepad2.right_stick_y;
            double leftX2 = gamepad2.left_stick_x;
            double rightX2 = gamepad2.right_stick_x;

            // the arm moves up
            if (gamepad2.left_stick_y > 0.2) {
              robot.armMotor(leftY2);
            }
            
            // the arm moves down
            if (gamepad2.left_stick_y < -0.2) {
                robot.armMotor(leftY2);
            }

            
            // the claw opens

            if (gamepad2.left_trigger > 0) {
                robot.clawServo(0);
            }


            // the claw closes

            if (gamepad2.right_trigger > 0) {
                robot.clawServo(180);
            }
           //reset 
            if (gamepad2.x) {
           //  armMotor();//reset
            }

            //tall junction preset
            if (gamepad2.y) {
               // armMotor();//however much it needs to go to preset tall junction
            }
            
            //medium junction preset
            if (gamepad2.b) {
               // armMotor();//however much it needs to go to preset medium junction
            }
            
            //small junction preset
            if (gamepad2.a) {
               // armMotor();//however much it needs to go to preset small junction
            }


        }
    }
}








