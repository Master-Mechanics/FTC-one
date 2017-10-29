package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;
import com.sun.tools.javac.util.ForwardingDiagnosticFormatter;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

@TeleOp(name="TeleOp")
public class TeleOpDefault extends OpMode{

    DcMotor ld = null, rd = null;

    public void init() {
        ld = hardwareMap.get(DcMotor.class, "left_drive");
        rd = hardwareMap.get(DcMotor.class, "right_drive");

        ld.setDirection(DcMotorSimple.Direction.REVERSE);
        rd.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void loop() {
        double drive = -gamepad1.left_stick_y, turn = gamepad1.left_stick_x;

        ld.setPower(Range.clip(drive + turn, -1d, 1d));
        rd.setPower(Range.clip(drive - turn, -1d, 1d));
    }
}
