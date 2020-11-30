package com.example.carparkin.command;

import com.example.carparking.model.Command;
import com.example.carparking.service.ParkingLotService;
import com.example.carparking.stragety.OutputPrinter;

public class ExitCommandExecutor extends CommandExecutor {
  public static String COMMAND_NAME = "exit";

  public ExitCommandExecutor(
      final ParkingLotService parkingLotService, final OutputPrinter outputPrinter) {
    super(parkingLotService, outputPrinter);
  }

  @Override
  public boolean validate(final Command command) {
    return command.getParams().isEmpty();
  }

  @Override
  public void execute(final Command command) {
    outputPrinter.end();
  }
}
