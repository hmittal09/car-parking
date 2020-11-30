package com.example.carparking;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.carparkin.command.CommandExecutorFactory;
import com.example.carparkin.mode.FileMode;
import com.example.carparkin.mode.InteractiveMode;
import com.example.carparking.exception.InvalidModeException;
import com.example.carparking.service.ParkingLotService;
import com.example.carparking.stragety.OutputPrinter;

@SpringBootApplication
public class RestfulWebServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		
		SpringApplication.run(RestfulWebServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		 final OutputPrinter outputPrinter = new OutputPrinter();
		    final ParkingLotService parkingLotService = new ParkingLotService();
		    final CommandExecutorFactory commandExecutorFactory =
		        new CommandExecutorFactory(parkingLotService);

		    if (isInteractiveMode(args)) {
		      new InteractiveMode(commandExecutorFactory, outputPrinter).process();
		    } else if (isFileInputMode(args)) {
		      new FileMode(commandExecutorFactory, outputPrinter, args[0]).process();
		    } else {
		      throw new InvalidModeException();
		    }

		
	}
	
	 private static boolean isFileInputMode(final String[] args) {
		    return args.length == 1;
		  }

		
		  private static boolean isInteractiveMode(final String[] args) {
		    return args.length == 0;
		  }

}
