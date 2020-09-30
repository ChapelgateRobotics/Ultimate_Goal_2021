package org.firstinspires.ftc.teamcode;

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
    @Override
    public void init()
    {

    }
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor frontLeft;
    DcMotor frontRight;

    @Override
    public void loop()
    {
        backLeft.getMotorType(DcMotor.class, "blackLeft");
        backRight.getMotorType(DcMotor.class, "backRight");
        frontLeft.getMotorType(DcMotor.class, "frontLeft");
        frontRight.getMotorType(DcMotor.class, "frontRight");


    }
}
