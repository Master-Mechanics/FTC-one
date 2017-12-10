package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Bot {

    public DcMotor ld  = null;
    public DcMotor rd  = null;
    public DcMotor arm = null;

    public Servo extend = null;
    public Servo lc = null;
    public Servo rc = null;
    public Servo jewel  = null;

    private HardwareMap hardwareMap = null;

    public void init(HardwareMap ahwMap) {
        hardwareMap = ahwMap;

        ld = hardwareMap.get(DcMotor.class, "left_drive");
        rd = hardwareMap.get(DcMotor.class, "right_drive");
        arm = hardwareMap.get(DcMotor.class, "front_arm");

        extend = hardwareMap.get(Servo.class, "extend_servo");
        lc = hardwareMap.get(Servo.class, "left_clamp");
        rc = hardwareMap.get(Servo.class, "right_clamp");
        jewel = hardwareMap.get(Servo.class, "jewel_servo");

        ld.setDirection(DcMotorSimple.Direction.REVERSE);
        rd.setDirection(DcMotorSimple.Direction.FORWARD);

    }
}
