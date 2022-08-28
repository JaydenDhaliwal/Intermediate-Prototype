package com.team2073.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.Joystick;

public class ApplicationContext {
    private static ApplicationContext instance;
    private static CANSparkMax motor1;
    private static CANSparkMax motor2;
    private static CANSparkMax motor3;
    private static Joystick controller;
    private static Joystick driveStick;
    private static OperatorInterface oi;
    private static PicoColorSensor m_colorSensor;
    private static IntermediateSubsystem intermediateSubsystem;

    public static ApplicationContext getInstance() {
        if (instance == null) {
            instance = new ApplicationContext();
        }
        return instance;
    }

    public CANSparkMax getMotor1() {
        if (motor1 == null) {
            motor1 = new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless);
        }
        return motor1;
    }

    public CANSparkMax getMotor2() {
        if (motor2 == null) {
            motor2 = new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless);
        }
        return motor2;
    }

    public CANSparkMax getMotor3() {
        if (motor3 == null) {
            motor3 = new CANSparkMax(10, CANSparkMaxLowLevel.MotorType.kBrushless);
        }
        return motor3;
    }

    public Joystick getController() {
        if (controller == null) {
            controller = new Joystick(2);
        }
        return controller;
    }


    public Joystick getDriveStick() {
        if (driveStick == null) {
            driveStick = new Joystick(3);
        }
        return driveStick;
    }

    public OperatorInterface getOperatorInterface() {
        if (oi == null) {
            oi = new OperatorInterface();
        }
        return oi;
    }

    public PicoColorSensor getPicoColorSensor(){
        if(m_colorSensor == null){
            m_colorSensor = new PicoColorSensor();
        }
        return m_colorSensor;
    }

    public IntermediateSubsystem getIntermediateSubsystem() {
        if (intermediateSubsystem == null){
            intermediateSubsystem = new IntermediateSubsystem();
        }
        return intermediateSubsystem;
    }
}
