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

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 *
 * This is a test Autonomous code to check the workings of the "moveInches" and "rotate" commands
 * in the 2018 HardwareJoeBots class.
 *
 */

@Autonomous(name="Bluedepotvuforia", group="Testing")
//@Disabled
public class BlueDeoptvufoeia extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareJoeBot2018vuforia robot = new HardwareJoeBot2018vuforia();

    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap, this);
        robot.vuforia_init();


        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();
        robot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        robot.moveInches(-12,.2,5);
        robot.rotate(-90,.2);
        robot.moveInches(16,.4,5);

        //have vuforia find our position
        sleep(2000);
        double coordinates [] = robot.getvuforiaposition();
        telemetry.addData("X:", coordinates[0]);
        telemetry.addData("Y:", coordinates[1]);
        telemetry.addData("HEADING:", coordinates[2]);
        telemetry.update();
        sleep(2000);
        double target_y=Math.abs(6-coordinates[1]);
        telemetry.addData("Y value was:", coordinates[1]);
        telemetry.addData("Vuforia says to go forward this far: ", target_y);
        telemetry.update();
        sleep(1000);
        telemetry.addData("Moving this many inches:   ", target_y);
        telemetry.update();
        robot.moveInches(target_y,.4,3);
        sleep(1000);
        coordinates = robot.getvuforiaposition();
        telemetry.addData("X:", coordinates[0]);
        telemetry.addData("Y:", coordinates[1]);
        telemetry.addData("HEADING:", coordinates[2]);
        telemetry.update();

        sleep(10000);
    }

}