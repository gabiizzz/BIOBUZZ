package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class IntakeSubsystem extends SubsystemBase{

    public Motor mIntake, mIntake2;
    public ServoEx flower, intakeLeft, intakeRight;

    public IntakeSubsystem(HardwareMap hardwareMap){
        super();

        mIntake= new Motor(hardwareMap, "INTAKE");
        mIntake.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        mIntake.setInverted(true);

        mIntake2= new Motor(hardwareMap, "INTAKE2");//transfer
        mIntake2.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        mIntake2.setInverted(true);

//        flower= hardwareMap.get(ServoEx.class, "FLOWER");
        intakeLeft= new ServoEx(hardwareMap, "INTLEFT");
//        intakeRight= new ServoEx(hardwareMap, "INTRIGHT");
//        intakeRight.setInverted(true);
    }

    public void take(){
        mIntake.set(1);
        mIntake2.set(1);
    }

    public void stopIntake(){
        mIntake.set(0);
        mIntake2.set(0);
    }

    public void setIntakePos(double pos){
        intakeLeft.set(pos);
    }

//    public void setPosForFlower(double pos){
//        flower.set(pos);
//    }
}
