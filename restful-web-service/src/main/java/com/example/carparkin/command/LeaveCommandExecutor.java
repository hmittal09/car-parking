package com.example.carparkin.command;

import java.util.List;

import com.example.carparking.model.Command;
import com.example.carparking.service.ParkingLotService;
import com.example.carparking.stragety.IntegerValidator;
import com.example.carparking.stragety.OutputPrinter;

public class LeaveCommandExecutor extends CommandExecutor {
  public static String COMMAND_NAME = "leave";

  public LeaveCommandExecutor(final ParkingLotService parkingLotService,
      final OutputPrinter outputPrinter) {
    super(parkingLotService, outputPrinter);
  }


  @Override
  public boolean validate( Command command) {
    final List<String> params = command.getParams();
    if (params.size() != 1) {
      return false;
    }
    return IntegerValidator.isInteger(params.get(0));
  }

  @Override
  public void execute(final Command command) {
    final int slotNum = Integer.parseInt(command.getParams().get(0));
    parkingLotService.makeSlotFree(slotNum);
    outputPrinter.printWithNewLine("Slot number " + slotNum + " is free");
  }
}
