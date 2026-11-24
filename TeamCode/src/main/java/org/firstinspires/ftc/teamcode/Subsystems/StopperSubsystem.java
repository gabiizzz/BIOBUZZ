package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class StopperSubsystem extends SubsystemBase{

    public ServoEx stpPolen, stpNectar;

    public StopperSubsystem(HardwareMap hardwareMap){
        super();

        stpPolen= new ServoEx(hardwareMap, "STOPPERPOLEN");
        stpNectar= new ServoEx(hardwareMap, "STOPPERNECTAR");

        stpNectar.setInverted(true);
    }

//    public void setStopperPos(double pos){
//        stpPolen.set(pos);
//        stpNectar.set(pos);
//    }

    public void STOP(){
        stpPolen.set(Constante.polenSTOP);
        stpNectar.set(Constante.nectarSTOP);
    }

    public void ALLOW(){
        stpPolen.set(Constante.polenALLOW);
        stpNectar.set(Constante.nectarALLOW);
    }
}
