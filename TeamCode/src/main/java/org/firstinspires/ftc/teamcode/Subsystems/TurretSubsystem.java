
package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class TurretSubsystem extends SubsystemBase {

    public DcMotorEx mPolen;
    // public DcMotorEx mNectar;

    ServoEx adjPolen, adjNectar;

    public TurretSubsystem(HardwareMap hardwareMap) {
        super();

        mPolen = hardwareMap.get(DcMotorEx.class, "FLYWHEELPOLEN");

        /*
        PIDFCoefficients pidfCoefficients =
                new PIDFCoefficients(0, 0, 0, 0);

        mPolen.setPIDFCoefficients(
                DcMotor.RunMode.RUN_USING_ENCODER,
                pidfCoefficients
        );
        */

        // mNectar = hardwareMap.get(DcMotorEx.class, "FLYWHEELNECTAR");

        /*
        adjPolen = hardwareMap.get(ServoEx.class, "AJUSTAREPOLEN");
        adjNectar = hardwareMap.get(ServoEx.class, "AJUSTARENECTAR");
        */
    }

    public void startPolenMotor() {
        mPolen.setVelocity(4000);
    }

    /*
    public void startNectarMotor() {
        mNectar.setVelocity(3000);
    }
    */

    public void startMotors() {
        startPolenMotor();

        // startNectarMotor();
    }

    /*
    public void setPosAdjPolen(double pos) {
        adjPolen.set(pos);
    }

    public void setPosAdjNectar(double pos) {
        adjNectar.set(pos);
    }
    */

    public double getPolenVelocity() {
        return mPolen.getVelocity();
    }

    public double getVelocityError() {
        return 4000 - mPolen.getVelocity();
    }
}
