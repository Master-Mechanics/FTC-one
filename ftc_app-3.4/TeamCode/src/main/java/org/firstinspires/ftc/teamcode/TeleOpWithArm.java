package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="TeleOp w/ Arm")
public class TeleOpWithArm extends OpMode{

    private Bot bot = new Bot();

    private final double g = .3d;

    public void init() {
        bot.init(hardwareMap);
    }

    public void loop() {
        double  drive = -gamepad1.right_stick_y, turn = gamepad1.right_stick_x;
        double straight = gamepad1.right_trigger, back = gamepad1.left_trigger;

        //bot.arm.setPower(gamepad2.left_stick_y * .2);

        double armMovement = (gamepad2.right_stick_y == 0) ? g : gamepad2.right_stick_y;
        double armExtend = gamepad2.left_stick_y;

        double clamp = (gamepad2.left_stick_y == 0) ? g : gamepad2.right_stick_y;
        boolean a = (gamepad2.a == true) ? g : gamepad2.a;

        bot.arm.setPower(armMovement);
        telemetry.addData("Arm Power: ", armMovement);

        bot.extend.setPower(armExtend);
        telemetry.addData("Arm Extension Power: ", armExtend);

        bot.clamp.setPower(clamp);
        bot.clamp1.setPower(-clamp);

        if(straight > 0)
        {
            bot.ld.setPower(straight);
            bot.rd.setPower(straight);
            telemetry.addData("driving straight: ", "true");
        }
        else if(back > 0)
        {
            bot.ld.setPower(back * -1);
            bot.rd.setPower(back * -1);
            telemetry.addData("driving backwards: ", "true");
        }
        else if( a == true)
        {
            
        }
        else
        {
            bot.ld.setPower(Range.clip(drive + turn, -1d, 1d));
            bot.rd.setPower(Range.clip(drive - turn, -1d, 1d));
            telemetry.addData("left drive power: ", bot.ld.getPowerFloat());
            telemetry.addData("right drive power: ", bot.rd.getPowerFloat());
        }
    }
}
