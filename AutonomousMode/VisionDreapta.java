package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
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
    private Servo BratStanga = null;
    private Servo BratDreapta = null;
    private DcMotor Lift =null;
    private CRServo Marker=null;

    @Override
    public void runOpMode() throws InterruptedException {
        RoataStangaFata = hardwareMap.get(DcMotor.class, "StangaFata");
        RoataStangaSpate = hardwareMap.get(DcMotor.class, "StangaSpate");
        RoataDreaptaFata = hardwareMap.get(DcMotor.class, "DreaptaFata");
        RoataDreaptaSpate = hardwareMap.get(DcMotor.class, "DreaptaSpate");
        Lift = hardwareMap.get(DcMotor.class, "Lift");
        BratDreapta = hardwareMap.get(Servo.class, "BratDreapta");
        BratStanga = hardwareMap.get(Servo.class, "BratStanga");
        Marker = hardwareMap.get(CRServo.class, "Marker");

        RoataStangaFata.setDirection(DcMotor.Direction.FORWARD);
        RoataStangaSpate.setDirection(DcMotor.Direction.FORWARD);
        //RoataDreaptaFata.setDirection(DcMotor.Direction.REVERSE);
        //RoataDreaptaSpate.setDirection(DcMotor.Direction.REVERSE);
        RoataDreaptaFata.setDirection(DcMotor.Direction.FORWARD);
        RoataDreaptaSpate.setDirection(DcMotor.Direction.FORWARD);
        Lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        double PozitieLift=Lift.getCurrentPosition();

        Marker.setPower(.1);
        BratStanga.setPosition(-1);
        BratDreapta.setPosition(1);

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;// recommended camera direction
        parameters.vuforiaLicenseKey = "Ac7GQyH/////AAABmQC0j+p04UHYpqWBuUpkcVo3F0cps9pRCdTxXtjoyFNly3j2vCgp5ZBWMWyEfHCk8XzVqJNdvqB6GEgsZLUjaESQMWKCrksUG+3B8iby7PADKBu1goL/oLZRMZG9DvzP/eltAS9BC+D7R1cHyOeFYDFQtIv0wVFGf41tKZd7hpLyiOmgh+xlxlm5Nh+ww3r+CsnHZlDXNXRXaEl0LHbVhnTv62DT1NYOOircdwsDCmZxX/q+GvL67ahAV34iUGxA8j8OwP82GO/UQuZwTYvlrXo3nscteWO40T4jdXY18uSaqfAiPQWk2HN1IyJZ9U3VjNR9naqtkqZVpLC9h+6Z9t7gQUos7WwSRZEhYLRrL3GI";

        vision = new MasterVision(parameters, hardwareMap, true, MasterVision.TFLiteAlgorithm.INFER_LEFT);
        vision.init();// enables the camera overlay. this will take a couple of seconds
        vision.enable();// enables the tracking algorithms. this might also take a little time



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
            //sleep(2000);
        /*RoataDreaptaFata.setPower(-1);
        RoataDreaptaSpate.setPower(-1);
        RoataStangaFata.setPower(1);
        RoataStangaSpate.setPower(1);
        sleep(2500);*/


            switch (goldPosition){
                case CENTER:{
                    Lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    Lift.setTargetPosition(38900);

                    Lift.setPower(1);
                    while(Lift.isBusy())
                    {

                    }
                    Lift.setPower(0);
                    //sleep(19000);
                    //Lift.setPower(0);

                    RoataDreaptaFata.setPower(1);//iese din cui
                    RoataDreaptaSpate.setPower(-1);
                    RoataStangaFata.setPower(1);
                    RoataStangaSpate.setPower(-1);
                    sleep(250);

                    RoataDreaptaFata.setPower(-1);//merge mai in fata de cui
                    RoataDreaptaSpate.setPower(-1);
                    RoataStangaFata.setPower(1);
                    RoataStangaSpate.setPower(1);
                    sleep(200);

                    RoataDreaptaFata.setPower(-1);//merge in stanga in dreptul cubului
                    RoataDreaptaSpate.setPower(1);
                    RoataStangaFata.setPower(-1);
                    RoataStangaSpate.setPower(1);
                    sleep(500);

                    RoataDreaptaFata.setPower(-1);//da in fata spre cub
                    RoataDreaptaSpate.setPower(-1);
                    RoataStangaFata.setPower(1);
                    RoataStangaSpate.setPower(1);
                    sleep(900);

                    RoataDreaptaFata.setPower(1);//da in spate din cub
                    RoataDreaptaSpate.setPower(1);
                    RoataStangaFata.setPower(-1);
                    RoataStangaSpate.setPower(-1);
                    sleep(400);

                    RoataDreaptaFata.setPower(-1);//se roteste in stanga
                    RoataDreaptaSpate.setPower(-1);
                    RoataStangaFata.setPower(-1);
                    RoataStangaSpate.setPower(-1);
                    sleep(850);

                    RoataDreaptaFata.setPower(-1);//o ia inainte spre perete
                    RoataDreaptaSpate.setPower(-1);
                    RoataStangaFata.setPower(1);
                    RoataStangaSpate.setPower(1);
                    sleep(1500);

                    RoataDreaptaFata.setPower(-1);//se roteste in stanga spre depo
                    RoataDreaptaSpate.setPower(-1);
                    RoataStangaFata.setPower(-1);
                    RoataStangaSpate.setPower(-1);
                    sleep(450);

                    RoataDreaptaFata.setPower(-1);//se duce in fata
                    RoataDreaptaSpate.setPower(-1);
                    RoataStangaFata.setPower(1);
                    RoataStangaSpate.setPower(1);
                    sleep(2000);

                    RoataDreaptaFata.setPower(0);
                    RoataDreaptaSpate.setPower(0);
                    RoataStangaFata.setPower(0);
                    RoataStangaSpate.setPower(0);
                    sleep(10);

                    Marker.setPower(-1);
                    sleep(400);

                    BratStanga.setPosition(1);
                    BratDreapta.setPosition(-1);
                    sleep(300);

                    RoataDreaptaFata.setPower(-1);//hatzaiala
                    RoataDreaptaSpate.setPower(-1);
                    RoataStangaFata.setPower(1);
                    RoataStangaSpate.setPower(1);
                    sleep(500);

                    RoataDreaptaFata.setPower(1);
                    RoataDreaptaSpate.setPower(1);
                    RoataStangaFata.setPower(-1);
                    RoataStangaSpate.setPower(-1);
                    sleep(500);

                    RoataDreaptaFata.setPower(-1);//hatzaiala
                    RoataDreaptaSpate.setPower(-1);
                    RoataStangaFata.setPower(1);
                    RoataStangaSpate.setPower(1);
                    sleep(500);

                    RoataDreaptaFata.setPower(1);
                    RoataDreaptaSpate.setPower(1);
                    RoataStangaFata.setPower(-1);
                    RoataStangaSpate.setPower(-1);
                    sleep(100);

                    break;

                }
                case RIGHT: {
                    //Lift.setPower(1);
                    Lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    Lift.setTargetPosition(38900);

                    Lift.setPower(1);
                    while(Lift.isBusy())
                    {

                    }
                    Lift.setPower(0);
                    //sleep(19000);
                    //Lift.setPower(0);

                    RoataDreaptaFata.setPower(1);
                    RoataDreaptaSpate.setPower(-1);
                    RoataStangaFata.setPower(1);
                    RoataStangaSpate.setPower(-1);
                    sleep(250);

                    RoataStangaFata.setPower(1);
                    RoataDreaptaSpate.setPower(-1);
                    sleep(400);

                    RoataDreaptaFata.setPower(-1);
                    RoataDreaptaSpate.setPower(-1);
                    RoataStangaFata.setPower(1);
                    RoataStangaSpate.setPower(1);
                    sleep(600);

                    RoataDreaptaFata.setPower(1);
                    RoataDreaptaSpate.setPower(1);
                    RoataStangaFata.setPower(-1);
                    RoataStangaSpate.setPower(-1);
                    sleep(300);

                    RoataDreaptaFata.setPower(-1);
                    RoataDreaptaSpate.setPower(-1);
                    RoataStangaFata.setPower(-1);
                    RoataStangaSpate.setPower(-1);
                    sleep(900);

                    RoataDreaptaFata.setPower(-1);
                    RoataDreaptaSpate.setPower(-1);
                    RoataStangaFata.setPower(1);
                    RoataStangaSpate.setPower(1);
                    sleep(500); //2500

                    RoataDreaptaFata.setPower(1);
                    RoataDreaptaSpate.setPower(1);
                    RoataStangaFata.setPower(1);
                    RoataStangaSpate.setPower(1);
                    sleep(100); //200

                    RoataDreaptaFata.setPower(-1);
                    RoataDreaptaSpate.setPower(-1);
                    RoataStangaFata.setPower(1);
                    RoataStangaSpate.setPower(1);
                    sleep(1700);

                    RoataDreaptaFata.setPower(-1);
                    RoataDreaptaSpate.setPower(-1);
                    RoataStangaFata.setPower(-1);
                    RoataStangaSpate.setPower(-1);
                    sleep(400);

                    RoataDreaptaFata.setPower(-1);
                    RoataDreaptaSpate.setPower(-1);
                    RoataStangaFata.setPower(1);
                    RoataStangaSpate.setPower(1);
                    sleep(2000);

                    RoataDreaptaFata.setPower(0);
                    RoataDreaptaSpate.setPower(0);
                    RoataStangaFata.setPower(0);
                    RoataStangaSpate.setPower(0);
                    sleep(100);

                    //Marker.setPower(-.1);


                    /*BratStanga.setPosition(-1);
                    BratDreapta.setPosition(1);
                    sleep(700);*/

                    Marker.setPower(-.1);
                    sleep(400);

                    BratStanga.setPosition(1);
                    BratDreapta.setPosition(-1);
                    sleep(300);

                    RoataDreaptaFata.setPower(-1);//hatzaiala
                    RoataDreaptaSpate.setPower(-1);
                    RoataStangaFata.setPower(1);
                    RoataStangaSpate.setPower(1);
                    sleep(300);

                    RoataDreaptaFata.setPower(1);
                    RoataDreaptaSpate.setPower(1);
                    RoataStangaFata.setPower(-1);
                    RoataStangaSpate.setPower(-1);
                    sleep(300);

                    RoataDreaptaFata.setPower(-1);//hatzaiala
                    RoataDreaptaSpate.setPower(-1);
                    RoataStangaFata.setPower(1);
                    RoataStangaSpate.setPower(1);
                    sleep(300);

                    RoataDreaptaFata.setPower(1);
                    RoataDreaptaSpate.setPower(1);
                    RoataStangaFata.setPower(-1);
                    RoataStangaSpate.setPower(-1);
                    sleep(100);

                    //Marker.setPower(.1);

                    /*RoataStangaFata.setPower(1);
                    RoataDreaptaSpate.setPower(-1);
                    sleep(600);*/

                    break;
                }
                case LEFT:{
                    Lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    Lift.setTargetPosition(38900);

                    Lift.setPower(1);
                    while(Lift.isBusy())
                    {

                    }
                    Lift.setPower(0);
                    //sleep(19000);
                    //Lift.setPower(0);

                    RoataDreaptaFata.setPower(1);//iese din cui
                    RoataDreaptaSpate.setPower(-1);
                    RoataStangaFata.setPower(1);
                    RoataStangaSpate.setPower(-1);
                    sleep(250);

                    RoataDreaptaFata.setPower(-1);//merge mai in fata de cui
                    RoataDreaptaSpate.setPower(-1);
                    RoataStangaFata.setPower(1);
                    RoataStangaSpate.setPower(1);
                    sleep(200);

                    RoataDreaptaFata.setPower(-1);//merge in stanga in dreptul cubului
                    RoataDreaptaSpate.setPower(1);
                    RoataStangaFata.setPower(-1);
                    RoataStangaSpate.setPower(1);
                    sleep(500);

                    RoataDreaptaFata.setPower(-1);//da in fata spre cub
                    RoataDreaptaSpate.setPower(-1);
                    RoataStangaFata.setPower(1);
                    RoataStangaSpate.setPower(1);
                    sleep(400);

                    RoataDreaptaFata.setPower(-1);//merge in stanga in dreptul cubului
                    RoataDreaptaSpate.setPower(1);
                    RoataStangaFata.setPower(-1);
                    RoataStangaSpate.setPower(1);
                    sleep(500);

                    RoataDreaptaFata.setPower(-1);//da in fata spre cub
                    RoataDreaptaSpate.setPower(-1);
                    RoataStangaFata.setPower(1);
                    RoataStangaSpate.setPower(1);
                    sleep(500);

                    RoataDreaptaFata.setPower(1);//da in spate din cub
                    RoataDreaptaSpate.setPower(1);
                    RoataStangaFata.setPower(-1);
                    RoataStangaSpate.setPower(-1);
                    sleep(150);

                    BratStanga.setPosition(1);
                    BratDreapta.setPosition(-1);
                    sleep(500);

                    RoataDreaptaFata.setPower(-1);//se roteste in stanga
                    RoataDreaptaSpate.setPower(-1);
                    RoataStangaFata.setPower(-1);
                    RoataStangaSpate.setPower(-1);
                    sleep(900);

                    RoataDreaptaFata.setPower(-1);//o ia inainte spre perete
                    RoataDreaptaSpate.setPower(-1);
                    RoataStangaFata.setPower(1);
                    RoataStangaSpate.setPower(1);
                    sleep(1100);

                    RoataDreaptaFata.setPower(-1);//se roteste in stanga spre depo
                    RoataDreaptaSpate.setPower(-1);
                    RoataStangaFata.setPower(-1);
                    RoataStangaSpate.setPower(-1);
                    sleep(400);

                    RoataDreaptaFata.setPower(-1);//se duce in fata
                    RoataDreaptaSpate.setPower(-1);
                    RoataStangaFata.setPower(1);
                    RoataStangaSpate.setPower(1);
                    sleep(1100);

                    RoataDreaptaFata.setPower(0);
                    RoataDreaptaSpate.setPower(0);
                    RoataStangaFata.setPower(0);
                    RoataStangaSpate.setPower(0);
                    sleep(10);


                    Marker.setPower(-1);
                    sleep(600);


                    RoataDreaptaFata.setPower(-1);//hatzaiala
                    RoataDreaptaSpate.setPower(-1);
                    RoataStangaFata.setPower(1);
                    RoataStangaSpate.setPower(1);
                    sleep(500);

                    RoataDreaptaFata.setPower(1);
                    RoataDreaptaSpate.setPower(1);
                    RoataStangaFata.setPower(-1);
                    RoataStangaSpate.setPower(-1);
                    sleep(500);

                    RoataDreaptaFata.setPower(-1);//hatzaiala
                    RoataDreaptaSpate.setPower(-1);
                    RoataStangaFata.setPower(1);
                    RoataStangaSpate.setPower(1);
                    sleep(500);

                    RoataDreaptaFata.setPower(1);
                    RoataDreaptaSpate.setPower(1);
                    RoataStangaFata.setPower(-1);
                    RoataStangaSpate.setPower(-1);
                    sleep(100);

                    break;
                }
            }

        vision.shutdown();
    }
}
