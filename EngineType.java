/**
 * The EngineType class manages the different types of engines and their performance characteristics.
 * It handles acceleration rates, gear shifting, and money shift mechanics for each engine type.
 * 
 * @author Lance Arnel G. Camacho (245288)
 * @author Jerome John C. Pardo (246268)
 * @version 20 May 2025
 * 
 * I have not discussed the Java language code in my program
 * with anyone other than my instructor or the teaching assistants
 * assigned to this course.
 * 
 * I have not used Java language code obtained from another student,
 * or any other unauthorized source, either modified or unmodified.
 * If any Java language code or documentation used in my program
 * was obtained from another source, such as a textbook or website,
 * that has been clearly noted with a proper citation in the comments
 * of my program.
 */
public class EngineType {

    private String engineType;
    private double acceleration, speed, lessTopSpeed;
    private int gear;
    private boolean moneyShift;

    /**
     * Creates a new engine type with the specified name and initializes its properties.
     * 
     * @param engineType the name of the engine type to create
     */
    public EngineType(String engineType){
        this.engineType = engineType;
        acceleration = 0;
        speed = 0;
        gear = 1;
        moneyShift = false;
        lessTopSpeed = 0;
    }

    /**
     * Determines and sets the appropriate acceleration rate based on the engine type.
     */
    public void checkEngine(){
        if (this.engineType == "ShortCake Core"){
            ShortCakeSpeed();
        } else if (this.engineType == "Matcha Core"){
            MatchaSpeed();
        } else if (this.engineType == "ChocolateOverload Core"){
            ChocoloateSpeed();
        }
    }

    /**
     * Increases the current gear by one.
     */
    public void gearShiftU(){
        gear+=1;
    }

    /**
     * Decreases the current gear by one.
     */
    public void gearShiftD(){
        gear-=1;
    }

    /**
     * Sets the acceleration rates and handles money shift for Shortcake Core.
     */
    public void ShortCakeSpeed(){

        // acceleration speeds 
        if(GameFrame.getSpeed() <= 60 && GameFrame.getGearUpdate() == 1){
            acceleration = 0.7;
        } else if ( GameFrame.getSpeed() <= 90 && GameFrame.getGearUpdate() == 2){
            acceleration = 0.6;
        } else if(GameFrame.getSpeed() <= 120 && GameFrame.getGearUpdate() == 3) {
            acceleration = 0.5;
        }  else if(GameFrame.getSpeed() <= 150 && GameFrame.getGearUpdate() == 4) {
            acceleration = 0.3;
        } else if(GameFrame.getSpeed() <= 170 && GameFrame.getGearUpdate() == 5){
            acceleration = 0.2;
        } else if(GameFrame.getSpeed() <= 190 - lessTopSpeed && GameFrame.getGearUpdate() == 6){
            acceleration = 0.1;
        }else {
            acceleration = 0;
        }

        //managing downshifting and moneyshift
        if(GameFrame.getGearUpdate() < GameFrame.getPrevGear()){
              double[] maxSpeed = {0, 60, 90, 120, 150, 170, 190};

            if(GameFrame.getSpeed() > maxSpeed[(int) GameFrame.getGearUpdate()]){
                moneyShift = true;
                ShortCakeMoneyShift();
            }
        }
    }

    /**
     * Handles money shift speed adjustment for Shortcake Core.
     */
    public void ShortCakeMoneyShift(){
          double[] maxSpeed = {0, 60, 90, 120, 150, 170, 190};

          speed = maxSpeed[(int) GameFrame.getGearUpdate()];
    }
    
