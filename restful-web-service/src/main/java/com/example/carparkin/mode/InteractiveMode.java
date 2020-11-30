package com.example.carparkin.mode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.example.carparkin.command.CommandExecutorFactory;
import com.example.carparkin.command.ExitCommandExecutor;
import com.example.carparking.model.Command;
import com.example.carparking.stragety.OutputPrinter;

public class InteractiveMode extends Mode {

	public InteractiveMode(final CommandExecutorFactory commandExecutorFactory, final OutputPrinter outputPrinter) {
		super(commandExecutorFactory, outputPrinter);
	}

	@Override
	public void process() throws IOException {
		outputPrinter.welcome();
		final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			final String input = reader.readLine();
			final Command command = new Command(input);
			processInput(command);
			if (command.getCommandName().equals(ExitCommandExecutor.COMMAND_NAME)) {
				break;
			}
		}
	}
}
