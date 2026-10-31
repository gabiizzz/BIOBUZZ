package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class IntakeSubsystem extends SubsystemBase{

    Motor mIntake;
    ServoEx flower, intakeLeft, intakeRight;

    public IntakeSubsystem(HardwareMap hardwareMap){
        super();

        mIntake= hardwareMap.get(Motor.class, "INTAKE" /*,rpm*/);
        mIntake.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        flower= hardwareMap.get(ServoEx.class, "FLOWER");
        intakeLeft= hardwareMap.get(ServoEx.class, "INTLEFT");
        intakeRight= hardwareMap.get(ServoEx.class, "INTRIGHT");

        //inverted
    }

    public void setPower(double power){
        mIntake.set(power);
    }

    public void setPosForFlower(double pos){
        flower.set(pos);
    }

    //functii pentru servo urile de la intake
}
