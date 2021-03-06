package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.lynx.MessageKeyedLock;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

//import sun.text.resources.th.BreakIteratorInfo_th;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOp", group="Red")
public class TeleOp extends OpMode {
    DcMotor frontRight, frontLeft, backRight, backLeft, intake, intakeMotor2, wobbleGoalGrabber;
    Servo WGS;
    final double COLLECTPOWER = 1.0;
    final double TICKS_PER_REVOLURION = (383.6 * 2);

    final double wobbleGoalGrabberPOWER = 0.5;
    boolean if_pressedGp1X = false;
    double MAXTICK = 383.6/2;






    public void init() {
            /*frontRight = hardwareMap.dcMotor.get("frontRight");
            frontLeft = hardwareMap.dcMotor.get("frontLeft");
            backRight = hardwareMap.dcMotor.get("backRight");
            backLeft = hardwareMap.dcMotor.get("backLeft");
            frontRight.setDirection(DcMotor.Direction.REVERSE);
            frontLeft.setDirection(DcMotor.Direction.FORWARD);
            backRight.setDirection(DcMotor.Direction.REVERSE);
            backLeft.setDirection(DcMotor.Direction.FORWARD);
            frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);*/

        intake = hardwareMap.dcMotor.get("intake");
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake.setDirection(DcMotor.Direction.FORWARD);
        intakeMotor2= hardwareMap.dcMotor.get("intakeMotor2");
        intakeMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeMotor2.setDirection(DcMotorSimple.Direction.FORWARD);

        wobbleGoalGrabber = hardwareMap.dcMotor.get("wobbleGoalGrabber");
        wobbleGoalGrabber.setDirection(DcMotorSimple.Direction.FORWARD);
        wobbleGoalGrabber.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        wobbleGoalGrabber.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        wobbleGoalGrabber.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }

    public void loop() {
        setDriveMotors();
        setIntakeMotor();
        setWobbleGoalGrabberEncoder();
    }

    private void setIntakeMotor() {
        if (gamepad1.right_bumper) {
            intake.setPower(COLLECTPOWER);
            intakeMotor2.setPower(COLLECTPOWER);
        } else if (gamepad1.left_bumper) {
            intake.setPower(-COLLECTPOWER);
            intakeMotor2.setPower(COLLECTPOWER);
        } else {
            intake.setPower(0);
            intakeMotor2.setPower(0);
        }
    }

    private void setWobbleGoalGrabberEncoder () {
        if (gamepad1.dpad_down) {
            if(wobbleGoalGrabber.getCurrentPosition() <= 386.3/2) {
                wobbleGoalGrabber.setPower(0.3);
            }else {
                wobbleGoalGrabber.setPower(0);
            }
        }
        else if (gamepad1.dpad_up) {
            if (wobbleGoalGrabber.getCurrentPosition() > 3) {
                wobbleGoalGrabber.setPower(-0.3);
            }else{
                wobbleGoalGrabber.setPower(0);
            }
        }
        else {
            wobbleGoalGrabber.setPower(0);
        }
    }


    private void setWobbleGoalGrabber() {
        if (gamepad1.dpad_down) {
            wobbleGoalGrabber.setPower(wobbleGoalGrabberPOWER);
        } else {
            wobbleGoalGrabber.setPower(0);
        }

        /* use case if it is needed */

        if (gamepad1.x) {
            if (!if_pressedGp1X) {
                if (WGS.getPosition() <= 0.1) {
                    WGS.setPosition(1);
                    if_pressedGp1X = true;
                }
            }

        } else {
            if_pressedGp1X = false;
        }
    }




    private void setDriveMotors() {
 /*           if(gamepad1.right_stick_y - gamepad1.right_stick_x > 1){
                frontLeft.setPower(1 - gamepad1.left_stick_x/2);
                backRight.setPower(1 + gamepad1.left_stick_x/2);
            }
            else{
                frontLeft.setPower((gamepad1.right_stick_y - gamepad1.right_stick_x) - gamepad1.left_stick_x/2);
                backRight.setPower((gamepad1.right_stick_y - gamepad1.right_stick_x) + gamepad1.left_stick_x/2);
            }
            if(gamepad1.right_stick_y + gamepad1.right_stick_x > 1){
                frontRight.setPower(1 + gamepad1.left_stick_x/2);
                backLeft.setPower(1 - gamepad1.left_stick_x/2);
            }
            else {
                frontRight.setPower((gamepad1.right_stick_y + gamepad1.right_stick_x) + gamepad1.left_stick_x / 2);
                backLeft.setPower((gamepad1.right_stick_y + gamepad1.right_stick_x) - gamepad1.left_stick_x / 2);
            }*/

    }
}