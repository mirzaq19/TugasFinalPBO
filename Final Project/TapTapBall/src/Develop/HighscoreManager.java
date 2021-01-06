package Develop;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Formatter;
import java.io.Serializable;
import java.util.NoSuchElementException;

public class HighscoreManager implements Serializable{

//	private int score;
	private int highScore = 0;
	private static ObjectOutputStream output;
	private static ObjectInputStream input;
	HighscoreManager record;
	
	public HighscoreManager()
	{
		this(0);
	}
	
	public HighscoreManager(int highScore)
	{
		this.highScore = highScore;
	}
	
	public int loadHighScore()
	{
		try
		{
			input = new ObjectInputStream(
					Files.newInputStream(Paths.get("data.bin")));
		}
		catch (IOException ioException)
		{
			System.err.println("Error opening file.");
			System.exit(1);
		}
		try
		{
			record = (HighscoreManager) input.readObject();	
		}
		catch (ClassNotFoundException classNotFoundException)
		{
			System.err.println("Invalid object type. Terminating.");
		}
		catch (IOException ioException)
		{
		}
		return record.getHighScore();
	}
	
	public void saveHighScore(int score)
	{
		try
		{
			output = new ObjectOutputStream(
					Files.newOutputStream(Paths.get("data.bin")));
		}
		catch (IOException ioException)
		{
			System.err.println("Error opening file. Terminating.");
			System.exit(1);
		}
		try
		{
			record = new HighscoreManager(score);
			output.writeObject(record);
		}
		catch (IOException ioException)
		{
		}
	}
	
	public int getHighScore()
	{
		return highScore;
	}
	
}