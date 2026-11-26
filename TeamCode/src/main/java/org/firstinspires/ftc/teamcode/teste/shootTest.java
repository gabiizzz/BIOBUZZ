package org.firstinspires.ftc.teamcode.teste;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TurretSubsystem;

@TeleOp
public class shootTest extends OpMode{

    IntakeSubsystem intake;
    TurretSubsystem tureta;

    @Override
    public void init() {
        intake= new IntakeSubsystem(hardwareMap);
        tureta= new TurretSubsystem(hardwareMap);
    }

    @Override
    public void loop() {
        intake.mIntake2.set(1);
        tureta.mPolen.setVelocity(3000);
    }
}
