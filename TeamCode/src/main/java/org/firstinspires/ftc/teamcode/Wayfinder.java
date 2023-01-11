package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
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

public class Wayfinder implements Runnable {

    LinearOpMode opmode;
    HardwareMap hardwareMap;
    Spark robot;
    Telemetry telem;

    //Seasonal Based Varaiables

    private static final String VUFORIA_KEY = "AX3G6fv/////AAABmZBRkXN1c0tErKrNShdsK6sg6OR84mZ/jXTiTsmSViRiT11DOpbYuleK3MKGoFvmzMm4L65rUWpoz/OEB0TMjSG3OjZ6VzNljtmWWKeKCiXCudemY6bb7b+oiWh488JScjUKSud0XvcQXHD7RB5NOXLQyDYjrCxvreo6mrI+Rc77PLFMtv5QItyJRz57htJ8dnp2o4qNx9J8T4XzObgSmIxOESJ5+pZ/wEkytqXWZ2ZGDUIJ5WXBgAadjmz+ypSkF321D7GYjXu1R03n8hjuJEyuiDnnNaAtwMvlYGeqtjBpCmKRD2SYkLKzboD/KM0geHWj2SoL+NDuvLsw6TfISbcDXFR3qGf5d4Z4fBRhc8sm";

    private static final float mmPerInch        = 25.4f;
    private static final float mmTargetHeight   = 6 * mmPerInch;   // the height of the center of the target image above the floor
    private static final float halfField        = 72 * mmPerInch;
    private static final float halfTile         = 12 * mmPerInch;
    private static final float oneAndHalfTile   = 36 * mmPerInch;

    final float CAMERA_FORWARD_DISPLACEMENT  = 0.0f * mmPerInch; // eg: Enter the forward distance from the center of the robot to the camera lens
    final float CAMERA_VERTICAL_DISPLACEMENT = 6.0f * mmPerInch; // Distance camera is above the ground. Camera is 6 inches above ground.
    final float CAMERA_LEFT_DISPLACEMENT = 0.0f * mmPerInch; //Left distance from center of robot to camera lens

    private VuforiaLocalizer.Parameters parameters;
    private OpenGLMatrix lastLocation;
    private VuforiaLocalizer vuforia;
    private VuforiaTrackables targets;
    private WebcamName webcamName;
    List<VuforiaTrackable> allTrackables;

    private boolean targetVisible = false;

    //Trackable variables
    public VectorF translation;
    public Orientation rotation;
    public float X;
    public float Y;
    public float Z;
    public float Roll;
    public float Pitch;
    public float Heading;

    public enum CameraPlacement {
        FRONT
    }
    public Wayfinder(LinearOpMode opmode, CameraPlacement type, Spark robot){
        this.opmode = opmode;
        hardwareMap = opmode.hardwareMap;
        this.robot = robot;
        switch(type){
            case FRONT: //Test camera placement configuration
                final float CAMERA_FORWARD_DISPLACEMENT  = 0.0f * mmPerInch;   // eg: Enter the forward distance from the center of the robot to the camera lens
                final float CAMERA_VERTICAL_DISPLACEMENT = 6.0f * mmPerInch;   // eg: Camera is 6 Inches above ground
                final float CAMERA_LEFT_DISPLACEMENT     = 0.0f * mmPerInch;   // eg: Enter the left distance from the center of the robot to the camera lens
        }

        //Map the camera varaiables
        webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");

        // Load the data sets for the trackable objects. These particular data
        // sets are stored in the 'assets' part of our application.
        targets = this.vuforia.loadTrackablesFromAsset("FreightFrenzy");

        allTrackables = new ArrayList<VuforiaTrackable>();
        allTrackables.addAll(targets);

        identifyTarget(0, "Blue Storage",       -halfField,  oneAndHalfTile, mmTargetHeight, 90, 0, 90);
        identifyTarget(1, "Blue Alliance Wall",  halfTile,   halfField,      mmTargetHeight, 90, 0, 0);
        identifyTarget(2, "Red Storage",        -halfField, -oneAndHalfTile, mmTargetHeight, 90, 0, 90);
        identifyTarget(3, "Red Alliance Wall",   halfTile,  -halfField,      mmTargetHeight, 90, 0, 180);
        //Camera setup
        OpenGLMatrix cameraLocationOnRobot = OpenGLMatrix
                .translation(CAMERA_FORWARD_DISPLACEMENT, CAMERA_LEFT_DISPLACEMENT, CAMERA_VERTICAL_DISPLACEMENT)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XZY, DEGREES, 90, 90, 0));

        for (VuforiaTrackable trackable : allTrackables) {
            ((VuforiaTrackableDefaultListener) trackable.getListener()).setCameraLocationOnRobot(parameters.cameraName, cameraLocationOnRobot);
        }
    }

    public void run(){
        addTelemetry();
        while(opmode.opModeIsActive() && !opmode.isStopRequested()){
            getLocation();
            telem.update();
        }
    }

    void identifyTarget(int targetIndex, String targetName, float dx, float dy, float dz, float rx, float ry, float rz) {
        VuforiaTrackable aTarget = targets.get(targetIndex);
        aTarget.setName(targetName);
        aTarget.setLocation(OpenGLMatrix.translation(dx, dy, dz)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, rx, ry, rz)));
    }

    void initVuforia(){
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        //Uncomment below and comment the above line if no camera preview wanted
        // VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = webcamName;

        // Turn off Extended tracking.  Set this true if you want Vuforia to track beyond the target.
        parameters.useExtendedTracking = false;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }
    public void addTelemetry(){
        telem.addData("X", X);
        telem.addData("Y", Y);
        telem.addData("Z", Z);
        telem.addData("Roll", Roll);
        telem.addData("Pitch", Pitch);
        telem.addData("Heading", Heading);
    }
    public void getLocation() {
        targetVisible = false;
        for (VuforiaTrackable trackable : allTrackables) {
            if (((VuforiaTrackableDefaultListener) trackable.getListener()).isVisible()) {

                //trackable.getName() = name of target
                targetVisible = true;

                // getUpdatedRobotLocation() will return null if no new information is available since
                // the last time that call was made, or if the trackable is not currently visible.
                OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener) trackable.getListener()).getUpdatedRobotLocation();
                if (robotLocationTransform != null) {
                    lastLocation = robotLocationTransform;
                }
                break;
            }
        }
        // Provide feedback as to where the robot is located (if we know).
        if (targetVisible) {

            // express position (translation) of robot in mm.
            //translation.get(0) = X position
            //translation.get(1) = Y position
            //translation.get(2) = Z position
            translation = lastLocation.getTranslation();
            X = translation.get(0);
            Y = translation.get(1);
            Z = translation.get(2);
            // express the rotation of the robot in degrees.
            //rotation.firstAngle = Roll
            //rotation.secondAngle = Pitch
            //rotation.thirdAngle = Heading
            rotation = Orientation.getOrientation(lastLocation, EXTRINSIC, XYZ, DEGREES);
            Roll = rotation.firstAngle;
            Pitch = rotation.secondAngle;
            Heading = rotation.thirdAngle;
        }
    }
    public void moveLocation(float x, float y){
        //Maybe turning while driving will not work, because camera needs to continually see target
        //Set max speed in beginning , slow down and proportionally increase turning speed
        //Arrange coordinate plane to be on positive x-y instead of 0 being center

        while (opmode.opModeIsActive()){
            //run entire distance calculation every second/few seconds
            //Prioritize lateral, set turning to max, and check for heading to tell when it is done turning
            //Slowdown parameter
        }

        //Second, move towards point
        while (opmode.opModeIsActive()){
        }
    }
}