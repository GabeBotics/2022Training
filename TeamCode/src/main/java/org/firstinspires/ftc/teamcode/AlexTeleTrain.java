package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.internal.camera.delegating.DelegatingCaptureSequence;

@TeleOp(name="AlexTeleTrain", group="Template")
//@Disabled
public class AlexTeleTrain extends OpMode {

    private Spark robot;

    @Override
    public void init() {

        robot = new Spark(this, Spark.Drivetrain.TANK);
    }

    @Override
    public void loop() {

/*
if (gamepad1.left_bumper) {
        robot.carouselMotor.setPower(-1);

}else if (gamepad1.right_bumper) {
        robot.carouselMotor.setPower(1);

}else   robot.carouselMotor.setPower(0);
*/
        if (gamepad1.right_stick_x > 0.5) {
            robot.moveRight(0.5);

        if (gamepad1.right_stick_y > 0.5) {
            robot.moveForward(0.5);




        if (gamepad1.left_stick_x > 0.5) {
            robot.turnLeft(0.5);


                            }}}}}