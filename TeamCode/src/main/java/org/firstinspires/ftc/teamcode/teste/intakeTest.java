package org.firstinspires.ftc.teamcode.teste;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Constante;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

@TeleOp
public class intakeTest extends OpMode{

    IntakeSubsystem intake;

    double pos=0, pos2=0;

    @Override
    public void init() {
        intake= new IntakeSubsystem(hardwareMap);
    }

    @Override
    public void loop() {
        if(gamepad1.circle) intake.mIntake.set(1);
        else intake.mIntake.set(0);

        if(gamepad1.square) intake.mIntake2.set(1);
        else intake.mIntake2.set(0);

        if(gamepad1.dpadUpWasReleased()) pos+=0.05;
        if(gamepad1.dpadDownWasReleased()) pos-=0.05;

        if(gamepad1.dpadRightWasReleased()) pos2+=0.05;
        if(gamepad1.dpadLeftWasReleased()) pos2-=0.05;

        intake.intakeLeft.set(pos);
        intake.intakeRight.set(pos2);

        telemetry.addData("pos", pos);
        telemetry.addData("pos2", pos2);
        telemetry.addData("Left servoPos", intake.intakeLeft.get());
        telemetry.addData("Right servoPos", intake.intakeRight.get());
        telemetry.update();
    }
}
