package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class LimelightSubsystem extends SubsystemBase{

    Limelight3A limelight;
    ServoEx servo;

    public LimelightSubsystem(HardwareMap hardwareMap){
        super();

        limelight= hardwareMap.get(Limelight3A.class, "LIMELIGHT");
        servo= hardwareMap.get(ServoEx.class, "SERVOCAMERA");
    }

    //functii pentru servo ul pt camera
}
