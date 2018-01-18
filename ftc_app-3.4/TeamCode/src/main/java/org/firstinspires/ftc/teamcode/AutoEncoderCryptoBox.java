package org.firstinspires.ftc.teamcode;

import android.util.SparseIntArray;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ColorSensor;


@Autonomous(name="Auto")
public class AutoEncoderCryptoBox extends LinearOpMode {

    private Bot bot = new Bot();

    ColorSensor color_sensor;

    static final double COUNTS_PER_MOTOR_REV    = 1440;
    static final double DRIVE_GEAR_REDUCTION    = 2.0;
    static final double WHEEL_DIAMETER_INCHES   = 5.0;
    static final double COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION)
            / (WHEEL_DIAMETER_INCHES * Math.PI) / 2;

    static final double DRIVE_SPEED             = 0.6;
    static final double TURN_SPEED              = 0.5;

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        bot.init(hardwareMap);

        bot.ld.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bot.rd.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        bot.ld.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bot.rd.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        bot.colorSensor = hardwareMap.colorSensor.get("color");

        // wait 'til the drive hits start
        waitForStart();

        boolean isBlue = false;

        // jewel
        encoderDrive(DRIVE_SPEED * -1, 1.75, 1.75, 0);
        bot.jewel.setPosition(.8d);
        if (bot.colorSensor.blue() > 150)
        {
            isBlue = true;
            encoderDrive(DRIVE_SPEED * -1, 2.5, 2.5, 0);
            encoderDrive(DRIVE_SPEED * 1, 2.5, 2.5, 0);
        }
        else
        {
            encoderDrive(DRIVE_SPEED * 1, 2.5, 2.5, 0);
            encoderDrive(DRIVE_SPEED * -1, 2.5, 2.5, 0);
        }

        bot.jewel.setPosition(0);

        /*if(isBlue)
            encoderDrive(DRIVE_SPEED, 36, 36, 0);
        else
            encoderDrive(DRIVE_SPEED, 42, 42, 0);

        encoderDrive(DRIVE_SPEED * -1, 6, -6, 0);

        encoderDrive(DRIVE_SPEED * 1, 12, 12, 0);*/
        // read vumark
        encoderDrive(DRIVE_SPEED, 6, 6, 0);
        ConceptVuMarkIdentification vumark = new ConceptVuMarkIdentification();

        vumark.runOpMode();
        // enter loop


        // Put first block in
        encoderDrive(DRIVE_SPEED, 18, 18, 0);

        encoderDrive(TURN_SPEED, 12, -12, 0);

        bot.arm.setPower(.3);

        try {
            wait(500);
        }
        catch (Exception e){}

        bot.arm.setPower(0);

        bot.clamp.setPosition(1);

        // move forward 18 inches
        //encoderDrive(DRIVE_SPEED, 18, 18, 0);
        
        // turn 90deg left
        //encoderDrive(TURN_SPEED, -12, 12, 0);

        // move forward 18 inches
        //encoderDrive(DRIVE_SPEED, 18, 18, 0);
        /*
        bot.clamp.setPosition(0);

        bot.arm.setPower(1);

        try {
            wait(500);
        }
        catch (Exception e){}

        bot.arm.setPower(0);

        encoderDrive(DRIVE_SPEED, -24, -24, 0);
        
        bot.arm2.setPower(1);

        try {
            wait(500);
        }
        catch (Exception e){}

        bot.arm2.setPower(-1);

        try {
            wait(500);
        }
        catch (Exception e){}

        bot.arm2.setPower(0);

        encoderDrive(DRIVE_SPEED, -6, -6, 0); */

        // place block in location or

        encoderDrive(TURN_SPEED, 24, -24, 0);

        encoderDrive(DRIVE_SPEED, 24, 24, 0);

        bot.clamp.setPosition(0);

        bot.arm.setPower(1);

        try {
            wait(500);
        }
        catch (Exception e){}

        bot.arm.setPower(0);

        encoderDrive(DRIVE_SPEED, -24, -24, 0);

        bot.arm2.setPower(1);

        try {
            wait(500);
        }
        catch (Exception e){}

        bot.arm2.setPower(-1);

        try {
            wait(500);
        }
        catch (Exception e){}

        bot.arm2.setPower(0);

        encoderDrive(TURN_SPEED, -24, 24, 0);

        encoderDrive(DRIVE_SPEED, 24, 24, 0);



    }

    public void encoderDrive(double speed, double leftInches, double rightInches, double timeoutSeconds) {
        int newLeftTarget, newRightTarget;

        if(opModeIsActive()) {
            // finding target   position
            newLeftTarget = bot.ld.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = bot.rd.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);

            // setting target encoder position
            bot.ld.setTargetPosition(newLeftTarget);
            bot.rd.setTargetPosition(newRightTarget);

            // move to position
            bot.ld.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bot.rd.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // resetting timer and setting speed
            runtime.reset();
            bot.ld.setPower(Math.abs(speed));
            bot.rd.setPower(Math.abs(speed));


            while(opModeIsActive() && (runtime.seconds() < timeoutSeconds) &&
                    (bot.ld.isBusy() && bot.rd.isBusy())) {
                telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget, newRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d",
                        bot.ld.getCurrentPosition(), bot.rd.getCurrentPosition());

                telemetry.update();
            }

            // stopping motion
            bot.ld.setPower(0);
            bot.rd.setPower(0);

            // turn off RUN_TO_POSITION mode
            bot.ld.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bot.rd.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
}

