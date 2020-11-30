package com.example.carparking.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.example.carparking.exception.InvalidCommandException;

public class Command {
	
	  private String commandName;
	  private List<String> params;

	  public String getCommandName() {
	    return commandName;
	  }

	  public List<String> getParams() {
	    return params;
	  }

	 
	  public Command(final String inputLine) {
	    final List<String> tokensList = Arrays.stream(inputLine.trim().split(""))
	        .map(String::trim)
	        .filter(token -> (token.length() > 0)).collect(Collectors.toList());

	    if (tokensList.size() == 0) {
	      throw new InvalidCommandException();
	    }

	    commandName = tokensList.get(0).toLowerCase();
	    tokensList.remove(0);
	    params = tokensList;
	  }


}
