package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Bot {

    public DcMotor ld = null;
    public DcMotor rd = null;
    public DcMotor arm = null;

    private HardwareMap hardwareMap = null;

    public void init(HardwareMap ahwMap) {
        hardwareMap = ahwMap;

        ld = hardwareMap.get(DcMotor.class, "left_drive");
        rd = hardwareMap.get(DcMotor.class, "right_drive");
        arm = hardwareMap.get(DcMotor.class, "front_arm");

        ld.setDirection(DcMotorSimple.Direction.REVERSE);
        rd.setDirection(DcMotorSimple.Direction.FORWARD);

    }
}
