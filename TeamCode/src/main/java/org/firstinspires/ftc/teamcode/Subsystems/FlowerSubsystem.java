package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;
import com.qualcomm.robotcore.hardware.Servo;

public class FlowerSubsystem extends SubsystemBase {

    private ServoEx flower;

    public FlowerSubsystem(HardwareMap hardwareMap) {
        super();

        flower = new ServoEx(hardwareMap, "FLOWER");

    }

    public void setPosForFlower(double pos) {
        flower.set(pos);
    }

    public void initPosition() {
        flower.set(0.4);
    }

    public void openFlower() {
        flower.set(0.95);
    }

    public void closeFlower() {
        flower.set(0.4);
    }
}
