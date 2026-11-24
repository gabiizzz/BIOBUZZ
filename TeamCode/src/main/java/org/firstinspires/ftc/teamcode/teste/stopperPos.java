package org.firstinspires.ftc.teamcode.teste;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Constante;
import org.firstinspires.ftc.teamcode.Subsystems.StopperSubsystem;

@TeleOp(name = "STOPPERTEST")
@Disabled
public class stopperPos extends OpMode{
    StopperSubsystem stoppers;
    double pos=0;
    //STOPPERPOLEN=0.54 STOP 0.42 ALLOW
    //STOPPERNECTAR=0.42 STOP 0.09 ALLOW

    @Override
    public void init() {
        stoppers= new StopperSubsystem(hardwareMap);

        telemetry.addData("Status:", "Initialized:D");
        telemetry.update();
    }

    @Override
    public void loop() {
        if(gamepad1.dpadUpWasReleased()) pos+=0.01;
        if(gamepad1.dpadDownWasReleased()) pos-=0.01;

//        stoppers.stpPolen.set(pos);
//        stoppers.stpNectar.set(pos);

        if(gamepad1.rightBumperWasPressed()) stoppers.stpPolen.set(Constante.polenSTOP);
        if(gamepad1.rightBumperWasReleased()) stoppers.stpPolen.set(Constante.polenALLOW);

        if(gamepad1.leftBumperWasPressed()) stoppers.stpNectar.set(Constante.nectarSTOP);
        if(gamepad1.leftBumperWasReleased()) stoppers.stpNectar.set(Constante.nectarALLOW);

        telemetry.addData("stopper Nectar", stoppers.stpNectar.get());
        telemetry.addData("stopper Polen", stoppers.stpPolen.get());
        telemetry.addData("position val", pos);
        telemetry.update();
    }
}
