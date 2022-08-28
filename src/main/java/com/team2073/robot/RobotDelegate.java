package com.team2073.robot;

import com.revrobotics.CANSparkMax;
import com.team2073.common.robot.AbstractRobotDelegate;
import com.team2073.common.util.Timer;

public class RobotDelegate extends AbstractRobotDelegate {
    private Timer t = new Timer();

    private ApplicationContext appCTX = ApplicationContext.getInstance();

    private CANSparkMax motor1 = appCTX.getMotor1();

    private CANSparkMax motor2 = appCTX.getMotor2();

    private CANSparkMax motor3 = appCTX.getMotor3();

    private OperatorInterface oi = new OperatorInterface();

    private IntermediateSubsystem intermediateSubsystem = appCTX.getIntermediateSubsystem();

    public RobotDelegate(double period) {
        super(period);
    }

    @Override
    public void robotInit() {
          motor3.set(0.4);
    }

    @Override
    public void robotPeriodic() {
    }

    @Override
    public void teleopInit() { }

    @Override
    public void autonomousInit() { }

    @Override
    public void autonomousPeriodic() { }



}
