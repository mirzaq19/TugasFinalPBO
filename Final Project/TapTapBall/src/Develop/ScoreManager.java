package Develop;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScoreManager {
    private static ObjectInputStream SaveInput;
    private static ObjectOutputStream SaveOutput;
    public static int easyScore;
    public static int mediumScore;
    public static int hardScore;
    private Score score;
    private int currentEasy;
    private int currentMedium;
    private int currentHard;

    public ScoreManager(){
        currentEasy = easyScore = 0;
        currentMedium = mediumScore = 0;
        currentHard = hardScore = 0;
    }

    public void loadScore(){
        try {
            SaveInput = new ObjectInputStream(Files.newInputStream(Paths.get("Highscore.ser")));

            Score scr = (Score) SaveInput.readObject();
            currentEasy = easyScore = scr.getEasy();
            currentMedium = mediumScore = scr.getMedium();
            currentHard = hardScore = scr.getHard();
            SaveInput.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            System.out.println("No save data");
        }
    }

    public void SaveScore(){
        try {
            SaveOutput = new ObjectOutputStream(Files.newOutputStream(Paths.get("Highscore.ser")));
            score = new Score(getCurrentEasy(),getCurrentMedium(),getCurrentHard());
            SaveOutput.writeObject(score);
        } catch (IOException e) {
            System.out.println("Error Opening File");
            e.printStackTrace();
        }
    }

    public int getCurrentEasy() {
        return currentEasy;
    }

    public void setCurrentEasy(int currentEasy) {
        this.currentEasy = currentEasy;
        ScoreManager.easyScore = currentEasy;
    }

    public int getCurrentMedium() {
        return currentMedium;
    }

    public void setCurrentMedium(int currentMedium) {
        this.currentMedium = currentMedium;
        ScoreManager.mediumScore = currentMedium;
    }

    public int getCurrentHard() {
        return currentHard;
    }

    public void setCurrentHard(int currentHard) {
        this.currentHard = currentHard;
        ScoreManager.hardScore = currentHard;
    }
}
