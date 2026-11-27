package org.firstinspires.ftc.teamcode.TeleOp;

import static android.os.Build.VERSION_CODES.P;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Commands.FieldCentric;
import org.firstinspires.ftc.teamcode.Commands.RobotCentric;
import org.firstinspires.ftc.teamcode.Subsystems.Constante;
import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LimelightSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.StopperSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TurretSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.FlowerSubsystem;

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
    FlowerSubsystem flower;
    //----------------------------------------------------------------------------------------------

    boolean start= false, shooting=false;

    double angle;

    @Override
    public void initialize(){

        flower = new FlowerSubsystem(hardwareMap);
        flower.initPosition();
        driveSubsystem= new DriveSubsystem(hardwareMap);
        turret = new TurretSubsystem(hardwareMap);
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(P, 0, 0, 0);
        gmp1= new GamepadEx(gamepad1);
        robotCentric= new RobotCentric(
                driveSubsystem,
                () -> -gmp1.getLeftX(),
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
        intake= new IntakeSubsystem(hardwareMap);
        stoppers= new StopperSubsystem(hardwareMap);
//        limelightServo= new LimelightSubsystem(hardwareMap);



        //-------------------------------------------controls---------------------------------------
        gmp1.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(new ParallelCommandGroup(
                        new InstantCommand(()-> stoppers.ALLOW()),
                        new InstantCommand(()-> shooting= true)
                ))
                .whenReleased(new ParallelCommandGroup(
                        new InstantCommand(()-> stoppers.STOP()),
                        new InstantCommand(()-> shooting= false)
                ));

        gmp1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(new ParallelCommandGroup(
                        new InstantCommand(()-> intake.take()),
                        new InstantCommand(()-> intake.setIntakePos(angle))
                ))
                .whenReleased(new ParallelCommandGroup(
                        new InstantCommand(()-> intake.stopIntake()),
                        new InstantCommand(()-> intake.setIntakePos(Constante.PUSH))
                ));

        gmp1.getGamepadButton(GamepadKeys.Button.PS).whenPressed(
                new SequentialCommandGroup(
                        new InstantCommand(() -> driveSubsystem.resetPinpoint()),
                        new WaitCommand(100),
                        new InstantCommand(() -> driveSubsystem.pinpoint.resetPosAndIMU())
                )
        );

        gmp1.getGamepadButton(GamepadKeys.Button.CIRCLE)
                .toggleWhenPressed(
                        new InstantCommand(() -> flower.openFlower()),
                        new InstantCommand(() -> flower.closeFlower())
                );

        driveSubsystem.pinpoint.resetPosAndIMU();

        telemetry.addData("Status", "INITIALIZED");
        telemetry.update();
    }

    @Override
    public void run(){
        super.run();
        if(!start) {
            start = true;
            schedule(new ParallelCommandGroup(
                    new InstantCommand(() -> intake.setIntakePos(Constante.PUSH)),
                    new InstantCommand(() -> stoppers.STOP()),
                    new InstantCommand(() -> turret.startPolenMotor())
            ));

        }

        if(shooting){
            angle= Constante.PUSH;
        } else{
            angle= Constante.TAKE;
        }

//        schedule(
//                new InstantCommand(()-> turret.startPolenMotor())
//        );
        telemetry.addData("Target Velocity", 4000);
        telemetry.addData("Current Velocity", turret.getPolenVelocity());
        telemetry.addData("Velocity Error", turret.getVelocityError());
        telemetry.update();
    }
}
