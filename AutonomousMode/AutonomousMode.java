package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="AutonomousMode", group="Linear Opmode")
//@Disabled
public class AutonomousMode extends LinearOpMode {

    //posibil sa le sterg
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor RoataStanga = null;
    private DcMotor RoataDreapta = null;
    private DcMotor Brat = null;
    private CRServo PinionStanga =null;
    private CRServo PinionDreapta = null;
   // private DcMotor MotorColectare = null;
    //private Servo trapa = null;


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        RoataDreapta  = hardwareMap.get(DcMotor.class, "Dreapta");
        RoataStanga = hardwareMap.get(DcMotor.class, "Stanga");
        Brat  = hardwareMap.get(DcMotor.class, "Brat");
        PinionStanga  = hardwareMap.get(CRServo.class, "PinionStanga");
        PinionDreapta = hardwareMap.get(CRServo.class, "PinionDreapta");



        RoataStanga.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RoataDreapta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Brat.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        RoataDreapta.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RoataStanga.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Brat.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        // Inversam motoarele impulsul electric dat motoarelor care stau in directia opusa cu celelalte
        RoataStanga.setDirection(DcMotor.Direction.FORWARD);
        RoataDreapta.setDirection(DcMotor.Direction.REVERSE);
        Brat.setDirection(DcMotor.Direction.FORWARD);

        // Le setam puterea 0 pentru a nu avea nicio surpriza

        /*MotorStangaFata.setPower(0);
        //*********ori asta, ori cea de jos dupa start trebuie stearsa, ori chiar pus mai sus inainte de runOpMode
        MotorStangaBrat.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); //tin bratul blocat
        MotorDreaptaBrat.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        MotorDreaptaBrat.setPower(-.6); // tinem bratul in tensiune pentru a nu se tranti de pamant
        MotorStangaBrat.setPower(-.6);*/

        Brat.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();
        runtime.reset();

        //**********
        Brat.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); //tin bratul blocat

        double PozitieBrat=Brat.getCurrentPosition();

        Brat.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        Brat.setTargetPosition(-200);

        Brat.setPower(1);

        while (Brat.isBusy()) {
            //Asteptam sa ajunga la pozitie
        }
        /*[
        //Trebuie sa verific mai intai in ce directie merge, cu ce tick-uri si cu ce putere pentru a cobora usor robotul.
        MotorDreaptaBrat.setTargetPosition(-3000);
        MotorStangaBrat.setTargetPosition(-3000);
        MotorStangaBrat.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        MotorDreaptaBrat.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        MotorDreaptaBrat.setPower(0.5);
        MotorStangaBrat.setPower(0.5);
        while(MotorStangaBrat.isBusy() && MotorDreaptaBrat.isBusy())
        {
            //asteptam sa ajunga la destinatie
        }

         */
        //posibil ca ce e in comentariu mai sus sa fie implementat dupa testari

        RoataStanga.setPower(-1);
        RoataDreapta.setPower(-1);
        sleep(1000);

        PinionDreapta.setPower(1);
        PinionStanga.setPower(-1);
        sleep(2000);
        
        /*while (getRuntime() < 3) {}
        MotorDreaptaFata.setPower(.3);
        MotorDreaptaSpate.setPower(.3);
        MotorStangaFata.setPower(-.3);
        MotorStangaSpate.setPower(-.3); // repet pentru a elibera robotul*/


        //repet while getruntime cu motoarele de la roti pana detasez robotul si il duc in patrat, urmand sa deblochez servo-ul si sa arunc markerul.
        //de asemenea, trebuie sa fac inca unul pentru partea opusa a lander-ului.
        telemetry.addData("EncoderBrat",PozitieBrat);

    }
}

