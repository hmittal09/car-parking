package com.example.carparkin.command;

import com.example.carparking.exception.NoFreeSlotAvailableException;
import com.example.carparking.model.Car;
import com.example.carparking.model.Command;
import com.example.carparking.service.ParkingLotService;
import com.example.carparking.stragety.OutputPrinter;

public class ParkCommandExecutor extends CommandExecutor {
  public static String COMMAND_NAME = "park";

  public ParkCommandExecutor(
      final ParkingLotService parkingLotService, final OutputPrinter outputPrinter) {
    super(parkingLotService, outputPrinter);
  }

  @Override
  public boolean validate( Command command) {
    return command.getParams().size() == 2;
  }

  @Override
  public void execute(final Command command) {
    final Car car = new Car(command.getParams().get(0), command.getParams().get(1));
    try {
      final Integer slot = parkingLotService.park(car);
      outputPrinter.printWithNewLine("Allocated slot number: " + slot);
    } catch (NoFreeSlotAvailableException exception) {
      outputPrinter.parkingLotFull();
    }
  }
}
