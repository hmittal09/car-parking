package com.example.carparkin.mode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.example.carparkin.command.CommandExecutorFactory;
import com.example.carparking.model.Command;
import com.example.carparking.stragety.OutputPrinter;

public class FileMode extends Mode{
	
	private String fileName;

	  public FileMode(  final CommandExecutorFactory commandExecutorFactory,final OutputPrinter outputPrinter, 
			  final String fileName) {
	   super(commandExecutorFactory, outputPrinter);
	    this.fileName = fileName;
	  }

	
	public void process() throws IOException {
	   File file = new File(fileName);
       BufferedReader reader = null;
	    try {
	      reader = new BufferedReader(new FileReader(file));
	    } catch (FileNotFoundException e) {
	     System.out.print("invalid file name");
	    }

	    String input = reader.readLine();
	    while (input != null) {
	     Command command = new Command(input);
	      processInput(command);
	      input = reader.readLine();
	    }
	    
	}
	 

}
