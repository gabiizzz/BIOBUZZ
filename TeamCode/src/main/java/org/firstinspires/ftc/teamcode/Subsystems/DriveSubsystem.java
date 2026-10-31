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

        fl= hardwareMap.get(Motor.class, "FL" /*, rpm*/);
        fr= hardwareMap.get(Motor.class, "FR" /*, rpm*/);
        bl= hardwareMap.get(Motor.class, "BL" /*, rpm*/);
        br= hardwareMap.get(Motor.class, "BR" /*, rpm*/);

        fl.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        //inverted

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