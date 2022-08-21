package com.team2073.robot;

import com.revrobotics.CANSparkMax;
import com.team2073.common.robot.AbstractRobotDelegate;
import com.team2073.common.util.Timer;
import com.team2073.robot.IntermediateSubsystem.IntermediateRollerState;
import edu.wpi.first.wpilibj.CAN;

public class RobotDelegate extends AbstractRobotDelegate {
    private Timer t = new Timer();

    private ApplicationContext appCTX = ApplicationContext.getInstance();

    private CANSparkMax motor1 = appCTX.getMotor1();

    private CANSparkMax motor2 = appCTX.getMotor2();

    private CANSparkMax motor3 = appCTX.getMotor3();

    private OperatorInterface oi = new OperatorInterface();

    private PicoColorSensor m_colorSensor = appCTX.getPicoColorSensor();

    private IntermediateSubsystem intermediateSubsystem = appCTX.getIntermediateSubsystem();
    boolean timerFinished = true;

    public RobotDelegate(double period) {
        super(period);
    }

    @Override
    public void robotInit() {
        intermediateSubsystem.setCurrentIntermediateRollerState(IntermediateSubsystem.IntermediateRollerState.STOP);
    }

    @Override
    public void robotPeriodic() {
        IntermediateRollerState currentState = IntermediateRollerState.STOP;
        if(m_colorSensor.getRawColor0().blue < 310){
            if (currentState != IntermediateRollerState.OUTTAKE && timerFinished) {
                timerFinished = false;
                t.start();
                currentState = IntermediateRollerState.OUTTAKE;
            }
            // intermediateSubsystem.setCurrentIntermediateRollerState(IntermediateSubsystem.IntermediateRollerState.OUTTAKE);
            System.out.println("o");
        } else if(m_colorSensor.getRawColor0().blue >= 310 && m_colorSensor.getRawColor0().blue < 700){
            if (currentState != IntermediateRollerState.INTAKE && timerFinished) {
                timerFinished = false;
                t.start();
                currentState = IntermediateRollerState.INTAKE;
            }
            System.out.println("i");
        }else {
            if(timerFinished){
                currentState = IntermediateRollerState.STOP;
            }
        }

        if (t.getElapsedTime() >= 1000) {
            t.stop();
            timerFinished = true;
        }

        if (t.getElapsedTime() <= 1000 && !timerFinished) {
            intermediateSubsystem.setCurrentIntermediateRollerState(currentState);
        }

        System.out.println(m_colorSensor.getRawColor0().blue);
    }

    @Override
    public void teleopInit() { }

    @Override
    public void autonomousInit() { }

    @Override
    public void autonomousPeriodic() { }

    public void startTimer() {
        t.start();
    }

}
