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

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;



@TeleOp(name="OpMode", group="Linear Opmode")
@Disabled
public class OpMode extends LinearOpMode {


    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor RoataStanga = null;
    private DcMotor RoataDreapta = null;
    private DcMotor Brat = null;
    private CRServo PinionDreapta = null;
    private CRServo PinionStanga = null;

    //int cupa=0;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();


        RoataStanga  = hardwareMap.get(DcMotor.class, "Stanga");
        RoataDreapta = hardwareMap.get(DcMotor.class, "Dreapta");
        Brat = hardwareMap.get(DcMotor.class, "Brat");
        PinionDreapta = hardwareMap.get(CRServo.class, "PinionDreapta");
        PinionStanga = hardwareMap.get(CRServo.class, "PinionStanga");

        RoataStanga.setDirection(DcMotor.Direction.FORWARD);
        RoataDreapta.setDirection(DcMotor.Direction.REVERSE);
        Brat.setDirection(DcMotor.Direction.FORWARD);

        RoataStanga.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RoataDreapta.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Brat.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        RoataStanga.setPower(0);
        RoataDreapta.setPower(0);
        Brat.setPower(0);

        waitForStart();
        runtime.reset();


        while (opModeIsActive()) {


            double PutereStanga=gamepad1.left_stick_y;
            double PutereDreapta=-gamepad1.right_stick_y;
            double PutereBrat=-gamepad2.right_stick_y;
            double PozitieDreapta=RoataDreapta.getCurrentPosition();
            double PozitieBrat=Brat.getCurrentPosition();


            RoataDreapta.setPower(PutereDreapta);
            RoataStanga.setPower(PutereStanga);
            Brat.setPower(PutereBrat);

            /*if(gamepad2.b)
                cupa=1;
            if(gamepad2.y)
                cupa=0;
            if(gamepad2.x)
                cupa=-1;
            */
            if(gamepad2.b)
            {
                PinionDreapta.setPower(1);
                PinionStanga.setPower(-1);
            }
            if(gamepad2.y)
            {
                PinionDreapta.setPower(-1);
                PinionStanga.setPower(1);
            }
            if(gamepad2.x)
            {
                PinionDreapta.setPower(0);
                PinionStanga.setPower(0);
            }

            if(gamepad1.a)
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
            }


            while(gamepad2.left_bumper)
            {
                Brat.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }


            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Roti", "stanga (%.2f), dreapta (%.2f)", PutereStanga, PutereDreapta);
            telemetry.addData("Brat", "putere (%.2f)", PutereBrat);
            telemetry.addData("EncoderRoti",PozitieDreapta);
            telemetry.addData("EncoderBrat",PozitieBrat);
            telemetry.update();
        }
    }
}
