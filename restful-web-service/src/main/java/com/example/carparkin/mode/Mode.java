package com.example.carparkin.mode;

import java.io.IOException;

import com.example.carparkin.command.CommandExecutor;
import com.example.carparkin.command.CommandExecutorFactory;
import com.example.carparking.exception.InvalidCommandException;
import com.example.carparking.model.Command;
import com.example.carparking.stragety.OutputPrinter;

public abstract class Mode {
	
	private CommandExecutorFactory commandExecutorFactory;
	  protected OutputPrinter outputPrinter;

	  public Mode(
	      final CommandExecutorFactory commandExecutorFactory, final OutputPrinter outputPrinter) {
	    this.commandExecutorFactory = commandExecutorFactory;
	    this.outputPrinter = outputPrinter;
	  }

	  protected void processInput(final Command command) {
		    final CommandExecutor commandExecutor = commandExecutorFactory.getCommandExecutor(command);
		    if (commandExecutor.validate(command)) {
		      commandExecutor.execute(command);
		    } else {
		      throw new InvalidCommandException();
		    }
		  }

		
		  public abstract void process() throws IOException;
		}



