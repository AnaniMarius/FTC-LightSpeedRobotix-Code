package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.teamcode.vision.MasterVision;
import org.firstinspires.ftc.teamcode.vision.SampleRandomizedPositions;


@Autonomous(name="TensorDreapta", group="Linear Opmode")
//@Disabled
public class VisionDreapta extends LinearOpMode{
    MasterVision vision;
    SampleRandomizedPositions goldPosition;
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor RoataStangaFata = null;
    private DcMotor RoataStangaSpate = null;
    private DcMotor RoataDreaptaFata = null;
    private DcMotor RoataDreaptaSpate = null;
    private DcMotor Brat = null;
    private Servo Marker=null;

    @Override
    public void runOpMode() throws InterruptedException {
        RoataDreaptaSpate = hardwareMap.get(DcMotor.class, "StangaFata");
        RoataDreaptaFata = hardwareMap.get(DcMotor.class, "StangaSpate");
        RoataStangaSpate = hardwareMap.get(DcMotor.class, "DreaptaFata");
        RoataStangaFata = hardwareMap.get(DcMotor.class, "DreaptaSpate");
        Brat = hardwareMap.get(DcMotor.class, "Brat");
        Marker = hardwareMap.get(Servo.class,"Servo");

        Brat.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        Brat.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;// recommended camera direction
        parameters.vuforiaLicenseKey = "Ac7GQyH/////AAABmQC0j+p04UHYpqWBuUpkcVo3F0cps9pRCdTxXtjoyFNly3j2vCgp5ZBWMWyEfHCk8XzVqJNdvqB6GEgsZLUjaESQMWKCrksUG+3B8iby7PADKBu1goL/oLZRMZG9DvzP/eltAS9BC+D7R1cHyOeFYDFQtIv0wVFGf41tKZd7hpLyiOmgh+xlxlm5Nh+ww3r+CsnHZlDXNXRXaEl0LHbVhnTv62DT1NYOOircdwsDCmZxX/q+GvL67ahAV34iUGxA8j8OwP82GO/UQuZwTYvlrXo3nscteWO40T4jdXY18uSaqfAiPQWk2HN1IyJZ9U3VjNR9naqtkqZVpLC9h+6Z9t7gQUos7WwSRZEhYLRrL3GI";

        vision = new MasterVision(parameters, hardwareMap, true, MasterVision.TFLiteAlgorithm.INFER_RIGHT);
        vision.init();// enables the camera overlay. this will take a couple of seconds
        vision.enable();// enables the tracking algorithms. this might also take a little time

        RoataStangaFata.setDirection(DcMotor.Direction.FORWARD);
        RoataStangaSpate.setDirection(DcMotor.Direction.FORWARD);
        RoataDreaptaFata.setDirection(DcMotor.Direction.REVERSE);
        RoataDreaptaSpate.setDirection(DcMotor.Direction.REVERSE);
        Brat.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();

        vision.disable();// disables tracking algorithms. this will free up your phone's processing power for other jobs.

        //sleep(2000);

        goldPosition = vision.getTfLite().getLastKnownSampleOrder();


            telemetry.addData("goldPosition was", goldPosition);// giving feedback

            switch (goldPosition){ // using for things in the autonomous program
                case LEFT:
                    telemetry.addLine("going to the left");
                    break;
                case CENTER:
                    telemetry.addLine("going straight");
                    break;
                case RIGHT:
                    telemetry.addLine("going to the right");
                    break;
                case UNKNOWN:
                    telemetry.addLine("staying put");
                    break;
            }

            telemetry.update();
            sleep(2000);

        Marker.setPosition(-1);

            switch (goldPosition){
                case CENTER:{
                    RoataDreaptaFata.setPower(.3);
                    RoataDreaptaSpate.setPower(.3);
                    RoataStangaFata.setPower(.3);
                    RoataStangaSpate.setPower(.3);
                    sleep(400);
                    RoataDreaptaFata.setPower(0);
                    RoataDreaptaSpate.setPower(0);
                    RoataStangaFata.setPower(0);
                    RoataStangaSpate.setPower(0);
                    sleep(10);
                    Brat.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    Brat.setTargetPosition(-945);

                    Brat.setPower(.2);

                    while (Brat.isBusy()) {
                        //Asteptam sa ajunga la pozitie
                    }

                    RoataDreaptaFata.setPower(.3);
                    RoataDreaptaSpate.setPower(.3);
                    RoataStangaFata.setPower(.3);
                    RoataStangaSpate.setPower(.3);
                    sleep(2000); //original cu 100 mai mult

                    RoataDreaptaFata.setPower(0);
                    RoataDreaptaSpate.setPower(0);
                    RoataStangaFata.setPower(0);
                    RoataStangaSpate.setPower(0);
                    sleep(10);

                    Brat.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    Brat.setTargetPosition(-50);

                    Brat.setPower(.2);

                    while (Brat.isBusy()) {
                        //Asteptam sa ajunga la pozitie
                    }

                    RoataDreaptaFata.setPower(.3);
                    RoataDreaptaSpate.setPower(.3);
                    RoataStangaFata.setPower(.3);
                    RoataStangaSpate.setPower(.3);
                    sleep(1000);

                    Marker.setPosition(1);

                    RoataDreaptaFata.setPower(.7);
                    RoataDreaptaSpate.setPower(.7);
                    RoataStangaFata.setPower(.7);
                    RoataStangaSpate.setPower(.7);
                    sleep(300);
                    RoataDreaptaFata.setPower(-.7);
                    RoataDreaptaSpate.setPower(-.7);
                    RoataStangaFata.setPower(-.7);
                    RoataStangaSpate.setPower(-.7);
                    sleep(300);
                    RoataDreaptaFata.setPower(.7);
                    RoataDreaptaSpate.setPower(.7);
                    RoataStangaFata.setPower(.7);
                    RoataStangaSpate.setPower(.7);
                    sleep(300);
                    RoataDreaptaFata.setPower(-.7);
                    RoataDreaptaSpate.setPower(-.7);
                    RoataStangaFata.setPower(-.7);
                    RoataStangaSpate.setPower(-.7);
                    sleep(300);
                    RoataDreaptaFata.setPower(-.3);
                    RoataDreaptaSpate.setPower(-.3);
                    RoataStangaFata.setPower(.3);
                    RoataStangaSpate.setPower(.3);
                    sleep(600); //1272

                    RoataDreaptaFata.setPower(-1);
                    RoataDreaptaSpate.setPower(-1);
                    RoataStangaFata.setPower(-1);
                    RoataStangaSpate.setPower(-1);
                    sleep(3000);

                    break;

                }
                case RIGHT: {
                    RoataDreaptaFata.setPower(.3);
                    RoataDreaptaSpate.setPower(.3);
                    RoataStangaFata.setPower(.3);
                    RoataStangaSpate.setPower(.3);
                    sleep(897);

                    RoataDreaptaFata.setPower(-.3);
                    RoataDreaptaSpate.setPower(-.3);
                    RoataStangaFata.setPower(.3);
                    RoataStangaSpate.setPower(.3);
                    sleep(1172); //1272

                    RoataDreaptaFata.setPower(0);
                    RoataDreaptaSpate.setPower(0);
                    RoataStangaFata.setPower(0);
                    RoataStangaSpate.setPower(0);
                    sleep(10);

                    Brat.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    Brat.setTargetPosition(-945);

                    Brat.setPower(.2);

                    while (Brat.isBusy()) {
                        //Asteptam sa ajunga la pozitie
                    }

                    RoataDreaptaFata.setPower(.3);
                    RoataDreaptaSpate.setPower(.3);
                    RoataStangaFata.setPower(.3);
                    RoataStangaSpate.setPower(.3);
                    sleep(2212); //era 1700

                    RoataDreaptaFata.setPower(0);
                    RoataDreaptaSpate.setPower(0);
                    RoataStangaFata.setPower(0);
                    RoataStangaSpate.setPower(0);
                    sleep(10);

                    Brat.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    Brat.setTargetPosition(-50);

                    Brat.setPower(.2);

                    while (Brat.isBusy()) {
                        //Asteptam sa ajunga la pozitie
                    }

                    RoataDreaptaFata.setPower(.3);
                    RoataDreaptaSpate.setPower(.3);
                    RoataStangaFata.setPower(-.3);
                    RoataStangaSpate.setPower(-.3);
                    sleep(2545);

                    RoataDreaptaFata.setPower(.3);
                    RoataDreaptaSpate.setPower(.3);
                    RoataStangaFata.setPower(.3);
                    RoataStangaSpate.setPower(.3);
                    sleep(2307);

                    Marker.setPosition(1);

                    RoataDreaptaFata.setPower(.7);
                    RoataDreaptaSpate.setPower(.7);
                    RoataStangaFata.setPower(.7);
                    RoataStangaSpate.setPower(.7);
                    sleep(500);
                    RoataDreaptaFata.setPower(-.7);
                    RoataDreaptaSpate.setPower(-.7);
                    RoataStangaFata.setPower(-.7);
                    RoataStangaSpate.setPower(-.7);
                    sleep(500);
                    RoataDreaptaFata.setPower(.7);
                    RoataDreaptaSpate.setPower(.7);
                    RoataStangaFata.setPower(.7);
                    RoataStangaSpate.setPower(.7);
                    sleep(500);
                    RoataDreaptaFata.setPower(-.7);
                    RoataDreaptaSpate.setPower(-.7);
                    RoataStangaFata.setPower(-.7);
                    RoataStangaSpate.setPower(-.7);
                    sleep(500);

                    RoataDreaptaFata.setPower(.3);
                    RoataDreaptaSpate.setPower(.3);
                    RoataStangaFata.setPower(-.3);
                    RoataStangaSpate.setPower(-.3);
                    sleep(2545);

                    RoataDreaptaFata.setPower(1);
                    RoataDreaptaSpate.setPower(1);
                    RoataStangaFata.setPower(1);
                    RoataStangaSpate.setPower(1);
                    sleep(3000);

                    break;
                }
                case LEFT:{
                    RoataDreaptaFata.setPower(.3);
                    RoataDreaptaSpate.setPower(.3);
                    RoataStangaFata.setPower(.3);
                    RoataStangaSpate.setPower(.3);
                    sleep(897);

                    RoataDreaptaFata.setPower(.3);
                    RoataDreaptaSpate.setPower(.3);
                    RoataStangaFata.setPower(-.3);
                    RoataStangaSpate.setPower(-.3);
                    sleep(848);

                    RoataDreaptaFata.setPower(0);
                    RoataDreaptaSpate.setPower(0);
                    RoataStangaFata.setPower(0);
                    RoataStangaSpate.setPower(0);
                    sleep(10);

                    Brat.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    Brat.setTargetPosition(-945);

                    Brat.setPower(.2);

                    while (Brat.isBusy()) {
                        //Asteptam sa ajunga la pozitie
                    }

                    RoataDreaptaFata.setPower(.3);
                    RoataDreaptaSpate.setPower(.3);
                    RoataStangaFata.setPower(.3);
                    RoataStangaSpate.setPower(.3);
                    sleep(2212); //era 1700

                    RoataDreaptaFata.setPower(0);
                    RoataDreaptaSpate.setPower(0);
                    RoataStangaFata.setPower(0);
                    RoataStangaSpate.setPower(0);
                    sleep(10);

                    Brat.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    Brat.setTargetPosition(-50);

                    Brat.setPower(.2);

                    while (Brat.isBusy()) {
                        //Asteptam sa ajunga la pozitie
                    }

                    RoataDreaptaFata.setPower(-.3);
                    RoataDreaptaSpate.setPower(-.3);
                    RoataStangaFata.setPower(.3);
                    RoataStangaSpate.setPower(.3);
                    sleep(2545);

                    RoataDreaptaFata.setPower(.3);
                    RoataDreaptaSpate.setPower(.3);
                    RoataStangaFata.setPower(.3);
                    RoataStangaSpate.setPower(.3);
                    sleep(2307);


                    Marker.setPosition(1);

                    RoataDreaptaFata.setPower(.7);
                    RoataDreaptaSpate.setPower(.7);
                    RoataStangaFata.setPower(.7);
                    RoataStangaSpate.setPower(.7);
                    sleep(500);
                    RoataDreaptaFata.setPower(-.7);
                    RoataDreaptaSpate.setPower(-.7);
                    RoataStangaFata.setPower(-.7);
                    RoataStangaSpate.setPower(-.7);
                    sleep(500);
                    RoataDreaptaFata.setPower(.7);
                    RoataDreaptaSpate.setPower(.7);
                    RoataStangaFata.setPower(.7);
                    RoataStangaSpate.setPower(.7);
                    sleep(500);
                    RoataDreaptaFata.setPower(-.7);
                    RoataDreaptaSpate.setPower(-.7);
                    RoataStangaFata.setPower(-.7);
                    RoataStangaSpate.setPower(-.7);
                    sleep(500);

                    RoataDreaptaFata.setPower(-1);
                    RoataDreaptaSpate.setPower(-1);
                    RoataStangaFata.setPower(-1);
                    RoataStangaSpate.setPower(-1);
                    sleep(3000);


                }
            }

        vision.shutdown();
    }
}
