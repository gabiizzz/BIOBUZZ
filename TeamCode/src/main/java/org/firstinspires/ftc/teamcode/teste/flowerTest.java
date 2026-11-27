package org.firstinspires.ftc.teamcode.teste;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.FlowerSubsystem;

@TeleOp
public class flowerTest extends OpMode {

    FlowerSubsystem flower;

    double pos = 0.5;

    @Override
    public void init() {
        flower = new FlowerSubsystem(hardwareMap);
        flower.initPosition();
    }

    @Override
    public void loop() {

        if (gamepad1.dpadUpWasReleased()) pos += 0.05;
        if (gamepad1.dpadDownWasReleased()) pos -= 0.05;

        pos = Math.max(0.0, Math.min(1.0, pos));

        flower.setPosForFlower(1.0 - pos);

        telemetry.addData("pos", pos);
        telemetry.addData("Flower servoPos", pos);

        telemetry.update();
    }
}
