package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 *import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
 *import com.qualcomm.robotcore.hardware.DcMotor;
 *
 *
 */

/**
 *Notes For this TeleOp Code. This code is for Comp and all proggramers should review over this
 *code and understand this code for the possibility that a question may be asked related to TeleOp and
 *you should be able to explain in good detail everything in this code.
 *11/16/17-> Changed all gamepad's in code to correct gamepad (i.e some gamepad1's to gamepad2)
 ***11/18/17-> Competition Notes below
 *Notes-> Autonomous is incorrect, Not much was wrong from a software sandpoint but hardware issues were fixed
 *Autonomous issues included: Incorrect spinning causing us to move out of destination,
 *To much time on the down motion of the clamp and arm.
 *These issues are still not resolved
 * Recomendation for autonomous issues(Not Offical):Fine tune the timer on the clamp
 * Fine tune the movements and LOWER the TIME OF MOVEMENT in autonomous.
 * List of issues at Comp(1)-> https://docs.google.com/a/stjoebears.com/spreadsheets/d/1r_liipKBU7GHfONdxq9E6d4f7zikcCuXwDL2bsQfwm0/edit?usp=sharing
 *G-Sheet of time VS Heading for autonomous -> https://docs.google.com/a/stjoebears.com/spreadsheets/d/1pqv0iN94fFd5KvX1YIWP7z39HgpURXsscn0zPujs1q4/edit?usp=sharing
*/
@TeleOp(name="Simple Mecanum Drive 2", group="TeleOp")

public class teleOpAll13702 extends LinearOpMode {

    HardwareJoeBot2018 robot = new HardwareJoeBot2018();

    @Override
    public void runOpMode() throws InterruptedException {


        robot.init(hardwareMap, this);


        double forward;
        double clockwise;
        double right;
        // Define class members
        DcMotor liftMotor;
        double  power   = 0;

        DcMotor shoulderMotor;
        double  shoulderPower   = 0;

        DcMotor elbowMotor;
        double  elbowPower   = 0;

        DcMotor intakeMotor;
        double intakePower   = 0;

       // Connect to motor (Assume standard lift motor)
        // Change the text in quotes to match any motor name on your robot.
        liftMotor = hardwareMap.get(DcMotor.class, "liftMotor");
        shoulderMotor = hardwareMap.get(DcMotor.class, "shoulderMotor");
        elbowMotor = hardwareMap.get(DcMotor.class, "elbowMotor");
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");

        waitForStart();



        //start of loop
        while (opModeIsActive()) {


            //Drive Via "Analog Sticks" (Not Toggle)
            //Set initial motion parameters to Gamepad1 Inputs
            forward = -gamepad1.left_stick_y;
            //right = gamepad1.left_stick_x;
            right = -gamepad1.left_trigger + gamepad1.right_trigger;
            clockwise = gamepad1.right_stick_x;

            robot.moveRobot(forward, right, clockwise);

            // Map "power" variable to gamepad input
            power = gamepad2.left_stick_y;
            shoulderMotor.setPower(shoulderPower);

            power = gamepad2.left_stick_x;
            elbowMotor.setPower(elbowPower);

            if (gamepad2.dpad_up) {
                liftMotor.setPower(power);
            }
            if (gamepad2.dpad_down) {
                liftMotor.setPower(-power);
            }


            if (gamepad1.a) {
                intakeMotor.setPower(intakePower);
            }
            if (gamepad1.b) {
                intakeMotor.setPower(-intakePower);
            }



            // Update Telemetry
            telemetry.addData(">", "Press Stop to end test.");
            telemetry.update();
            idle();


        }//end while
    }
}