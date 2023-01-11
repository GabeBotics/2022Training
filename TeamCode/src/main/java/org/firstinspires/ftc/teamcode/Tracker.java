package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

public class Tracker implements Runnable {

    /*
     * Specify the source for the Tensor Flow Model.
     * If the TensorFlowLite object model is in
     * cluded in the Robot Controller App as an "asset",
     * the OpMode must to load it using loadModelFromAsset().  However, if a team generated model
     * has been downloaded to the Robot Controller's SD FLASH memory, it must to be loaded using loadModelFromFile()
     * Here we assume it's an Asset. Also see method initTfod() below .
     */
    private static final String TFOD_MODEL_ASSET = "PowerPlay.tflite";
    // private static final String TFOD_MODEL_FILE  = "/sdcard/FIRST/tflitemodels/CustomTeamModel.tflite";

    private static final String[] LABELS = {
            "1 Bolt",
            "2 Bulb",
            "3 Panel"
    };

    /*
     * IMPORTANT: You need to obtain your own license key to use Vuforia. The string below with which
     * 'parameters.vuforiaLicenseKey' is initialized is for illustration only, and will not function.
     * A Vuforia 'Development' license key, can be obtained free of charge from the Vuforia developer
     * web site at https://developer.vuforia.com/license-manager.
     *
     * Vuforia license keys are always 380 characters long, and look as if they contain mostly
     * random data. As an example, here is a example of a fragment of a valid key:
     *      ... yIgIzTqZ4mWjk9wd3cZO9T1axEqzuhxoGlfOOI2dRzKS4T0hQ8kT ...
     * Once you've obtained a license key, copy the string from the Vuforia web site
     * and paste it in to your code on the next line, between the double quotes.
     */
    private static final String VUFORIA_KEY =
            "AX3G6fv/////AAABmZBRkXN1c0tErKrNShdsK6sg6OR84mZ/jXTiTsmSViRiT11DOpbYuleK3MKGoFvmzMm4L65rUWpoz/OEB0TMjSG3OjZ6VzNljtmWWKeKCiXCudemY6bb7b+oiWh488JScjUKSud0XvcQXHD7RB5NOXLQyDYjrCxvreo6mrI+Rc77PLFMtv5QItyJRz57htJ8dnp2o4qNx9J8T4XzObgSmIxOESJ5+pZ/wEkytqXWZ2ZGDUIJ5WXBgAadjmz+ypSkF321D7GYjXu1R03n8hjuJEyuiDnnNaAtwMvlYGeqtjBpCmKRD2SYkLKzboD/KM0geHWj2SoL+NDuvLsw6TfISbcDXFR3qGf5d4Z4fBRhc8sm";

    private static final float mmPerInch        = 25.4f;

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    private VuforiaLocalizer vuforia;

    /**
     * {@link #tfod} is the variable we will use to store our instance of the TensorFlow Object
     * Detection engine.
     */
    private TFObjectDetector tfod;

    LinearOpMode opmode;
    HardwareMap hardwareMap;
    Spark robot;
    Telemetry telem;


    public enum CameraPlacement {
        FRONT
    }

    public String signalDetected = null;

    public Tracker(LinearOpMode opmode, CameraPlacement type, Spark robot) {
        this.opmode = opmode;
        hardwareMap = opmode.hardwareMap;
        telem = opmode.telemetry;
        this.robot = robot;
        switch (type) {
            case FRONT: //Test camera placement configuration
                final float CAMERA_FORWARD_DISPLACEMENT = 0.0f * mmPerInch;   // eg: Enter the forward distance from the center of the robot to the camera lens
                final float CAMERA_VERTICAL_DISPLACEMENT = 6.0f * mmPerInch;   // eg: Camera is 6 Inches above ground
                final float CAMERA_LEFT_DISPLACEMENT = 0.0f * mmPerInch;   // eg: Enter the left distance from the center of the robot to the camera lens
        }
    }

    public void run(){

        while(opmode.opModeIsActive() && !opmode.isStopRequested()){
            trackObjects();

            //Breaks the thread if the signal is detected
            if (signalDetected != null){
                break;
            }

        }
    }

    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }

    /**
     * Initialize the TensorFlow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.75f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 300;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);

        // Use loadModelFromAsset() if the TF Model is built in as an asset by Android Studio
        // Use loadModelFromFile() if you have downloaded a custom team model to the Robot Controller's FLASH.
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
        // tfod.loadModelFromFile(TFOD_MODEL_FILE, LABELS);
    }

    public void loadTracker(){
        initVuforia();
        initTfod();

        if (tfod != null) {
            tfod.activate();

            // The TensorFlow software will scale the input images from the camera to a lower resolution.
            // This can result in lower detection accuracy at longer distances (> 55cm or 22").
            // If your target is at distance greater than 50 cm (20") you can increase the magnification value
            // to artificially zoom in to the center of image.  For best results, the "aspectRatio" argument
            // should be set to the value of the images used to create the TensorFlow Object Detection model
            // (typically 16/9).
            tfod.setZoom(1.0, 16.0/9.0);
        }

    }

    private void trackObjects(){
        if (tfod != null) {
            // getUpdatedRecognitions() will return null if no new information is available since
            // the last time that call was made.
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                telem.addData("# Objects Detected", updatedRecognitions.size());

                // step through the list of recognitions and display image position/size information for each one
                // Note: "Image number" refers to the randomized image orientation/number
                for (Recognition recognition : updatedRecognitions) {
                    double col = (recognition.getLeft() + recognition.getRight()) / 2 ;
                    double row = (recognition.getTop()  + recognition.getBottom()) / 2 ;
                    double width  = Math.abs(recognition.getRight() - recognition.getLeft()) ;
                    double height = Math.abs(recognition.getTop()  - recognition.getBottom()) ;

                    telem.addData(""," ");
                    telem.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100 );
                    telem.addData("- Position (Row/Col)","%.0f / %.0f", row, col);
                    telem.addData("- Size (Width/Height)","%.0f / %.0f", width, height);

                    signalDetected = recognition.getLabel();

                }
                telem.update();
            }
        }
    }
}
