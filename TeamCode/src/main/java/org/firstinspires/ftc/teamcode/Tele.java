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
        robot = new Spark(this, Spark.Drivetrain.MECHANUM);
    }

    @Override
    public void loop() {
        //MOVEMENT
        //First, we want to make the robot rest if the gamepad is not being touched


        //If the gamepad is NOT at rest, then we want to see what we need to do.
        if (gamepad1.atRest() && gamepad2.atRest()) robot.rest();
        else {
            //If the gamepad is NOT at rest, then we want to see what we need to do.
            //GAMEPAD 1 CODE
            robot.mechanumMovL(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);

            //Arm code
            if (gamepad2.left_stick_y > 0.1) {
                robot.armMotor.setPower(gamepad2.left_stick_y);
            } else if (gamepad2.left_stick_y < -0.1) {
                robot.armMotor.setPower(gamepad2.left_stick_y);
            } else {
                robot.armMotor.setPower(0);
            }

            //Claw code
            if (gamepad2.left_trigger > 0.1) {
               robot.clawServo.setPosition(0);
            }
            if (gamepad2.right_trigger > 0.1) {
                robot.clawServo.setPosition(1);
            }
            if (gamepad2.x) {
                robot.clawServo.setPosition(0.5);
            }
            if (gamepad2.y) {
                robot.clawServo.setPosition(0.25);
            }
            if (gamepad2.a) {
                robot.clawServo.setPosition(0.75);
            }

            /*reset
            if (gamepad2.x) {
           //  armMotor();//reset
                //robot.armUpFT(80, 0.5);
                //robot.armDownFT(80, 0.5);
            }

            //tall junction preset
            if (gamepad2.y) {
                //robot.armUpFT(180, 0.5);
            }
            
            //medium junction preset
            if (gamepad2.b) {
               robot.armUpFT(145, 0.5);
              // robot.rest(250);
               //robot.armDownFT(245, 0.5);
            }
            
            //small junction preset
            if (gamepad2.a) {
                //robot.armDownFT(245, 0.5);
                //robot.rest(250);
                //robot.armUpFT(100, 0.5);
            }*/

        }
    }
}