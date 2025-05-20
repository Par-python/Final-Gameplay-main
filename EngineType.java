public class EngineType {

    private String engineType;
    private double acceleration, speed, lessTopSpeed;
    private int gear;
    private boolean moneyShift;

    public EngineType(String engineType){
        this.engineType = engineType;
        acceleration = 0;
        speed = 0;
        gear = 1;
        moneyShift = false;
        lessTopSpeed = 0;
    }

    public void checkEngine(){
        if (this.engineType == "ShortCake Core"){
            ShortCakeSpeed();
        } else if (this.engineType == "Matcha Core"){
            MatchaSpeed();
        } else if (this.engineType == "ChocolateOverload Core"){
            ChocoloateSpeed();
        }
    }

    public void gearShiftU(){
        gear+=1;
    }

    public void gearShiftD(){
        gear-=1;
    }

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

    public void ShortCakeMoneyShift(){
          double[] maxSpeed = {0, 60, 90, 120, 150, 170, 190};

          speed = maxSpeed[(int) GameFrame.getGearUpdate()];
    }
    
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

    public void MatchaMoneyShift(){
          double[] maxSpeed = {0, 70, 100, 130, 160, 180, 205};

          speed = maxSpeed[(int) GameFrame.getGearUpdate()];
    }

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

    public void CholocateMoneyShift(){
          double[] maxSpeed = {0, 90, 120, 150, 180, 200, 225};

          speed = maxSpeed[(int) GameFrame.getGearUpdate()];
    }

    public void tubroIsActivated(){
        lessTopSpeed -=30;
    }

    public boolean isMoneyShift(){        
        return moneyShift;
    }

    public void resetMoneyShift(){
        moneyShift = false;
    }


    public double getAccelerationFinal(){
        return acceleration;
    }

    public int getGear(){
        return gear;
    }

    public double getSpeed(){
        return speed;
    }

    public String getEngineType(){
        return engineType;
    }
}