package org.firstinspires.ftc.teamcode.teste;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.Commands.RobotCentric;
import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem;

@TeleOp
@Disabled
public class driveTest extends OpMode{
//pt config
//    public Motor motor;
    DriveSubsystem driveSubsystem;
    RobotCentric robotCentric;
    GamepadEx gmp1;

    @Override
    public void init() {
//        motor= new Motor(hardwareMap, "motor");
//        motor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        driveSubsystem= new DriveSubsystem(hardwareMap);


    }

    @Override
    public void loop() {
//        motor.set(1);
        if(gamepad1.triangle) driveSubsystem.fl.set(1);
        else driveSubsystem.fl.set(0);
        if(gamepad1.square) driveSubsystem.bl.set(1);
        else driveSubsystem.bl.set(0);
        if(gamepad1.circle) driveSubsystem.fr.set(1);
        else driveSubsystem.fr.set(0);
        if(gamepad1.cross) driveSubsystem.br.set(1);
        else driveSubsystem.br.set(0);
     }
}
