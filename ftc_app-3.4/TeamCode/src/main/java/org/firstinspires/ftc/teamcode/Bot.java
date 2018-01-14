package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Bot {

    public DcMotor ld  = null;
    public DcMotor rd  = null;
    public DcMotor arm = null;
    public DcMotor arm2 = null;

    public Servo pivot = null;
    public Servo clamp = null;
    public Servo clamp2 = null;
    public Servo armTop = null;
    public Servo jewel  = null;

    public ColorSensor colorSensor = null;

    public double pivotPosition = 0d;

    public String vuLicense = "AZdKRPL/////AAAAGdWHky5ZjET7mNUJ+qxkIDtvddP9PIXKwsMwBNDXV0SrsOuioPqAv1q7EB2k1QqsJd3eIb8WIXmHt4fxm153DMq6AEpg0L8nuqQuMmzxL9nFfM2ubV6PVSoZcdQqPOwtLpJqk4KUjKc8v0Es0M0aZl7R8a+jyzDiobaKVE9+4EY1dKuRPTB1OYCqCKfn1OkULrWxH6zSzwyf6ztPp6cKFn+oaglcxzX9iOhejsX4CsoAg4X/6HLnWWrux1Z4hfVP2EKanjrXqeSm5iursV0Lu+HRKMvo9VbIzGnit7JDusQsI7JVEpVsRnR39GaTJT1W1jijOVaw7VVkCt/7D9U+z5JcjgzOoug+Mye9H6n020MW";

    private HardwareMap hardwareMap = null;

    public void init(HardwareMap ahwMap) {
        hardwareMap = ahwMap;

        ld = hardwareMap.get(DcMotor.class, "left_drive");
        rd = hardwareMap.get(DcMotor.class, "right_drive");
        arm = hardwareMap.get(DcMotor.class, "front_arm");
        arm2 = hardwareMap.get(DcMotor.class, "front_arm");

        pivot = hardwareMap.get(Servo.class, "pivot");
        clamp = hardwareMap.get(Servo.class,  "clamp");
        clamp2 = hardwareMap.get(Servo.class,  "clamp2");
        armTop = hardwareMap.get(Servo.class, "arm_top")
        jewel = hardwareMap.get(Servo.class, "jewel_servo");

        colorSensor = hardwareMap.get(ColorSensor.class, "color");

        pivotPosition = pivot.getPosition();

        ld.setDirection(DcMotorSimple.Direction.REVERSE);
        rd.setDirection(DcMotorSimple.Direction.FORWARD);

        ld.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rd.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void unfold()
    {
        try {
            jewel.setPosition(100 / 360);
            pivot.setPosition(35 / 360);

            arm.setPower(.7);

            wait(1000);

            arm.setPower(0);

            arm2.setPower(-.7);

            wait(1000);

            arm2.setPower(0);
        }
        catch(Exception e){ }
    }
}
