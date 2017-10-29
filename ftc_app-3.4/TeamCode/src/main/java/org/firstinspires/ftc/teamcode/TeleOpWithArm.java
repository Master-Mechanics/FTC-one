package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="TeleOp w/ Arm")
public class TeleOpWithArm extends OpMode{

    DcMotor ld = null, rd = null, arm = null;

    public void init() {
        ld = hardwareMap.get(DcMotor.class, "left_drive");
        rd = hardwareMap.get(DcMotor.class, "right_drive");
        arm = hardwareMap.get(DcMotor.class, "front_arm");

        ld.setDirection(DcMotorSimple.Direction.REVERSE);
        rd.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void loop() {
        double  drive = -gamepad1.right_stick_y, turn = gamepad1.right_stick_x;


        ld.setPower(Range.clip(drive + turn, -1d, 1d));
        rd.setPower(Range.clip(drive - turn, -1d, 1d));

        arm.setPower(gamepad1.left_stick_y * .2);
    }
}
