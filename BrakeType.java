/**
 * The BrakeType class manages the different types of brakes and their deceleration values.
 * It provides methods to set and retrieve brake-specific deceleration rates for the game mechanics.
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
public class BrakeType {

    private String brakeType;
    private double decceleration;

    /**
     * Creates a new brake type with the specified name.
     * 
     * @param brakeType the name of the brake type to create
     */
    public BrakeType(String brakeType){
        this.brakeType = brakeType;
        decceleration = 0;
    }

    /**
     * Determines and sets the appropriate deceleration rate based on the brake type.
     */
    public void checkBrake(){
        if (this.brakeType == "Candy Brakes"){
            CandyBrakesSpeed();
            
        } else if (this.brakeType == "Stripe Brakes"){
            StripeBrakesSpeed();
        } else if (this.brakeType == "Magic Brakes"){
            MagicBrakesSpeed();
        }
    }

    /**
     * Sets the deceleration rate for Candy Brakes.
     */
    public void CandyBrakesSpeed(){
        decceleration = 2;
    }

    /**
     * Sets the deceleration rate for Stripe Brakes.
     */
    public void StripeBrakesSpeed(){
       decceleration = 0.25;
    }

    /**
     * Sets the deceleration rate for Magic Brakes.
     */
    public void MagicBrakesSpeed(){
        decceleration = 0.05;
    }

    /**
     * Returns the current deceleration value.
     * 
     * @return the deceleration value for the selected brake type
     */
    public double getDeccelerationFinal(){
        return decceleration;
    }

    /**
     * Returns the name of the current brake type.
     * 
     * @return the name of the brake type
     */
    public String getBrakeType(){
        return brakeType;
    }
}