    /**
     * Sets the acceleration rates and handles money shift for Matcha Core.
     */
    public void MatchaSpeed(){

        // acceleration speeds 
        if(GameFrame.getSpeed() <= 70 && GameFrame.getGearUpdate() == 1){
            acceleration = 0.6;
        } else if ( GameFrame.getSpeed() <= 100 && GameFrame.getGearUpdate() == 2){
            acceleration = 0.3;
        } else if(GameFrame.getSpeed() <= 130 && GameFrame.getGearUpdate() == 3) {
            acceleration = 0.2;
        }  else if(GameFrame.getSpeed() <= 160 && GameFrame.getGearUpdate() == 4) {
            acceleration = 0.1;
        } else if(GameFrame.getSpeed() <= 180 && GameFrame.getGearUpdate() == 5){
            acceleration = 0.050;
        } else if(GameFrame.getSpeed() <= 205 - lessTopSpeed && GameFrame.getGearUpdate() == 6){
            acceleration = 0.025;
        }else {
            acceleration = 0;
        }

        //managing downshifting and moneyshift
        if(GameFrame.getGearUpdate() < GameFrame.getPrevGear()){
              double[] maxSpeed = {0, 70, 100, 130, 160, 180, 205};

            if(GameFrame.getSpeed() > maxSpeed[(int) GameFrame.getGearUpdate()]){
                moneyShift = true;
                MatchaMoneyShift();
            }
        }
    }

    /**
     * Handles money shift speed adjustment for Matcha Core.
     */
    public void MatchaMoneyShift(){
          double[] maxSpeed = {0, 70, 100, 130, 160, 180, 205};

          speed = maxSpeed[(int) GameFrame.getGearUpdate()];
    }

    /**
     * Sets the acceleration rates and handles money shift for Chocolate Overload Core.
     */
    public void ChocoloateSpeed(){

        // acceleration speeds 
        if(GameFrame.getSpeed() <= 90 && GameFrame.getGearUpdate() == 1){
            acceleration = 0.7;
        } else if ( GameFrame.getSpeed() <= 120 && GameFrame.getGearUpdate() == 2){
            acceleration = 0.3;
        } else if(GameFrame.getSpeed() <= 150 && GameFrame.getGearUpdate() == 3) {
            acceleration = 0.2;
        }  else if(GameFrame.getSpeed() <= 180 && GameFrame.getGearUpdate() == 4) {
            acceleration = 0.15;
        } else if(GameFrame.getSpeed() <= 200 && GameFrame.getGearUpdate() == 5){
            acceleration = 0.1;
        } else if(GameFrame.getSpeed() <= 225 - lessTopSpeed && GameFrame.getGearUpdate() == 6){
            acceleration = 0.025;
        }else {
            acceleration = 0;
        }

        //managing downshifting and moneyshift
        if(GameFrame.getGearUpdate() < GameFrame.getPrevGear()){
              double[] maxSpeed = {0, 90, 120, 150, 180, 200, 225};

            if(GameFrame.getSpeed() > maxSpeed[(int) GameFrame.getGearUpdate()]){
                moneyShift = true;
                CholocateMoneyShift();
            }
        }
    }

    /**
     * Handles money shift speed adjustment for Chocolate Overload Core.
     */
    public void CholocateMoneyShift(){
          double[] maxSpeed = {0, 90, 120, 150, 180, 200, 225};

          speed = maxSpeed[(int) GameFrame.getGearUpdate()];
    }

    /**
     * Activates turbo mode by reducing top speed limit.
     */
    public void tubroIsActivated(){
        lessTopSpeed -=30;
    }

    /**
     * Checks if a money shift has occurred.
     * 
     * @return true if a money shift has occurred
     */
    public boolean isMoneyShift(){        
        return moneyShift;
    }

    /**
     * Resets the money shift state to false.
     */
    public void resetMoneyShift(){
        moneyShift = false;
    }

    /**
     * Returns the current acceleration value.
     * 
     * @return the current acceleration value
     */
    public double getAccelerationFinal(){
        return acceleration;
    }

    /**
     * Returns the current gear.
     * 
     * @return the current gear number
     */
    public int getGear(){
        return gear;
    }

    /**
     * Returns the current speed.
     * 
     * @return the current speed value
     */
    public double getSpeed(){
        return speed;
    }

    /**
     * Returns the name of the current engine type.
     * 
     * @return the name of the engine type
     */
    public String getEngineType(){
        return engineType;
    }
}