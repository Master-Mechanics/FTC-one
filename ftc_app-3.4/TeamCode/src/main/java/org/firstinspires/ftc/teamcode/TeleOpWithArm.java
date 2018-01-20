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

    // B - gamepad 2
    private boolean armLock = false;

    public void init() {
        bot.init(hardwareMap);
    }

    public void loop() {

        //--- nuclear launch protocol ---//
        if (!pushed) {
            if (gamepad1.y && gamepad2.y) {
                bot.unfold();
                pushed = !pushed;
            }
        }

        double  drive = -gamepad1.right_stick_y / 2, turn = gamepad1.right_stick_x / 2;
        double straight = gamepad1.right_trigger / 2, back = gamepad1.left_trigger / 2;

        /*if(gamepad1.left_trigger > 0)
        {
            bot.jewel.setPosition(bot.jewelPosition - 0.05);
        }
        else if(gamepad1.right_trigger > 0)
        {
            bot.jewel.setPosition(bot.jewelPosition + 0.05);
        }*/

        // whole arm motor
        double armMovement = gamepad2.left_stick_y/3.5;
        if(!armLock) {
            bot.arm.setPower(armMovement);
        }

        // arm pivot server
        double pivot = gamepad2.right_stick_y;
        bot.pivotPosition = bot.pivot.getPosition();
        bot.pivot.setPosition(bot.pivotPosition + (pivot / 40));

        // open arm                close arm
        boolean open = gamepad2.a, close = gamepad2.x;
        if(close)
        {
            bot.clamp.setPosition(0);
        }
        else if(open)
        {
            bot.clamp.setPosition(.5);
        }

        boolean open2 = gamepad2.y, close2 = gamepad2.b;
        if(close2)
        {
            bot.pivot2.setPosition(-.05);
        }
        else if(open2)
        {
            bot.pivot2.setPosition(.3);
        }

        // left  trigger = drop
        double leftTrigger = gamepad2.left_trigger;

        // right trigger = bring back
        double rightTrigger = gamepad2.right_trigger;

        if(leftTrigger > rightTrigger)
        {
            bot.arm2.setPower(leftTrigger / 3 * -1);
        }
        else
        {
            bot.arm2.setPower(rightTrigger / 3);
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
            if(turn > 0)
            {
                bot.ld.setPower(Range.clip(turn, -1d, 1d));
                bot.rd.setPower(Range.clip(turn * -1, -1d, 1d));
            }
            else if(turn < 0)
            {
                bot.ld.setPower(Range.clip(turn * -1, -1d, 1d));
                bot.rd.setPower(Range.clip(turn, -1d, 1d));
            }
            else
            {
                bot.ld.setPower(Range.clip(drive, -1d, 1d));
                bot.rd.setPower(Range.clip(drive, -1d, 1d));
            }
            
            telemetry.addData("left drive power: ", bot.ld.getPowerFloat());
            telemetry.addData("right drive power: ", bot.rd.getPowerFloat());
        }

        if(gamepad1.y)
        {
            bot.jewel.setPosition(.8);
        }
        else if(gamepad1.b)
        {
            bot.jewel.setPosition(.25);
        }
    }
}
