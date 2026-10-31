package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem;
import java.util.function.Supplier;

public class RobotCentric extends CommandBase{

    DriveSubsystem driveSubsystem;
    Supplier<Double> strafeSpeed;
    Supplier<Double> forwardSpeed;
    Supplier<Double> turnSpeed;

    public RobotCentric(DriveSubsystem driveSubsystem, Supplier<Double> strafeSpeed, Supplier<Double> forwardSpeed, Supplier<Double> turnSpeed){
        this.driveSubsystem=driveSubsystem;
        this.strafeSpeed=strafeSpeed;
        this.forwardSpeed=forwardSpeed;
        this.turnSpeed=turnSpeed;

        addRequirements(driveSubsystem);
    }

    @Override
    public void execute(){
        driveSubsystem.driveRobotCentric(strafeSpeed.get(), forwardSpeed.get(), turnSpeed.get());
    }
}
