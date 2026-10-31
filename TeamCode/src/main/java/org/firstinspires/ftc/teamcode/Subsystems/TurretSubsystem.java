package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class TurretSubsystem extends SubsystemBase{

    MotorEx mPolen, mNectar;
    ServoEx adjPolen, adjNectar;

    public TurretSubsystem(HardwareMap hardwareMap){
        super();

        mPolen= hardwareMap.get(MotorEx.class, "FLYWHEELPOLEN");
        mNectar= hardwareMap.get(MotorEx.class, "FLYWHEELNECTAR");

        mPolen.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        mNectar.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        adjPolen= hardwareMap.get(ServoEx.class, "AJUSTAREPOLEN");
        adjNectar= hardwareMap.get(ServoEx.class, "AJUSTARENECTAR");
    }

    public void setPowerPolen(double pow){
        mPolen.setVelocity(pow);
    }

    public void setPowerNectar(double pow){
        mNectar.setVelocity(pow);
    }

    //??
    /*public void shootPolen(){
        //valoare specifica shoot polen
        mPolen.setVelocity(0);
    }

    public void shootNectar(){
        //valoare specifica shoot nectar
        mNectar.setVelocity(0);
    }*/

    public void setPosAdjPolen(double pos){
        adjPolen.set(pos);
    }

    public void setPosAdjNectar(double pos){
        adjNectar.set(pos);
    }
}
