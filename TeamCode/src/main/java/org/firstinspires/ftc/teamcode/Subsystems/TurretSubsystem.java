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
        mNectar= hardwareMap.get(DcMotorEx.class, "FLYWHEELNECTAR");

//
//        adjPolen= hardwareMap.get(ServoEx.class, "AJUSTAREPOLEN");
//        adjNectar= hardwareMap.get(ServoEx.class, "AJUSTARENECTAR");
    }

    public void startPolenMotor(){
        mPolen.setVelocity(2900);
    }
    public void startNectarMotor(){
        mNectar.setVelocity(3000);
    }

    public void startMotors(){
        startPolenMotor();
        startNectarMotor();
    }

//    public void setPosAdjPolen(double pos){
//        adjPolen.set(pos);
//    }
//
//    public void setPosAdjNectar(double pos){
//        adjNectar.set(pos);
//    }
}
