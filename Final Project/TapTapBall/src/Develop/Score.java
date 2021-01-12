package Develop;

import java.io.Serializable;

public class Score implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    private int easy;
    private int medium;
    private int hard;

    public Score(){
        this(0,0,0);
    }

    public Score(int easy, int medium, int hard) {
        this.easy = easy;
        this.medium = medium;
        this.hard = hard;
    }

    public int getEasy() {
        return easy;
    }

    public void setEasy(int easy) {
        this.easy = easy;
    }

    public int getMedium() {
        return medium;
    }

    public void setMedium(int medium) {
        this.medium = medium;
    }

    public int getHard() {
        return hard;
    }

    public void setHard(int hard) {
        this.hard = hard;
    }
}
