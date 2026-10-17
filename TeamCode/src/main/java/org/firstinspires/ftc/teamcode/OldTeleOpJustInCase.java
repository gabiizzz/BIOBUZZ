package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp", group = "TeleOp")
@SuppressWarnings("unused")
public class OldTeleOpJustInCase extends OpMode{
    //-----------------------------------------hardware stuff---------------------------------------
    DcMotor frontLeft, frontRight, backLeft, backRight;
    DcMotor sh1, sh2;
    Servo RS, LS;
    Servo stopper;
    Servo adjust;
    DcMotor intL, intR;
    IMU imu;

    //--------------------------------------variabile + timer---------------------------------------
    double TAKE= 0.71;//0.73
    double IDLE= 0.74;//0.77
    private ElapsedTime timer;
    double ALLOW= 0;
    double STOP= 0.27;
    double maxFlywheelPower= 0.8;
    double servoPos= 0.15;

    //----------------------------------------unused stuff------------------------------------------
    //extendo
//    double PUSH= 0.64;
//    double robotX, robotY, heading= Math.toRadians(0);
//    Pose2D pose;
//    double dx, dy, distance;
//    double goalX= 130, goalY= 130;
    double position= 0;
//    double pos=0.5;

    //----------------------------------------------------------------------------------------------

    @Override
    public void init() {
        //---------------------------------------sasiu----------------------------------------------
        frontLeft= hardwareMap.get(DcMotorEx.class, "FL");
        frontRight= hardwareMap.get(DcMotor.class, "FR");
        backLeft= hardwareMap.get(DcMotor.class, "BL");
        backRight= hardwareMap.get(DcMotor.class, "BR");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //-----------------------------------------extendo------------------------------------------
        RS= hardwareMap.get(Servo.class, "RS");
        LS= hardwareMap.get(Servo.class, "LS");

        LS.setDirection(Servo.Direction.REVERSE);

        //-----------------------------------------intake-------------------------------------------
        intL= hardwareMap.get(DcMotor.class, "iL");
        intR= hardwareMap.get(DcMotor.class, "iR");

        //-----------------------------------------timer--------------------------------------------
        timer= new ElapsedTime();

        //------------------------------------------imu---------------------------------------------
        imu= hardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection =
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT;
        RevHubOrientationOnRobot.UsbFacingDirection usbDirection =
                RevHubOrientationOnRobot.UsbFacingDirection.UP;

        RevHubOrientationOnRobot orientationOnRobot = new
                RevHubOrientationOnRobot(logoDirection, usbDirection);
        imu.initialize(new IMU.Parameters(orientationOnRobot));

        //-----------------------------------------outtake------------------------------------------
        sh1= hardwareMap.get(DcMotor.class, "sh1");
        sh2= hardwareMap.get(DcMotor.class, "sh2");

        sh1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        sh2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        sh1.setDirection(DcMotorSimple.Direction.REVERSE);

        //-----------------------------------------stopper------------------------------------------
        stopper= hardwareMap.get(Servo.class, "stopper");

        adjust= hardwareMap.get(Servo.class, "adjust");
        //------------------------------------------------------------------------------------------

        telemetry.addData("Status:", "Initialized:D");
        telemetry.update();
    }



    //-----------------------------------------drive------------------------------------------------
    public void drive(double forwardSpeed, double strafeSpeed, double turnSpeed){
        frontLeft.setPower(forwardSpeed + strafeSpeed + turnSpeed);
        frontRight.setPower(forwardSpeed - strafeSpeed - turnSpeed);
        backLeft.setPower(forwardSpeed - strafeSpeed + turnSpeed);
        backRight.setPower(forwardSpeed + strafeSpeed - turnSpeed);
    }

    public void driveFieldCentric(double newForward, double newStrafe, double turn){
        drive(newForward, newStrafe, turn);
    }

    //-----------------------------------------extendo position-------------------------------------
    public void setPos(double pos){
        RS.setPosition(pos);
        LS.setPosition(pos);
    }

    //-----------------------------------------intake power-----------------------------------------
//    public void setPow(double pow){
//        intL.setPower(pow);
//        intR.setPower(pow);
//    }

