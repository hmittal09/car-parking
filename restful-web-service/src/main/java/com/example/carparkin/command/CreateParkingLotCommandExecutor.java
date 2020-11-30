package com.example.carparkin.command;

import java.util.List;

import com.example.carparking.model.Command;
import com.example.carparking.model.ParkingLot;
import com.example.carparking.service.ParkingLotService;
import com.example.carparking.stragety.IntegerValidator;
import com.example.carparking.stragety.NaturalOrderingParkingStrategy;
import com.example.carparking.stragety.OutputPrinter;

public class CreateParkingLotCommandExecutor extends CommandExecutor {
  public static String COMMAND_NAME = "create_parking_lot";

  public CreateParkingLotCommandExecutor(
      final ParkingLotService parkingLotService, final OutputPrinter outputPrinter) {
    super(parkingLotService, outputPrinter);
  }

  
  @Override
  public boolean validate(final Command command) {
    final List<String> params = command.getParams();
    if (params.size() != 1) {
      return false;
    }
    return IntegerValidator.isInteger(params.get(0));
  }

  
  @Override
  public void execute(final Command command) {
    final int parkingLotCapacity = Integer.parseInt(command.getParams().get(0));
    final ParkingLot parkingLot = new ParkingLot(parkingLotCapacity);
    parkingLotService.createParkingLot(parkingLot, new NaturalOrderingParkingStrategy());
    outputPrinter.printWithNewLine(
        "Created a parking lot with " + parkingLot.getCapacity() + " slots");
  }
}
