package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.motors.RevRoboticsCoreHexMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

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
    RevRoboticsCoreHexMotor collector;

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
        collector=(RevRoboticsCoreHexMotor) hardwareMap.dcMotor.get("collector");

        //set motors power behaviors
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }


    @Override
    public void loop()
    {



    }
}
