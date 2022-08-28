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

    IntermediateRollerState currentState;

    String c1;

    String c2;

    private IntermediateSubsystem intermediateSubsystem = appCTX.getIntermediateSubsystem();
    boolean timerFinished = true;

    public RobotDelegate(double period) {
        super(period);
    }

    @Override
    public void robotInit() {
          motor3.set(0.4);
    }

    @Override
    public void robotPeriodic() {
        if(m_colorSensor.getRawColor0().red > m_colorSensor.getRawColor0().blue && m_colorSensor.getRawColor0().red > 350){
            currentState = IntermediateRollerState.INTAKE;
            System.out.println("Intake");
        }else if(m_colorSensor.getRawColor0().red < m_colorSensor.getRawColor0().blue && m_colorSensor.getRawColor0().blue > 350){
            currentState = IntermediateRollerState.OUTTAKE;
            System.out.println("outtake");
        }else {
            currentState = IntermediateRollerState.STOP;
            System.out.println("stop");
        }
        intermediateSubsystem.setCurrentIntermediateRollerState(currentState);
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
