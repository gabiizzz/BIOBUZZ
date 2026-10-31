package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem;

import java.util.function.Supplier;

public class FieldCentric extends CommandBase {

    DriveSubsystem driveSubsystem;
    Supplier<Double> strafeSpeed;
    Supplier<Double> forwardSpeed;
    Supplier<Double> turnSpeed;

    public FieldCentric(DriveSubsystem driveSubsystem, Supplier<Double> strafeSpeed, Supplier<Double> forwardSpeed, Supplier<Double> turnSpeed){
        this.driveSubsystem=driveSubsystem;
        this.strafeSpeed=strafeSpeed;
        this.forwardSpeed=forwardSpeed;
        this.turnSpeed=turnSpeed;

        addRequirements(driveSubsystem);
    }

    @Override
    public void execute(){
        driveSubsystem.pinpoint.update();
        driveSubsystem.driveFieldCentric(strafeSpeed.get(), forwardSpeed.get(), turnSpeed.get(), driveSubsystem.pinpoint.getHeading(AngleUnit.DEGREES));
    }
}
