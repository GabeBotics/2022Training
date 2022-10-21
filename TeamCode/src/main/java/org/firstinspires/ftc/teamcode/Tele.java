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
        //This nested if statement is used to make the robot move using the left joystick.
        // It works for every drive train, as long as it is set up in Anvil!


        //In the gap below would normally be where you would create if statements for buttons
        
        
        
    

        


        //MOVEMENT
        //First, we want to make the robot rest if the gamepad is not being touched


        //If the gamepad is NOT at rest, then we want to see what we need to do.
        if (gamepad1.atRest()) robot.rest();
        else {
            //If the gamepad is NOT at rest, then we want to see what we need to do.
            robot.mechanumMovT(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);
                
            int leftY = gamepad1.left_stick_y;
            int leftX = gamepad1.left_stick_x;
            int rightY = gamepad1.right_stick_y;
            int rightX = gamepad1.right_stick_x;
            
            //If left stick is held to the left, move left.
            if (gamepad1.left_stick_x < -0.2) {
                 robot.moveLeft(leftX);
            }
            //If left stick is held to the right, move right.
            if (gamepad1.left_stick_x > 0.2) {
                robot.moveRight(leftX);            
            }
            //If left stick is held forward, move forward.
            if (gamepad1.left_stick_y > 0.2) {
                robot.moveForward(leftY);
            }
            //If left stick is held back, move backward.
            if (gamepad1.left_stick_y < -0.2) {
                robot.moveBackward(leftY);
            }
            //if right stick is held left, turn left.
            if (gamepad1.right_stick_x < -0.2) {
                robot.turnLeft(rightX);
            }
            //if right stick is held right, turn right.
            if (gamepad1.right_stick_x > 0.2) {
                robot.turnRight(rightX);
            }

        
            //arm/claw code kinda since we don't remember how to

            int leftY2 = gamepad2.left_stick_y;

            // the arm moves up
            if (gamepad2.left_stick_y > 0.2) {
                armMotor(leftY2);
            }
            
            // the arm moves down
            
            if (gamepad2.left_stick_y < -0.2) {
                armMotor(leftY2);
            }

            
            // the claw opens

            if (gamepad2.left_trigger) {
                clawServo(1);
            }


            // the claw closes

            if (gamepad2.right_trigger) {
                clawServo(0);
            }

            
        }
    }
}