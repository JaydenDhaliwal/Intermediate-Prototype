package com.team2073.robot;

import com.revrobotics.CANSparkMax;
import com.team2073.common.periodic.AsyncPeriodicRunnable;
import edu.wpi.first.wpilibj.CAN;

public class IntermediateSubsystem implements AsyncPeriodicRunnable {
    private ApplicationContext appCTX = new ApplicationContext();
    private PicoColorSensor m_colorSensor = appCTX.getPicoColorSensor();
    private IntermediateRollerState currentIntermediateRollerState = IntermediateRollerState.STOP;
    private final CANSparkMax motor1 = appCTX.getMotor1();
    private final CANSparkMax motor2 = appCTX.getMotor2();
    public IntermediateSubsystem(){
        // currentIntermediateRollerState = IntermediateRollerState.STOP;
        autoRegisterWithPeriodicRunner();
    }

    @Override
    public void onPeriodicAsync() {
        System.out.println("Current Intermediate State: " + currentIntermediateRollerState);
        switch(currentIntermediateRollerState) {
            case STOP:
                motor1.set(0); motor2.set(0);
                break;
            case INTAKE:
                motor1.set(-0.6); motor2.set(-0.6);
                break;
            case OUTTAKE:
                motor1.set(0.1); motor2.set(0.1);
                break;
        }
    }

    public void setCurrentIntermediateRollerState(IntermediateRollerState currentIntermediateRollerState1) {
        currentIntermediateRollerState = currentIntermediateRollerState1;
    }

    public enum IntermediateRollerState{
        STOP,
        INTAKE,
        OUTTAKE
    }
}