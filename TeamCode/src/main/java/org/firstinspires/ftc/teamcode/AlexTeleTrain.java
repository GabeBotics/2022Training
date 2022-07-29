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

        //This code initializes the drivetrain. Make sure that you have the right drivetrain selected!
        robot = new Spark(this, Spark.Drivetrain.TANK);
    }

    @Override
    public void loop() {


    if (gamepad1.left_bumper) {
    robot.carouselMotor.setPower(-1);

    if (gamepad1.right_bumper) {
    robot.carouselMotor.setPower(1);
}
    else{if (gamepad1.atRest()){
    robot.rest();

}
}
}
}
}