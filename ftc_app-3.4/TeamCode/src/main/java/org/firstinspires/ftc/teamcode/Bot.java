package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Bot {

    public DcMotor ld  = null;
    public DcMotor rd  = null;
    public DcMotor arm = null;

    public CRServo extend = null;
    public Servo clamp = null;
    //public Servo rc = null;
    public Servo jewel  = null;

    public ColorSensor colorSensor = null;

    public String vuLicense = "AZdKRPL/////AAAAGdWHky5ZjET7mNUJ+qxkIDtvddP9PIXKwsMwBNDXV0SrsOuioPqAv1q7EB2k1QqsJd3eIb8WIXmHt4fxm153DMq6AEpg0L8nuqQuMmzxL9nFfM2ubV6PVSoZcdQqPOwtLpJqk4KUjKc8v0Es0M0aZl7R8a+jyzDiobaKVE9+4EY1dKuRPTB1OYCqCKfn1OkULrWxH6zSzwyf6ztPp6cKFn+oaglcxzX9iOhejsX4CsoAg4X/6HLnWWrux1Z4hfVP2EKanjrXqeSm5iursV0Lu+HRKMvo9VbIzGnit7JDusQsI7JVEpVsRnR39GaTJT1W1jijOVaw7VVkCt/7D9U+z5JcjgzOoug+Mye9H6n020MW";

    private HardwareMap hardwareMap = null;

    public void init(HardwareMap ahwMap) {
        hardwareMap = ahwMap;

        ld = hardwareMap.get(DcMotor.class, "left_drive");
        rd = hardwareMap.get(DcMotor.class, "right_drive");
        arm = hardwareMap.get(DcMotor.class, "front_arm");

        extend = hardwareMap.get(CRServo.class, "extend_servo");
        clamp = hardwareMap.get(Servo.class,  "clamp");
        //rc = hardwareMap.get(Servo.class, "right_clamp");
        jewel = hardwareMap.get(Servo.class, "jewel_servo");

        colorSensor = hardwareMap.get(ColorSensor.class, "color");

        ld.setDirection(DcMotorSimple.Direction.REVERSE);
        rd.setDirection(DcMotorSimple.Direction.FORWARD);

        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}
