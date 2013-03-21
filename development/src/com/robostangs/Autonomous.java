package com.robostangs;

import com.sun.squawk.io.BufferedReader;
import com.sun.squawk.microedition.io.FileConnection;
import edu.wpi.first.wpilibj.Timer;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.microedition.io.Connector;

/**
 * All functionality is read from dash; if that fails, runs a fallback mode
 * @author sky
 */

public class Autonomous {
    private static Autonomous instance = null;    
    private static Timer timer;
    private static boolean driving = false;
    private static boolean ingesting = false;
    private static boolean shooting = false;
    private static boolean turning = false;
    private static boolean delay = false;
    private static boolean armMoving = false;
    private static boolean fallbackMode = false;
    private static boolean init = true;
    private static String[] keys;
    private static double[] stepData;
    private static double timeForStep = 0.0;
    private static int status = 0;
    private static boolean gyroReady = false;
    private static double angle = 0;
    private static int step = 0;
    
    //Read From TXT
    private static String inputFileName = "C:\\Users\\input.txt"; //CHANGE
    private static String line;
    private static String contents = "";
    private static int commaPos = 0;
    private static int semiPos = 0;
    private static String constantName = "";
    private static int driveTime, driveAngle, turnTime, turnAngle, armAngle, shootTime, shootNum;
    
    private Autonomous() {
        timer = new Timer();
        try {
            getInfo();
        } catch (IOException ex) {
            ex.printStackTrace();
            fallbackMode = true;
        }
        checkInfo();
    }
    
    public static Autonomous getInstance() {
        if (instance == null) {
            instance = new Autonomous();
        }
        return instance;
    }

    /**
     * Sets up the keys array with all the keys from the dash
     * Also sets variables not related to steps
     */
    public static void getInfo() throws IOException{
        FileConnection fc = (FileConnection) Connector.open(inputFileName);
        BufferedReader in = new BufferedReader(new InputStreamReader(fc.openInputStream()));
        
        while ((line = in.readLine()) != null) {
            contents+=line;
        }

        fc.close();
        keys = new String[contents.length()];
        stepData = new double[contents.length()];
        
        for (int i = 0; i < contents.length(); i++) {
            commaPos = contents.indexOf(",", i);
            semiPos = contents.indexOf(";", i);            
            constantName = contents.substring(i, commaPos);

            keys[i] = constantName;
            stepData[i] = Double.parseDouble(contents.substring(commaPos + 1, semiPos));
            
            i = semiPos;
        }
    }
    

    public static void printKeys() {
        for (int i = 0; i < keys.length; i++) {
            System.out.println(keys[i]);
        }
    }
    
    /**
     * Checks to see if data read from dash is valid
     * @return true if data is good
     */
    public static void checkInfo() {
        //TODO: more fallback mode checks?
        if (keys == null) {
            fallbackMode = true;
        } else if (keys.length < 2) {
            fallbackMode = true;
        }
    }
    
