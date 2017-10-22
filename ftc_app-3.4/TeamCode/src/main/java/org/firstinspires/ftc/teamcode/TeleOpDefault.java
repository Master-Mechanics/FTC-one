package org.firstinspires.ftc.teamcode;

/**
 * Created by Arindum on 10/22/2017.
 */

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

@TeleOp(name="TeleOp")
public class TeleOpDefault extends OpMode{


    org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot robot = new HardwarePushbot();
    DcMotor ld, rd;

    enum Mode {
        tank,
        singleJoystick
    }

    Mode mainMode = Mode.singleJoystick;

    public void init() {
        robot.init(hardwareMap);
        ld = hardwareMap.get(DcMotor.class, "left_drive");
        rd = hardwareMap.get(DcMotor.class, "right_drive");
    }

    public void loop() {
        double  left_y  = gamepad1.left_stick_y,
                left_x  = gamepad1.left_stick_x,
                right_y = gamepad1.left_stick_y,
                right_x = gamepad1.right_stick_x,
                left = 0d,
                right = 0d;

        switch(mainMode)
        {
            case tank:
                if(left_y > 0 && right_y > 0)
                {
                    left    = (left_y + right_y) / 2;
                    right   = (left_y + right_y) / 2;
                }
                else if(left_y < 0 && right_y < 0)
                {
                    left    = (left_y + right_y) / 2;
                    right   = (left_y + right_y) / 2;
                }
                else if(left_y < 0 && right_y > 0)
                {
                    double avg = (Math.abs(left_y) + Math.abs(right_y)) / 2;
                    left = avg;
                    right = -1 * avg;
                }
                else if(left_y > 0 && right_y < 0)
                {
                    double avg = (Math.abs(left_y) + Math.abs(right_y)) / 2;
                    left = -1 * avg;
                    right = avg;
                }
                break;
            case singleJoystick:
                if((left_y > 0 || left_y < 0) && left_x == 0)
                {
                    left    = left_y;
                    right   = left_y;
                }
                else
                {
                    if(left_x > 0)
                    {
                        left    = left_y;
                        right   = -1 * left_y;
                    }
                    else if(left_x < 0)
                    {
                        left    = -1 * left_y;
                        right   = left_y;
                    }
                }
                break;
        }

        ld.setPower(left);
        rd.setPower(right);
    }
}
