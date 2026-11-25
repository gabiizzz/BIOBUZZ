package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Commands.FieldCentric;
import org.firstinspires.ftc.teamcode.Commands.RobotCentric;
import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LimelightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.StopperSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TurretSubsystem;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends CommandOpMode{

    //-------------------------------------------drive----------------------------------------------
    DriveSubsystem driveSubsystem;
    RobotCentric robotCentric;
    //FieldCentric fieldCentric;
    GamepadEx gmp1;

    //----------------------------------------subsistems--------------------------------------------
    IntakeSubsystem intake;
    StopperSubsystem stoppers;
    LimelightSubsystem limelightServo;
    TurretSubsystem turret;
    //----------------------------------------------------------------------------------------------

    boolean start= false;

    @Override
    public void initialize(){

        driveSubsystem= new DriveSubsystem(hardwareMap);
        gmp1= new GamepadEx(gamepad1);
        robotCentric= new RobotCentric(
                driveSubsystem,
                gmp1::getLeftX,
                gmp1::getLeftY,
                gmp1::getRightX
        );
//        /*fieldCentric= new FieldCentric(
//                driveSubsystem,
//                gmp1::getLeftX,
//                gmp1::getLeftY,
//                gmp1::getRightX
//        );*/
        driveSubsystem.setDefaultCommand(robotCentric);
//
//        intake= new IntakeSubsystem(hardwareMap);
        stoppers= new StopperSubsystem(hardwareMap);
//        limelightServo= new LimelightSubsystem(hardwareMap);
        turret= new TurretSubsystem(hardwareMap);

        //-------------------------------------------controls---------------------------------------
        gmp1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(new InstantCommand(()-> stoppers.ALLOW()))
                .whenReleased(new InstantCommand(()-> stoppers.STOP()));

        telemetry.addData("Status", "INITIALIZED");
        telemetry.update();
    }

    @Override
    public void run(){
        super.run();

        if(!start) {
            start = true;
            schedule(new InstantCommand(() -> stoppers.STOP()));
        }

        schedule(
                new InstantCommand(()-> turret.startPolenMotor())
        );
    }
}
