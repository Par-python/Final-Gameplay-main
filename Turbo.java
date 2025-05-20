/**
 * The Turbo class manages the turbo boost mechanics in the game.
 * It handles turbo activation, acceleration boost, and turbo state management.
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
public class Turbo {
    private double addedTurbo;
    private boolean isTurboActive;

    /**
     * Initializes the turbo system with default values.
     */
    public Turbo(){
        addedTurbo = 0;
        isTurboActive = false;
    }

    /**
     * Activates the turbo boost and sets the acceleration value.
     */
    public void addedTubroAcceleration(){
        addedTurbo = 0.5;
        isTurboActive = true;
    }

    /**
     * Checks if the turbo is currently active.
     * 
     * @return true if turbo is active
     */
    public boolean isTurboActive(){
        return isTurboActive;
    }

    /**
     * Activates turbo and returns the boost value.
     * 
     * @return the turbo boost acceleration value
     */
    public double getTurboBurst(){
        addedTubroAcceleration();
        return addedTurbo;
    }
}
