package com.team2073.robot;

import com.revrobotics.CANSparkMax;
import com.team2073.common.periodic.AsyncPeriodicRunnable;

public class IntermediateSubsystem implements AsyncPeriodicRunnable {
    private ApplicationContext appCTX = new ApplicationContext();
    private PicoColorSensor m_colorSensor = appCTX.getPicoColorSensor();
    private static IntermediateRollerState currentIntermediateRollerState = IntermediateRollerState.STOP;
    private final CANSparkMax motor1 = appCTX.getMotor1();
    private final CANSparkMax motor2 = appCTX.getMotor2();
    public IntermediateSubsystem(){
        currentIntermediateRollerState = IntermediateRollerState.DEFAULT;
        autoRegisterWithPeriodicRunner();
    }

    @Override
    public void onPeriodicAsync() {
        colorMotorOutput();
        switch(currentIntermediateRollerState) {
            case STOP:
                motor1.set(0); motor2.set(0);
                motor2.setIdleMode(CANSparkMax.IdleMode.kBrake);
                motor1.setIdleMode(CANSparkMax.IdleMode.kBrake);
                break;
            case INTAKE:
                motor1.set(0.6); motor2.set(0.6);
                break;
            case OUTTAKE:
                motor1.set(-0.1); motor2.set(-0.1);
                break;
            case DEFAULT:
                break;
        }
    }
    public static void setCurrentIntermediateRollerState(IntermediateRollerState currentIntermediateRollerState1) {
        currentIntermediateRollerState = currentIntermediateRollerState1;
    }
    public void colorMotorOutput(){
        IntermediateRollerState currentState;
        if(m_colorSensor.getRawColor0().red > m_colorSensor.getRawColor0().blue && m_colorSensor.getRawColor0().red > 350){
            currentState = IntermediateRollerState.INTAKE;
        }else if(m_colorSensor.getRawColor0().red < m_colorSensor.getRawColor0().blue && m_colorSensor.getRawColor0().blue > 350){
            currentState = IntermediateRollerState.OUTTAKE;
        }else {
            currentState = IntermediateRollerState.STOP;
        }
        currentIntermediateRollerState = currentState;
    }


    public enum IntermediateRollerState{
        STOP,
        INTAKE,
        OUTTAKE,
        DEFAULT
    }
}
