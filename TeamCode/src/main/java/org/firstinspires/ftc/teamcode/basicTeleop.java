package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.lynx.MessageKeyedLock;
import com.qualcomm.hardware.motors.RevRoboticsCoreHexMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import sun.text.resources.th.BreakIteratorInfo_th;

/**
 * Created byhomefrankfurth on 9/30/20 in Android_Studio-FTC_app.
 * <p>
 * Copyright (c) ©2020 Kirk Frankfurth
 * Copyright (c) ©2020 Mentor of Team VECTOR (5233) Kirk
 * Resource: https:/gitlab.com/robotics/ftc_app
 * Contact: kfrankfurth@chapelgateacademy.org, vector5233@gmail.com
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="basicTeleop", group = "Red")

public class basicTeleop extends OpMode {

    DcMotor backLeft;
    DcMotor backRight;
    DcMotor frontLeft;
    DcMotor frontRight;
    //DcMotor collector;
    DcMotor intake;
    DcMotor wobbleGoalGrabber;
    DcMotor intakeMotor2;
    Servo WGS; // Wobble Goal Servo

    final double COLLECTPOWER = 1.0;
    final double TICKS_PER_REVOLUTION = (383.6 * 2);
    final double wobbleGoalGrapperPOWER = 0.5;

    boolean if_pressedGp1x = false;
    double MAXTICKS = 383.6 / 2;

    @Override
    public void init() {
        // maps motor names to hardware on Rev Expansion Hub
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "blackLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");


        // sets motors directions
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        //RevRoboticsCore Hex Motor map hardware on Rev Expansion Hub

        //collector = hardwareMap.dcMotor.get("collector");


        //set motors power behaviors
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intake = hardwareMap.dcMotor.get("intake");
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake.setDirection(DcMotor.Direction.FORWARD);

        intakeMotor2 = hardwareMap.dcMotor.get("intakeMotor2");
        intakeMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeMotor2.setDirection(DcMotor.Direction.FORWARD);

        wobbleGoalGrabber = hardwareMap.dcMotor.get("wobbleGoalGrabber");
        wobbleGoalGrabber.setDirection(DcMotor.Direction.FORWARD);
        wobbleGoalGrabber.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        wobbleGoalGrabber.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        wobbleGoalGrabber.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    @Override
    public void loop() {
        setDriveMotors();
        setIntakeMotor();
        setWobbleGoalEncoder();
    }

    private void setIntakeMotor() {
        if (gamepad1.right_bumper) {
            intake.setPower(COLLECTPOWER);
            intakeMotor2.setPower(COLLECTPOWER);
        } else if (gamepad1.left_bumper) {
            intake.setPower(-COLLECTPOWER);
            intakeMotor2.setPower(-COLLECTPOWER);
        } else {
            intake.setPower(0);
            intakeMotor2.setPower(0);
        }

    }

    private void setWobbleGoalEncoder() {
        if (gamepad1.dpad_down) {
            if (wobbleGoalGrabber.getCurrentPosition() <= 386.3 / 2) {
                wobbleGoalGrabber.setPower(0.3);
            } else {
                wobbleGoalGrabber.setPower(0);
            }
        } else if (gamepad1.dpad_up) {
            if (wobbleGoalGrabber.getCurrentPosition() > 3) {
                wobbleGoalGrabber.setPower(-0.3);
            } else {
                wobbleGoalGrabber.setPower(0);
            }
        } else {
            wobbleGoalGrabber.setPower(0);
        }
    }

    private void setWobbleGoalGrabber() {
        if (gamepad1.dpad_down) {
            wobbleGoalGrabber.setPower(wobbleGoalGrapperPOWER);
        } else {
            wobbleGoalGrabber.setPower(0);
        }


        if(gamepad1.x){
        if (!if_pressedGp1x) {
            if (WGS.getPosition() <= 0.1) {
                WGS.setPosition(1);
                if_pressedGp1x = true;
            }
        }
    } else {
        if_pressedGp1x = false;

    }

}


    private void setDriveMotors(){
        if (gamepad1.right_stick_y - gamepad1.right_stick_x >1){
            frontLeft.setPower(1 -gamepad1.left_stick_x /2);
            backRight.setPower(1 + gamepad1.left_stick_x /2);
        }
        else {
         frontLeft.setPower((gamepad1.right_stick_y - gamepad1.right_stick_x) - gamepad1.left_stick_x /2);
         backRight.setPower((gamepad1.right_stick_y -gamepad1.right_stick_x) + gamepad1.left_stick_x /2);

        }if (gamepad1.right_stick_y + gamepad1.right_stick_x > 1){
            frontRight.setPower(1 + gamepad1.left_stick_x /2);
            backLeft.setPower(1 -gamepad1.left_stick_x /2);
        }else{
            frontRight.setPower((gamepad1.right_stick_y + gamepad1.right_stick_x) + gamepad1.right_stick_x /2);
            backLeft.setPower((gamepad1.right_stick_y + gamepad1.right_stick_x) - gamepad1.left_stick_x /2);
        }

    }

}
