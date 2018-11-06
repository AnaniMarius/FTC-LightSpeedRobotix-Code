package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="AutonomousMode", group="Linear Opmode")
//@Disabled
public class AutonomousMode extends LinearOpMode {

    //posibil sa le sterg
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor MotorStangaFata = null;
    private DcMotor MotorDreaptaFata = null;
    private DcMotor MotorStangaSpate = null;
    private DcMotor MotorDreaptaSpate = null;
    private DcMotor MotorDreaptaBrat = null;
    private DcMotor MotorStangaBrat = null;
    private DcMotor MotorColectare = null;
    private Servo trapa = null;

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        MotorStangaFata  = hardwareMap.get(DcMotor.class, "MSF");
        MotorDreaptaFata = hardwareMap.get(DcMotor.class, "MDF");
        MotorStangaSpate  = hardwareMap.get(DcMotor.class, "MSS");
        MotorDreaptaSpate  = hardwareMap.get(DcMotor.class, "MDS");
        MotorDreaptaBrat = hardwareMap.get(DcMotor.class, "MDB");
        MotorStangaBrat = hardwareMap.get(DcMotor.class, "MSB");
        MotorColectare = hardwareMap.get(DcMotor.class, "MC");
        trapa = hardwareMap.get(Servo.class, "Trapa");


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


        waitForStart();
        runtime.reset();


        trapa.setPosition(0);  // tine marker-ul
        MotorDreaptaBrat.setPower(.6); // tinem bratul in tensiune pentru a nu se tranti de pamant
        MotorStangaBrat.setPower(.6);

        while (getRuntime() < 2){} // il lasam sa cada 2 secunde

        MotorDreaptaBrat.setPower(0); // omoram bratul;
        MotorStangaBrat.setPower(0);
        //aici trebuie sa vad ce miscari sa fac pentru a putea sa desprind carlicul de maner

        MotorDreaptaFata.setPower(-.5); //il fatzaim pentru a iesi din carlig
        MotorDreaptaSpate.setPower(-.5);
        MotorStangaFata.setPower(.5);
        MotorStangaSpate.setPower(.5);

        while (getRuntime() < 3) {}
        MotorDreaptaFata.setPower(.3);
        MotorDreaptaSpate.setPower(.3);
        MotorStangaFata.setPower(-.3);
        MotorStangaSpate.setPower(-.3); // repet pentru a elibera robotul


        //repet while getruntime cu motoarele de la roti pana detasez robotul si il duc in patrat, urmand sa deblochez servo-ul si sa arunc markerul.
        //de asemenea, trebuie sa fac inca unul pentru partea opusa a lander-ului.


    }
}

