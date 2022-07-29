/* Copyright (c) 2019 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.ArrayList;
import java.util.List;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XZY;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;
import static org.firstinspires.ftc.teamcode.Spark.Drivetrain.MECHANUM;

/**
 * This OpMode illustrates using the Vuforia localizer to determine positioning and orientation of
 * robot on the FTC field using a WEBCAM.  The code is structured as a LinearOpMode
 *
 * NOTE: If you are running on a Phone with a built-in camera, use the ConceptVuforiaFieldNavigation example instead of this one.
 * NOTE: It is possible to switch between multiple WebCams (eg: one for the left side and one for the right).
 *       For a related example of how to do this, see ConceptTensorFlowObjectDetectionSwitchableCameras
 *
 * When images are located, Vuforia is able to determine the position and orientation of the
 * image relative to the camera.  This sample code then combines that information with a
 * knowledge of where the target images are on the field, to determine the location of the camera.
 *
 * Finally, the location of the camera on the robot is used to determine the
 * robot's location and orientation on the field.
 *
 * To learn more about the FTC field coordinate model, see FTC_FieldCoordinateSystemDefinition.pdf in this folder
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list.
 *
 * IMPORTANT: In order to use this OpMode, you need to obtain your own Vuforia license key as
 * is explained below.
 */

@TeleOp(name="Kindling", group ="Testing")
//@Disabled
public class Kindling extends LinearOpMode {
    private Spark robot;
    private Wayfinder finder;
    @Override public void runOpMode() {
        robot = new Spark(this, MECHANUM);
        finder = new Wayfinder(this, Wayfinder.CameraPlacement.FRONT, robot);
        String[] directions = {"Forward", "Backward", "MoveLeft", "MoveRight", "TurnRight", "TurnLeft"};
        int d = 0;
        String direct = directions[d];
        int tickTarget = 0;
        double speed = 0;
        finder.initVuforia(); //Initializes Vuforia through Wayfinder
        finder.run(); //Starts background target tracking

        waitForStart();

        /* Note: To use the remote camera preview
         * AFTER you hit Init on the Driver Station, use the "options menu" to select "Camera Stream"
         * Tap the preview window to receive a fresh image.
         * It is not permitted to transition to RUN while the camera preview window is active.
         * Either press STOP to exit the OpMode, or use the "options menu" again, and select "Camera Stream" to close the preview window.
         */

        telemetry.addData("direction", direct);
        telemetry.addData("Target Ticks", tickTarget);
        telemetry.addData("Speed", speed);
        while(opModeIsActive() && !isStopRequested()){

            //Speed control using joysticks
            if (speed >= 0 && speed <= 1) {
                speed += (gamepad1.left_stick_y * -1) / 100;
            } else if (speed < 0){
                speed = 0;
            } else if (speed > 1) {
                speed = 1;
            }


            if(gamepad1.x){ //Reset all motor encoders
                robot.resetDriveEncoders();
            } else if (gamepad1.dpad_left){ //Sets direction
                if (d > 0) {
                    d--;
                    direct = directions[d];
                }
            } else if (gamepad1.dpad_right) { //Sets direction
                if (d < directions.length)
                    d++;
                direct = directions[d];
            } else if (gamepad1.dpad_up){ //Sets -tick amount
                tickTarget += 2;
            } else if (gamepad1.dpad_down){ //Sets +tick amount
                tickTarget -= 2;
            } else if (gamepad1.right_trigger > 0.5){ //Activates function
                switch (direct){
                    case "Forward":
                        robot.moveForwardFT(tickTarget, speed);
                    case "Backward":
                        robot.moveBackwardFT(tickTarget, speed);
                    case "MoveLeft":
                        robot.moveLeftFT(tickTarget, speed);
                    case "MoveRight":
                        robot.moveRightFT(tickTarget, speed);
                    case "TurnRight":
                        robot.turnLeftFT(tickTarget, speed);
                    case "TurnLeft":
                        robot.turnLeftFT(tickTarget, speed);
                }
            }

            telemetry.update();
        }
    }
}
