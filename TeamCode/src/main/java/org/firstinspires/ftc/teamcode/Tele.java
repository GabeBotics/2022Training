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

        //This code initializes the drivetrain. Make sure that you have the right drivetrain selected
        robot = new Spark(this, Spark.Drivetrain.MECHANUM);
    }

    @Override
    public void loop() {
        //MOVEMENT
        //First, we want to make the robot rest if the gamepad is not being touched

       // telemetry.addData("ArmTouch: ", robot.armIsDown());
       // telemetry.update();
        //If the gamepad is NOT at rest, then we want to see what we need to do.
        telemetry.addData("Touch", robot.armIsDown());
        telemetry.update();

        if (gamepad1.atRest() && gamepad2.atRest()) robot.rest();
        else {
            //If the gamepad is NOT at rest, then we want to see what we need to do.
            //GAMEPAD 1 CODE
            if (gamepad2.left_stick_y < -0.3) {
                robot.armOverride();
                robot.armUp(-gamepad2.left_stick_y);
            } else if (gamepad2.left_stick_y > 0.3) {
                robot.armOverride();
                robot.armDown(gamepad2.left_stick_y);
            } else if (!robot.checkArm()){
                robot.armStop();
            }

            if (gamepad2.left_trigger > 0.2) {
                robot.servoOpen();
            } else if (gamepad2.right_trigger > 0.2) {
                robot.servoClose();
            }

            if (gamepad2.right_stick_x > 0.8) {
                robot.servoPrepare();
            }

            //load preset
            if (gamepad2.a) {
                robot.armLoad();
            }

            //primed preset
            if (gamepad2.y) {
                robot.armPrimed();
            }

            //low junction preset
            if (gamepad2.dpad_down) {
                robot.armLow();
            }

            //medium junction preset
            if (gamepad2.dpad_right) {
                robot.armMedium();
            }

            //high junction preset
            if (gamepad2.dpad_up) {
                robot.armHigh();
            }

            robot.mechanumMovT(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);
            robot.checkArm();
        }
    }
}