package org.firstinspires.ftc.teamcode;

import android.graphics.Color;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import org.firstinspires.ftc.robotcore.external.JavaUtil;

//sensor test
@TeleOp
public class sensor extends OpMode{
    NormalizedColorSensor sensor;
    double hue;

    ServoEx servo;
    double position=0;

    @Override
    public void init() {
        sensor = hardwareMap.get(NormalizedColorSensor.class, "sensor_color");
        servo= hardwareMap.get(ServoEx.class, "servo");
    }

    @Override
    public void loop() {
        NormalizedRGBA colors = sensor.getNormalizedColors();
        hue = JavaUtil.colorToHue(colors.toColor());

        final float[] hsvValues = new float[3];

        Color.colorToHSV(colors.toColor(), hsvValues);

        telemetry.addLine()
                .addData("Red", "%.3f", colors.red)
                .addData("Green", "%.3f", colors.green)
                .addData("Blue", "%.3f", colors.blue);
        telemetry.addLine()
                .addData("Hue", "%.3f", hsvValues[0])
                .addData("Saturation", "%.3f", hsvValues[1])
                .addData("Value", "%.3f", hsvValues[2]);
        telemetry.addData("Alpha", "%.3f", colors.alpha);

        if(hue < 30){
            telemetry.addData("Color", "Red");
        }
//        else if (hue < 60) {
//            telemetry.addData("Color", "Orange");
//        }
        else if (hue < 90){
            telemetry.addData("Color", "Yellow");
        }
//        else if (hue < 150){
//            telemetry.addData("Color", "Green");
//        }
        else if (hue < 225){
            telemetry.addData("Color", "Blue");
        }
//        else if (hue < 350){
//            telemetry.addData("Color", "Purple");
//        }
        else{
            telemetry.addData("Color", "Red");
        }
        telemetry.update();
    }
}
