package com.example.carparkin.command;

import java.util.List;
import java.util.stream.Collectors;

import com.example.carparking.model.Command;
import com.example.carparking.model.Slot;
import com.example.carparking.service.ParkingLotService;
import com.example.carparking.stragety.OutputPrinter;

public class ColorToRegNumberCommandExecutor extends CommandExecutor {
  public static String COMMAND_NAME = "registration_numbers_for_cars_with_colour";

  public ColorToRegNumberCommandExecutor(
      final ParkingLotService parkingLotService, final OutputPrinter outputPrinter) {
    super(parkingLotService, outputPrinter);
  }


  @Override
  public boolean validate( Command command) {
    return command.getParams().size() == 1;
  }

  @Override
  public void execute(final Command command) {
    final List<Slot> slotsForColor = parkingLotService.getSlotsForColor(command.getParams().get(0));
    if (slotsForColor.isEmpty()) {
      outputPrinter.notFound();
    } else {
      final String result =
          slotsForColor.stream()
              .map(slot -> slot.getParkedCar().getRegistrationNumber())
              .collect(Collectors.joining(", "));
      outputPrinter.printWithNewLine(result);
    }
  }
}
