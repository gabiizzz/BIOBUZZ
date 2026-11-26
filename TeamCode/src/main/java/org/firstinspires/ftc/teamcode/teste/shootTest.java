package org.firstinspires.ftc.teamcode.teste;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

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
        if (gamepad1.circle) {
            intake.mIntake2.set(1);
            tureta.mPolen.setVelocity(4000);
        }
        else if(gamepad1.square) {
            intake.mIntake2.set(1);
            tureta.mNectar.setVelocity(3000);
        }
        else if(gamepad1.triangle){
            intake.mIntake2.set(0);
            tureta.mPolen.setVelocity(0);
            tureta.mNectar.setVelocity(0);
        }
    }
}
