package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="TeleOPmode", group="Linear Opmode")
//@Disabled
public class TeleOpMode extends LinearOpMode {

    // Declaram componentele robotului.
     ElapsedTime runtime = new ElapsedTime();
     DcMotor MotorStangaFata = null;
     DcMotor MotorDreaptaFata = null;
     DcMotor MotorStangaSpate = null;
     DcMotor MotorDreaptaSpate = null;
     DcMotor MotorDreaptaBrat = null;
     DcMotor MotorStangaBrat = null;
     DcMotor MotorColectare = null;
     Servo trapa = null;
	//declaram variabilele prin care motoarele o sa primeasca impulsuri. Sunt folosite in while-ul cel mai jos
    double leftPower;
    double rightPower;
    boolean prindere=false;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initializam variabilele hardware cu cele notate in config file-ul din telefon
        MotorStangaFata  = hardwareMap.get(DcMotor.class, "MSF");
        MotorDreaptaFata = hardwareMap.get(DcMotor.class, "MDF");
        MotorStangaSpate  = hardwareMap.get(DcMotor.class, "MSS");
        MotorDreaptaSpate  = hardwareMap.get(DcMotor.class, "MDS");
        MotorDreaptaBrat = hardwareMap.get(DcMotor.class, "MDB");
        MotorStangaBrat = hardwareMap.get(DcMotor.class, "MSB");
        MotorColectare = hardwareMap.get(DcMotor.class, "MC");
        trapa = hardwareMap.get(Servo.class, "Trapa");

        MotorStangaFata.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        MotorDreaptaFata.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        MotorStangaSpate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        MotorDreaptaSpate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        MotorStangaBrat.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        MotorDreaptaBrat.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        MotorColectare.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        MotorStangaFata.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MotorDreaptaFata.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MotorStangaSpate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MotorDreaptaSpate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MotorStangaBrat.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MotorDreaptaBrat.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MotorColectare.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Inversam motoarele impulsul electric dat motoarelor care stau in directia opusa cu celelalte
        MotorStangaFata.setDirection(DcMotor.Direction.REVERSE);
        MotorStangaSpate.setDirection(DcMotor.Direction.REVERSE);
        MotorDreaptaFata.setDirection(DcMotor.Direction.FORWARD);
        MotorDreaptaSpate.setDirection(DcMotor.Direction.FORWARD);
        MotorStangaBrat.setDirection (DcMotor.Direction.FORWARD);
        MotorDreaptaBrat.setDirection (DcMotor.Direction.REVERSE);

		// Le setam puterea 0 pentru a nu avea nicio surpriza
        MotorStangaFata.setPower(0);
        MotorDreaptaFata.setPower(0);
        MotorStangaSpate.setPower(0);
        MotorDreaptaSpate.setPower(0);
        MotorStangaBrat.setPower(0);
        MotorDreaptaBrat.setPower(0);
        MotorColectare.setPower(0);
        trapa.setPosition(0);


        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            leftPower  = -gamepad1.left_stick_y ;
            rightPower = -gamepad1.right_stick_y ;

            // Trimitem motoarelor impulsul primit de variabile
            MotorStangaFata.setPower(leftPower);
            MotorDreaptaFata.setPower(rightPower);
            MotorStangaSpate.setPower(leftPower);
            MotorDreaptaSpate.setPower(rightPower);
            MotorColectare.setPower(-gamepad2.left_stick_y);
            MotorDreaptaBrat.setPower(-gamepad2.right_stick_y);
            MotorStangaBrat.setPower(-gamepad2.right_stick_y);

            if(gamepad2.a)
            {
                prindere=true;
                MotorStangaBrat.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                MotorDreaptaBrat.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }

            while(prindere)
            {
                if(gamepad2.x)
                {
                    prindere=false;
                }
                //Trebuie sa verific mai intai in ce directie merge, cu ce tick-uri si cu ce putere pentru a urca usor robotul.
                //circumferinta=diametrul*pi | diametrul rotii~12cm | motorul are 1440 tick-uri | 37.70 cm are o rotatie completa
                //circumferinta=37.70 cm
                //fiecare tick are 0.02618 cm (circumferinta/1440)

                MotorDreaptaBrat.setTargetPosition(3000);
                MotorStangaBrat.setTargetPosition(3000);
                MotorStangaBrat.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                MotorDreaptaBrat.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                MotorDreaptaBrat.setPower(0.5);
                MotorStangaBrat.setPower(0.5);
                while(MotorStangaBrat.isBusy() && MotorDreaptaBrat.isBusy())
                {
                    //asteptam sa ajunga la destinatie
                }
                MotorStangaBrat.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); //aplic o frana, just in case;
                MotorDreaptaBrat.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                //MotorStangaBrat.setZeroPowerBehavior(DcMotor.ZeroBehavior.BRAKE); le tin in comentariu pentru a vedea daca este nevoie sa le franez acolo, sau se franeaza singure de la targetPosition
                //MotorDreaptaBrat.setZeroPowerBehavior(DcMotor.ZeroBehavior.BRAKE);
                /*MotorStangaBrat.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                MotorDreaptaBrat.setMode(DcMotor.RunMode.RUN_TO_POSITION));*/
            }

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();
        }
    }
}
