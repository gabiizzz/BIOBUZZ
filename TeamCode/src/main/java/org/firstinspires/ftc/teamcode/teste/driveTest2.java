package org.firstinspires.ftc.teamcode.teste;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import org.firstinspires.ftc.teamcode.Commands.RobotCentric;
import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem;

@TeleOp
public class driveTest2 extends CommandOpMode{
//teleop dar fara celelalte subsisteme
    DriveSubsystem driveSubsystem;
    RobotCentric robotCentric;
    GamepadEx gmp1;

    @Override
    public void initialize() {
        driveSubsystem= new DriveSubsystem(hardwareMap);
        gmp1= new GamepadEx(gamepad1);
        robotCentric= new RobotCentric(
                driveSubsystem,
                gmp1::getLeftX,
                gmp1::getLeftY,
                gmp1::getRightX
        );
        driveSubsystem.setDefaultCommand(robotCentric);
    }

    @Override
    public void run() {
        super.run();
    }
}
