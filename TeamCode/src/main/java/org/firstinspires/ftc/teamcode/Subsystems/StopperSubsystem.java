package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class StopperSubsystem extends SubsystemBase{

    public ServoEx stpPolen, stpNectar;

    public StopperSubsystem(HardwareMap hardwareMap){
        super();

        stpPolen= hardwareMap.get(ServoEx.class, "STOPPERPOLEN");
        stpNectar= hardwareMap.get(ServoEx.class, "STOPPERNECTAR");
    }

    public void setStopperPos(double pos){
        stpPolen.set(pos);
        stpNectar.set(pos);
    }
}
