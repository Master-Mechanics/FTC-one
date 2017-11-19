package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="AutoEncoder")
public class AutoEncoder extends LinearOpMode {

    Bot bot = new Bot();

    static final double COUNTS_PER_MOTOR_REV    = 1440;
    static final double DRIVE_GEAR_REDUCTION    = 2.0;
    static final double WHEEL_DIAMETER_INCHES   = 5.0;
    static final double COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION)
                                                        / (WHEEL_DIAMETER_INCHES * Math.PI);

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


        // wait 'til the drive hits start
        waitForStart();

        encoderDrive(DRIVE_SPEED,  48,  48, 5.0);
    }

    public void encoderDrive(double speed, double leftInches, double rightInches, double timeoutSeconds) {
        int newLeftTarget, newRightTarget;

        if(opModeIsActive()) {
            // finding target position
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
