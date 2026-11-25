package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class DriveSubsystem extends SubsystemBase{

    MecanumDrive mecanum;
    public GoBildaPinpointDriver pinpoint;
    public Motor fl, fr, bl, br;

    public DriveSubsystem(HardwareMap hardwareMap){
        super();

        pinpoint= hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");
        pinpoint.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.REVERSED, GoBildaPinpointDriver.EncoderDirection.FORWARD);
        pinpoint.setOffsets(0, 0, DistanceUnit.INCH);

        pinpoint.resetPosAndIMU();
        pinpoint.recalibrateIMU();

        fl= new Motor(hardwareMap, "FL");
        fr= new Motor(hardwareMap, "FR");
        bl= new Motor(hardwareMap, "BL");
        br= new Motor(hardwareMap, "BR");

        fl.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        fl.setInverted(true);
        bl.setInverted(true);

        this.mecanum= new MecanumDrive(false, fl, fr, bl, br);
    }

    public void resetPINPOIN(){
        pinpoint.resetPosAndIMU();
        pinpoint.recalibrateIMU();
    }

    //------------------------------------------drive-----------------------------------------------
    public void driveRobotCentric(double strafeS, double forwardS, double turnS){
        mecanum.driveRobotCentric(strafeS, forwardS, turnS);
    }

    public void driveFieldCentric(double strafeS, double forwardS, double turnS, double angles){
        mecanum.driveFieldCentric(strafeS, forwardS, turnS, angles, false);
    }
}