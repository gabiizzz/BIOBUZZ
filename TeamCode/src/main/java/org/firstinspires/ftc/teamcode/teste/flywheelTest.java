package org.firstinspires.ftc.teamcode.teste;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.Subsystems.TurretSubsystem;

@TeleOp
public class flywheelTest extends OpMode{

    TurretSubsystem tureta;
    double power=0;

    @Override
    public void init() {
        tureta= new TurretSubsystem(hardwareMap);

        telemetry.addData("Status:", "Initialized:D");
        telemetry.update();
    }

    @Override
    public void loop() {
//        if(gamepad1.dpadUpWasReleased()) power+=10;
//        if(gamepad1.dpadDownWasReleased()) power-=10;
//
//        tureta.mPolen.setVelocity(power);

        tureta.startPolenMotor();

        telemetry.addData("MAX VELOCITY", 2900);
        telemetry.addData("mPolen (Velocity)", tureta.mPolen.getVelocity());
        telemetry.update();
    }
}