    public static void run() {
        if (!fallbackMode) {
            for (int i = 0; i < keys.length; i++) {    
                driving = keys[i].endsWith("Distance");
                turning = keys[i].endsWith("Turn");
                ingesting = keys[i].startsWith("ingest");
                shooting = keys[i].startsWith("shoot");
                armMoving = keys[i].startsWith("arm");
                delay = keys[i].startsWith("delay");

                status = 0;
                determineAngle();
                timer.start();

                if (driving && !ingesting) {
                    while (status == 0 && timer.get() < timeForStep) {
                        /*
                        status = DriveTrain.driveStraight(Constants.AUTON_DRIVE_POWER, angle, 
                                stepData[i]);
                                * */
                    }

                    gyroReady = false;
                    DriveTrain.stop();
                    timer.stop();
                    timer.reset();

                    if (status != 1) {
                        Log.write("Auton Step Incomplete " + keys[i] + "Return code: " + status);
                    }

                } else if (driving && ingesting) {
                    while (status == 0 && timer.get() < timeForStep) {
                        /*
                        status = DriveTrain.driveStraight(Constants.AUTON_DRIVE_POWER, angle, 
                                stepData[i]);
                                * */
                        Loader.ingest();
                    }

                    Loader.allOff();
                    DriveTrain.stop();
                    gyroReady = false;
                    timer.stop();
                    timer.reset();

                    if (status != 1) {
                        Log.write("Auton Step Incomplete " + keys[i] + "Return code: " + status);
                    }

                } else if (turning) {
                    while (status == 0 && timer.get() < timeForStep) {
                        //status = DriveTrain.turn(Constants.AUTON_TURN_POWER, stepData[i]);
                    }

                    gyroReady = false;
                    DriveTrain.stop();
                    timer.stop();
                    timer.reset();

                    if (status != 1) {
                        Log.write("Auton Step Incomplete " + keys[i] + "Return code: " + status);
                    }

                } else if (shooting) {
                    while (status == 0 && timer.get() < timeForStep) {
                        status = Shooter.shoot( (int) stepData[i]);
                        Loader.loadShooter();
                    }

                    gyroReady = false;
                    Loader.allOff();
                    Shooter.stop();
                    timer.stop();
                    timer.reset();

                    if (status != 1) { 
                        Log.write("Auton Step Incomplete " + keys[i] + "Return code: " + status);
                    }

                } else if (armMoving) {
                    while (status == 0 && timer.get() < timeForStep) {
                        if (stepData[i] == Constants.AUTON_ARM_LOW_POS) {
                            status = Arm.lowestPos();
                        } else if (stepData[i] == Constants.AUTON_ARM_UNDER_PYRAMID_POS) {
                            status = Arm.sidePyramidPos();
                        } else if (stepData[i] != 0 && stepData[i] != -1) {
                            status = Arm.setPosition(stepData[i]);
                        } else if (stepData[i] == -1) {
                            //status = Arm.camPos();
                        }
                    }

                    Arm.stop();
                    timer.stop();
                    timer.reset();

                    if (status != 1) { 
                        Log.write("Auton Step Incomplete " + keys[i] + "Return code: " + status);
                    }

                } else if (delay) {
                    while (timer.get() < stepData[i]);

                    timer.stop();
                    timer.reset();
                }

            }
        } else {
            //fallbackMode();
            shootThree();
        }
    }
    
    /**
     * determine the angle using gyro
     */
    public static void determineAngle() {
        if (!gyroReady) {
            //angle = DriveTrain.getAngle();
            gyroReady = true;
        }
    }
    
    /**
     * if fall to get info from the driver's station, backup plan
     */
    public static void fallbackMode() {
        //drive, turn, arm, shoot
        switch (step) {
            case 0:
                timer.start();
                while (timer.get() < Constants.AUTON_FALLBACK_DRIVE_TIME) {
                    //DriveTrain.driveStraight(Constants.AUTON_DRIVE_POWER, Constants.AUTON_DRIVE_ANGLE);
                }
                
                DriveTrain.stop();
                timer.stop();
                timer.reset();
                step++;
                break;
            case 1:
                timer.start();
                while (timer.get() < Constants.AUTON_FALLBACK_TURN_TIME) {
                    //DriveTrain.turn(Constants.AUTON_TURN_POWER, Constants.AUTON_TURN_ANGLE);
                }
                
                DriveTrain.stop();
                timer.stop();
                timer.reset();
                step++;
                break;
            case 2:
                timer.start();
                while (timer.get() < Constants.AUTON_FALLBACK_ARM_MOVE_TIME) {
                    Arm.setPosition(Constants.AUTON_ARM_POS);
                }
                
                Arm.stop();
                timer.stop();
                timer.reset();
                step++;
                break;
            case 3:
                timer.start();
                while (timer.get() < Constants.AUTON_FALLBACK_SHOOT_TIME) {
                    Shooter.shoot(Constants.AUTON_SHOOT_DISC_NUM);
                }
                
                Shooter.stop();
                timer.stop();
                timer.reset();
                break;
            default:
                break;
        }
    }

    public static void shootThree() {
        if (init) {
            timer.stop();
            timer.reset();
            System.out.println("timer init: " + timer.get());
            timer.start();
            init = false;
        }
        System.out.println("step timer: " + timer.get() + " " + step);
        Shooter.shoot();

        if (timer.get() > 2.5) {
            Loader.loadShooter();
        }

        if (timer.get() > 4.0) {
            Loader.allOff();
            timer.reset();
        }
    }
    
}
