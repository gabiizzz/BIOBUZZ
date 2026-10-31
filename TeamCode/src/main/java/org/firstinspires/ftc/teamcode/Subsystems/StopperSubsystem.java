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

    //--------------------------------------------POLEN---------------------------------------------
    public void stopPolen(){
        //variabila pt stop
        stpPolen.set(0);
    }

    public void allowPolen(){
        //variabila pt allow
        stpPolen.set(0);
    }

    //--------------------------------------------NECTAR--------------------------------------------
    public void stopNectar(){
        //variabila pt stop
        stpNectar.set(0);
    }

    public void allowNectar(){
        //variabila pt allow
        stpNectar.set(0);
    }

    ///sau efectiv punem valorile variabilelor in teleop si : 'stoppers.stopper_ales.set(pos)' as they re public objects
}
