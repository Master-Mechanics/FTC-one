package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="TeleOp w/ Arm")
public class TeleOpWithArm extends OpMode{

    private Bot bot = new Bot();

    // supposedly the force of gravity according to the best source
    // the robot that we made... meaning... AI HAS TAKEN OVER
    private final double g = .3d;

    private boolean pushed = false;

    public void init() {
        bot.init(hardwareMap);
    }

    public void loop() {

        //--- nuclear launch protocol ---//
        if(!pushed)
        {
            if(gamepad1.y && gamepad2.y)
            {
                bot.unfold();
                pushed = !pushed;
            }
        }


        double  drive = -gamepad1.right_stick_y, turn = gamepad1.right_stick_x;
        double straight = gamepad1.right_trigger, back = gamepad1.left_trigger;

        // whole arm motor
        double armMovement = gamepad2.right_stick_y;
        bot.arm.setPower(armMovement);

        // arm pivot server
        double pivot = gamepad2.left_stick_y;
        bot.pivot.setPosition(bot.pivotPosition + pivot);
        bot.pivotPosition = bot.pivot.getPosition();

        // open arm                close arm
        boolean open = gamepad2.a, close = gamepad2.x;
        if(close)
        {
            bot.clamp.setPosition(0);
            bot.clamp2.setPosition(0);
        }
        else if(open)
        {
            bot.clamp.setPosition(.5);
            bot.clamp2.setPosition(.5);
        }

        // left  trigger = drop
        double leftTrigger = gamepad2.left_trigger;

        // right trigger = bring back
        double rightTrigger = gamepad2.right_trigger;

        if(leftTrigger > rightTrigger)
        {
            bot.arm2.setPower(leftTrigger * -1);
        }
        else
        {
            bot.arm2.setPower(rightTrigger);
        }

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
        else
        {
            bot.ld.setPower(Range.clip(drive + turn, -1d, 1d));
            bot.rd.setPower(Range.clip(drive - turn, -1d, 1d));
            telemetry.addData("left drive power: ", bot.ld.getPowerFloat());
            telemetry.addData("right drive power: ", bot.rd.getPowerFloat());
        }
    }
}
