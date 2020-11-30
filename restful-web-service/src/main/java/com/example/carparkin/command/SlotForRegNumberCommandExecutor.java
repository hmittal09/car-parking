package com.example.carparkin.command;

import java.util.List;
import java.util.Optional;

import com.example.carparking.model.Command;
import com.example.carparking.model.Slot;
import com.example.carparking.service.ParkingLotService;
import com.example.carparking.stragety.OutputPrinter;

public class SlotForRegNumberCommandExecutor extends CommandExecutor {
  public static String COMMAND_NAME = "slot_number_for_registration_number";

  public SlotForRegNumberCommandExecutor(
      final ParkingLotService parkingLotService,
      final OutputPrinter outputPrinter) {
    super(parkingLotService, outputPrinter);
  }

  @Override
  public boolean validate(final Command command) {
    return command.getParams().size() == 1;
  }

  @Override
  public void execute(final Command command) {
    final List<Slot> occupiedSlots = parkingLotService.getOccupiedSlots();
    final String regNumberToFind = command.getParams().get(0);
    final Optional<Slot> foundSlot = occupiedSlots.stream()
        .filter(slot -> slot.getParkedCar().getRegistrationNumber().equals(regNumberToFind))
        .findFirst();
    if (foundSlot.isPresent()) {
      outputPrinter.printWithNewLine(foundSlot.get().getSlotNumber().toString());
    } else {
      outputPrinter.notFound();
    }
  }
}
