/* Copyright (c) 2017 FIRST. All rights reserved.
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

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="OpMode", group="Linear Opmode")
//@Disabled
public class OpMode extends LinearOpMode {


    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor RoataStangaFata = null;
    private DcMotor RoataStangaSpate = null;
    private DcMotor RoataDreaptaFata = null;
    private DcMotor RoataDreaptaSpate = null;
    private Servo BratStanga = null;
    private Servo BratDreapta = null;
    private DcMotor Lift =null;
    //private Servo PinionTrapa = null;

    //int cupa=0;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();


        RoataStangaFata = hardwareMap.get(DcMotor.class, "StangaFata");
        RoataStangaSpate = hardwareMap.get(DcMotor.class, "StangaSpate");
        RoataDreaptaFata = hardwareMap.get(DcMotor.class, "DreaptaFata");
        RoataDreaptaSpate = hardwareMap.get(DcMotor.class, "DreaptaSpate");
        Lift = hardwareMap.get(DcMotor.class, "Lift");
        BratDreapta = hardwareMap.get(Servo.class, "BratDreapta");
        BratStanga = hardwareMap.get(Servo.class, "BratStanga");
        //PinionTrapa = hardwareMap.get(Servo.class, "PinionTrapa");

        RoataStangaFata.setDirection(DcMotor.Direction.FORWARD);
        RoataStangaSpate.setDirection(DcMotor.Direction.FORWARD);
        //RoataDreaptaFata.setDirection(DcMotor.Direction.REVERSE);
        //RoataDreaptaSpate.setDirection(DcMotor.Direction.REVERSE);
        RoataDreaptaFata.setDirection(DcMotor.Direction.FORWARD);
        RoataDreaptaSpate.setDirection(DcMotor.Direction.FORWARD);



        RoataStangaFata.setPower(0);
        RoataStangaSpate.setPower(0);
        RoataDreaptaFata.setPower(0);
        RoataDreaptaSpate.setPower(0);


        waitForStart();
        runtime.reset();


        while (opModeIsActive()) {


            double RotireStanga=gamepad1.left_stick_x;
            double MersFata=gamepad1.right_stick_y;
            double MersStanga=gamepad1.right_stick_x;

            while(gamepad2.x){
                BratStanga.setPosition(1);
                BratDreapta.setPosition(1);
            }

            while(gamepad2.b){
                BratStanga.setPosition(-1);
                BratDreapta.setPosition(-1);
            }

            Lift.setPower(gamepad2.right_stick_y);


            RoataDreaptaFata.setPower(RotireStanga);
            RoataDreaptaSpate.setPower(RotireStanga);
            RoataStangaFata.setPower(RotireStanga);
            RoataStangaSpate.setPower(RotireStanga);

            RoataDreaptaFata.setPower(MersFata);
            RoataDreaptaSpate.setPower(MersFata);
            RoataStangaFata.setPower(-MersFata);
            RoataStangaSpate.setPower(-MersFata);

            RoataDreaptaFata.setPower(MersStanga);
            RoataDreaptaSpate.setPower(-MersStanga);
            RoataStangaFata.setPower(MersStanga);
            RoataStangaSpate.setPower(-MersStanga);



            /*if(gamepad2.x)
            {
                PinionTrapa.setPosition(.7);
            }
            if(gamepad2.y)
            {
                PinionTrapa.setPosition(.2);
            }*/

            /*if(gamepad1.a)
            {
                RoataDreapta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                RoataStanga.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                RoataDreapta.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                RoataStanga.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                RoataDreapta.setTargetPosition(4);
                RoataStanga.setTargetPosition(4);

                RoataDreapta.setPower(1);
                RoataStanga.setPower(1);

                while(RoataDreapta.isBusy() && RoataStanga.isBusy())
                {
                    //Asteptam sa ajunga la pozitie
                }
            }*/




            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            //telemetry.addData("Roti", "stanga (%.2f), dreapta (%.2f)", PutereStanga, PutereDreapta);
            //telemetry.addData("Brat", "putere (%.2f)", PutereBrat);
            telemetry.update();
        }
    }
}