    //-----------------------------------------flywheel power---------------------------------------
    public void shoot(double pow){
        sh1.setPower(pow);
        sh2.setPower(pow);
    }

    //--------------------------------------------wait----------------------------------------------
    public void waitCommand(double millis){
        timer.reset();
        while(timer.milliseconds() < millis);
        timer.reset();
    }

    //----------------------------------------------------------------------------------------------
    public void setStopperPos(double pos){
        stopper.setPosition(pos);
    }

    //---------------------------------------------shoot thread-------------------------------------
    Thread t = new Thread(() -> {

        shoot(maxFlywheelPower);
        setStopperPos(ALLOW);
        waitCommand(2000);
//        Thread.sleep(2000);
        intR.setPower(0.8);
        intL.setPower(0.8);
        setPos(TAKE);
        waitCommand(1250);
        shoot(0);
        intR.setPower(0);
        intL.setPower(0);
        setStopperPos(STOP);
        setPos(IDLE);
    });


    //----------------------------------------------------------------------------------------------

    @Override
    public void start(){
        super.start();

        setPos(IDLE);
        setStopperPos(STOP);
        adjust.setPosition(servoPos);
    }



    @Override
    public void loop() {
        //-----------------------------------------drive stuff--------------------------------------

        double forwardSpeed=0, strafeSpeed=0, turnSpeed=0, heading;

        forwardSpeed-= gamepad1.left_stick_y;
        strafeSpeed+= gamepad1.left_stick_x;
        turnSpeed+= gamepad1.right_stick_x;

        //field centric
        heading= imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double newForward= strafeSpeed * Math.sin(-heading) + forwardSpeed * Math.cos(-heading);
        double newStrafe= strafeSpeed * Math.cos(-heading) - forwardSpeed * Math.sin(-heading);

        if(gamepad1.ps) imu.resetYaw();

        drive(forwardSpeed, strafeSpeed, turnSpeed);

        //-------------------------------------------intake-----------------------------------------
        if(gamepad1.right_bumper){
            setPos(TAKE);
            intR.setPower(1);
            intL.setPower(0.1);
        }else if(gamepad1.rightBumperWasReleased()){
            setPos(IDLE);
            intR.setPower(0);
            intL.setPower(0);
        }

        //-------------------------------------------shoot------------------------------------------
        if(gamepad1.leftBumperWasReleased() && !t.isAlive()){
            t.start();
        }

        //------------------------------------------------------------------------------------------
//        if(gamepad1.ps) imu.resetYaw();
//
//        driveFieldCentric(newForward, newStrafe, turnSpeed);

//        if(gamepad1.circleWasReleased()) position+=0.01;
//        if(gamepad1.squareWasReleased()) position-=0.01;
//        stopper.setPosition(position);


        if(gamepad1.dpadUpWasReleased()) maxFlywheelPower+=0.1;
        if(gamepad1.dpadDownWasReleased()) maxFlywheelPower-=0.1;
//
        if(gamepad1.dpadRightWasReleased()) servoPos+=0.15;
        if(gamepad1.dpadLeftWasReleased()) servoPos-=0.15;
        adjust.setPosition(servoPos);

//        if(gamepad1.circleWasReleased()) position+=0.01;
//        if(gamepad1.squareWasReleased()) position-=0.01;
//        setPos(position);

        //------------------------------------------------------------------------------------------
        telemetry.addData("random", "shadow pls nu exploda");

        telemetry.addData(" ", " ");

        telemetry.addData("LS", LS.getPosition());
        telemetry.addData("RS", RS.getPosition());

        telemetry.addData(" ", " ");

        telemetry.addData("stopper", stopper.getPosition());

        telemetry.addData("maxFlywheelPower", maxFlywheelPower);
        telemetry.addData("mmmm", sh1.getPower());
        telemetry.addData("mmmm", sh2.getPower());

        telemetry.addData(" ", " ");

        telemetry.addData("servoPos", adjust.getPosition());
        telemetry.addData("servoPos variabila", servoPos);
//
//        telemetry.addData(" ", " ");

        telemetry.addData("thread(shoot)", t.isAlive());
        telemetry.update();
    }
}
