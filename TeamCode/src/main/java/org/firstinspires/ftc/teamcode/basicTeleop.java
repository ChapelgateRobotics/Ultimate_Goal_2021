package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.motors.RevRoboticsCoreHexMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created byhomefrankfurth on 9/30/20 in Android_Studio-FTC_app.
 * <p>
 * Copyright (c) ©2020 Kirk Frankfurth
 * Copyright (c) ©2020 Mentor of Team VECTOR (5233) Kirk
 * Resource: https:/gitlab.com/robotics/ftc_app
 * Contact: kfrankfurth@chapelgateacademy.org, vector5233@gmail.com
 */
class basicTeleop extends OpMode
{

    DcMotor backLeft;
    DcMotor backRight;
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor collector;
    final double COLLECTPOWER = 0.5;

    @Override
    public void init()
    {
        // maps motor names to hardware on Rev Expansion Hub
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight= hardwareMap.get(DcMotor.class, "frontRight");
        backLeft  = hardwareMap.get(DcMotor.class, "blackLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");


        // sets motors directions
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        //RevRoboticsCore Hex Motor map hardware on Rev Expansion Hub

        collector =hardwareMap.dcMotor.get("collector");


        //set motors power behaviors
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        collector.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        collector.setDirection(DcMotor.Direction.FORWARD);
    }


    @Override
    public void loop()
    {
        setDriveMotors();
        setCollectorMotor();


    }

    private void setCollectorMotor(){
        if (gamepad1.right_bumper){
            collector.setPower(COLLECTPOWER);
        }else if (gamepad1.left_bumper){
            collector.setPower(-COLLECTPOWER);
        }else{
            collector.setPower(0);
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
