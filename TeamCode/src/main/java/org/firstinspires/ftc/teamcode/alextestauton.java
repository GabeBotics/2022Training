            package org.firstinspires.ftc.teamcode;

            import static org.firstinspires.ftc.teamcode.Spark.Drivetrain.MECHANUM;

            import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
            import com.qualcomm.robotcore.eventloop.opmode.Disabled;
            import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
            import com.qualcomm.robotcore.util.ElapsedTime;


            @Autonomous(name="Auton", group="Template")
            @Disabled

            public class alextestauton extends LinearOpMode {
                private Spark robot;
                private Wayfinder finder;
                private ElapsedTime runtime = new ElapsedTime();

                @Override
                public void runOpMode() {
                    robot = new Spark(this, MECHANUM);
                    telemetry.addData("Status", "Initialized");
                    runtime.reset();
                    telemetry.update();
                    Wayfinder finder = new Wayfinder(this, Wayfinder.CameraPlacement.FRONT, robot);
                    finder.initVuforia();
                    waitForStart();
                    finder.run();
                    while (opModeIsActive() && runtime.milliseconds() < 30000) {

//square
                        for (int i = 0; i < 4; i++) {

                            robot.moveForwardFT(1000, 0.5);
                            sleep(100);
                            robot.turnLeftFT(1300, 0.5);
                            sleep(100);


//triangle

                        for (int t = 0; i < 3; i++) {

                            robot.moveForwardFT(1000, 0.5);
                            sleep(100);
                            robot.turnLeftFT(866, 0.5);
                            sleep(100);

//dodecagon

                        for (int d = 0; i < 12; i++) {
                            robot.moveForwardFT(1000, 0.5);
                            sleep(100);
                            robot.turnLeftFT(43, 0.5);
                            sleep(100);


                            }
                        }
                    }
                }
            }
        }
