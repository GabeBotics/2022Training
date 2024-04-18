package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;


import java.util.List;

public class Spark {

    public HardwareMap hwMap;

    LinearOpMode auton;

   /* An ENUM is a custom variable type that we can define the options for
            * This variable can be declared as a type Drivetrain, and then can be set to one of the options
     listed in the list inside {}
   */
    public enum Drivetrain {
        MECHANUM,
       MISTRO,
       TEST
   }
   //these enums are for the different teams in the competition
    public enum Team {
        RED,
       BLUE
   }

   //Here is an example of us DECLARING the Drivetrain variable type drive
    //This drive can be set to any values inside of the Drivetrain enum list above

    public Drivetrain drive;

  //  This is the telem object that the library will be using to write to the driver hub
     //telem is how we output to the drivers while they are using the robot
     //Also can be used for us to debug

    public Telemetry telem;

    //global variables declared below

    public DcMotor motor1, motor2, motor3, motor4;

    public DcMotor[] allDriveMotors;

    public DcMotor armMotor, intakeMotor, suspensionMotor, droneMotor;

    public Servo leftClawServo, rightClawServo, angleServo, hookServo;

    public CRServo revolveServo, intakeServo;

    public IMU imu;

    public IMU.Parameters parameters;

    public WebcamName webcamName;

    //contants go here

    static final double HOOK_DROP_POSITION = .45;

    static final double HOOK_UP_POSITION = .42;

    //ticks per inch value

    static final double INCH_TICKS = 40;

    /**
     * The CONSTRUCTOR for the library class. This constructor pulls the HardwareMap from the opmode
     * and runs the setupHardware function
     * @param opmode the opmode that is being used. NOTE: This only works for TELEOP opmodes, not AUTON
     */

    public Spark( OpMode opmode, Drivetrain drivetrain) {

        //set the hardware map to the opmode's hardwareMap
        this.hwMap = opmode.hardwareMap;

        //set the drive to the opmode's drive train
        this.drive = drivetrain;

        this.telem = opmode.telemetry;

        //maps variables to their hardware map
        setupHardware();

    }

    public  Spark(LinearOpMode opmode, Drivetrain type) {
        //Set the auton opmode directly to the opmode object.
        //Objects can be passed in to functions, so this object will be updated live.
        this.auton = opmode;

        //sets the hardwareMap
        hwMap = opmode.hardwareMap;

        //sets the telem
        telem = opmode.telemetry;

        //sets the drivetrains
        drive = type;

        //maps variables for hwMap and Drivetrain
        setupHardware();

    }

        /**
         * This constructor is used for testing the hardwareMap and drivetrain
         * @param hardwareMap the hardwareMap being tested
         * @param drivetrain the drivetrain being tested
         */
    public Spark( HardwareMap hardwareMap, Drivetrain drivetrain ) {

        this.hwMap = hardwareMap;

        this.drive = drivetrain;

        setupHardware();

        }

    /**
     * This function maps the variables declared above to a specific hardware object,
     * as defined by the configuration on the driver hub
     * Note: This function can only be used inside the library, since it is private
     */
    private void setupHardware() {

        // This switch statement is used to choose which drivetrain to setup,
        // depending on the drive variable.
        switch ( drive ) {

            // If drive is MECHANUM, then everything in here will setup the MECHANUM hardware variables
            case MISTRO:

                motor1 = hwMap.dcMotor.get("motor1");
                motor2 = hwMap.dcMotor.get("motor1");
                motor3 = hwMap.dcMotor.get("motor1");
                motor4 = hwMap.dcMotor.get("motor1");

                //Next, reverse motors that need to spin the other direction
                // Tip: All motors should move the robot forward if set to power 1
                motor1.setDirection(DcMotorSimple.Direction.REVERSE);

                //below any additional Hardware devices

                //map the imu to the hwmap
                imu = hwMap.get( IMU.class, "imu");

                //Set parameters for the imu.
                //Check the direction that the logo is facing.
                //Check the direction that the USB plugs are facing
                parameters = new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.FORWARD, RevHubOrientationOnRobot.UsbFacingDirection.LEFT));

                imu.initialize( parameters );

                webcamName = hwMap.get(WebcamName.class, "Webcam 1");

                //add arm mechanism hw devices

                armMotor = hwMap.dcMotor.get( "armMotor" );

                leftClawServo = hwMap.servo.get( "leftClawServo" );
                rightClawServo = hwMap.servo.get( "rightClawServo" );

                break;

            case MECHANUM:

                motor1 = hwMap.dcMotor.get("motor1");
                motor2 = hwMap.dcMotor.get("motor2");
                motor3 = hwMap.dcMotor.get("motor3");
                motor4 = hwMap.dcMotor.get("motor4");

                motor1.setDirection(DcMotorSimple.Direction.REVERSE);

                //additional hardware device for robot down hea

                //map the IMU for the hw device

                imu = hwMap.get(IMU.class, "imu");


                //parameters for imu
                parameters = new IMU.Parameters( new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.FORWARD, RevHubOrientationOnRobot.UsbFacingDirection.LEFT));

            imu.initialize( parameters );

            //camera setup

                webcamName = hwMap .get(WebcamName.class, "Webcam 1");

                //arm mechanism

                motor1.setDirection(DcMotorSimple.Direction.REVERSE);
                motor4.setDirection(DcMotorSimple.Direction.REVERSE);

                armMotor = hwMap.dcMotor.get( "armMotor" );
                droneMotor = hwMap.dcMotor.get( "droneMotor ");
                suspensionMotor = hwMap.dcMotor.get( "suspensionMotor" );
                revolveServo = hwMap.crservo.get( "revolveServo" );
                intakeServo = hwMap.crservo.get( "intakeServo" );
                hookServo = hwMap.servo.get("hookServo");
                angleServo = hwMap.servo.get("angleServo");
                leftClawServo = hwMap.servo.get("leftClawServo");
                rightClawServo = hwMap.servo.get("rightClawServo");
                allDriveMotors = new DcMotor[]{motor1, motor2, motor3, motor4};
                suspensionMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }

}
