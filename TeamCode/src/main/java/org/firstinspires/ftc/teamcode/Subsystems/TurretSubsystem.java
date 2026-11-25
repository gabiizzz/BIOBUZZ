package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class TurretSubsystem extends SubsystemBase{

    public DcMotorEx mPolen, mNectar;
    ServoEx adjPolen, adjNectar;

    public TurretSubsystem(HardwareMap hardwareMap){
        super();

        mPolen= hardwareMap.get(DcMotorEx.class, "FLYWHEELPOLEN");
//        mNectar= hardwareMap.get(DcMotorEx.class, "FLYWHEELNECTAR");

//
//        adjPolen= hardwareMap.get(ServoEx.class, "AJUSTAREPOLEN");
//        adjNectar= hardwareMap.get(ServoEx.class, "AJUSTARENECTAR");
    }

    public void setPowerPolen(double pow){
        mPolen.setVelocity(pow);
    }

    public void startPolenMotor(){
        mPolen.setVelocity(2900);
    }

//    public void setPowerNectar(double pow){
//        mNectar.setVelocity(pow);
//    }

    //??
    /*public void shootPolen(){
        //valoare specifica shoot polen
        mPolen.setVelocity(0);
    }

    public void shootNectar(){
        //valoare specifica shoot nectar
        mNectar.setVelocity(0);
    }*/
//
//    public void setPosAdjPolen(double pos){
//        adjPolen.set(pos);
//    }
//
//    public void setPosAdjNectar(double pos){
//        adjNectar.set(pos);
//    }
}
