package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="TeleOp w/ Arm")
public class TeleOpWithArm extends OpMode{

    Bot bot;

    public void init() {
        bot.init(hardwareMap);
    }

    public void loop() {
        double  drive = -gamepad1.right_stick_y, turn = gamepad1.right_stick_x;

        bot.ld.setPower(Range.clip(drive + turn, -1d, 1d));
        bot.rd.setPower(Range.clip(drive - turn, -1d, 1d));

        bot.arm.setPower(gamepad1.left_stick_y * .2);
    }
}
