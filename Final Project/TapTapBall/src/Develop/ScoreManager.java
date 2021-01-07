package Develop;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ScoreManager {
    public static int easyScore;
    public static int mediumScore;
    public static int hardScore;
    private int currentEasy;
    private int currentMedium;
    private int currentHard;
    private String filepath;
    private String highScore;

    public ScoreManager(){
        try {
            filepath = new File("").getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        highScore = "Scores";
    }

    public void loadScore(){
        try {
            File f = new File(filepath,highScore);
            if(!f.exists()){
                createSaveData();
            }
            Scanner reader = new Scanner(f);
            int i=0;
            while(reader.hasNextLine()){
                if(i==0) easyScore = Integer.parseInt(reader.nextLine());
                else if(i==1) mediumScore = Integer.parseInt(reader.nextLine());
                else if(i==2) hardScore = Integer.parseInt(reader.nextLine());
                i++;
            }
            currentEasy = easyScore;
            currentMedium = mediumScore;
            currentHard = hardScore;
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createSaveData() {
        try {
            File file = new File(filepath,highScore);
            FileWriter output = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(output);
            writer.write("0");
            writer.newLine();
            writer.write("0");
            writer.newLine();
            writer.write("0");
            writer.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void SaveScore(){
        FileWriter output = null;
        try {
            File f = new File(filepath,highScore);
            output = new FileWriter(f);
            BufferedWriter writer = new BufferedWriter(output);
            writer.write(Integer.toString(getCurrentEasy()));
            writer.newLine();
            writer.write(Integer.toString(getCurrentMedium()));
            writer.newLine();
            writer.write(Integer.toString(getCurrentHard()));
            writer.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
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
