package com.example.carparkin.command;

import com.example.carparking.model.Command;
import com.example.carparking.service.ParkingLotService;
import com.example.carparking.stragety.OutputPrinter;

public abstract class CommandExecutor {
  protected ParkingLotService parkingLotService;
  protected OutputPrinter outputPrinter;

  public CommandExecutor(final ParkingLotService parkingLotService,
      final OutputPrinter outputPrinter) {
    this.parkingLotService = parkingLotService;
    this.outputPrinter = outputPrinter;
  }

  public abstract boolean validate(Command command);

 
  public abstract void execute(Command command);
}
