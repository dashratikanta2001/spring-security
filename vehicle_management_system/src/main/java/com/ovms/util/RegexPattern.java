package com.ovms.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ovms.exception.InvalidVehicleTypeException;

public class RegexPattern {

	public static boolean checkVehicleNumberPattern(String vehicleNumber)
	{
		String regex = "DEMO-[a-zA-Z]+-[a-zA-Z]+-\\d{4}-\\d+";
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(vehicleNumber);
		
		
		if (matcher.matches()) {
			return true;
		}
		throw new InvalidVehicleTypeException("Vehicle Number pattern must be  DEMO-XXXX-XXXX-0000-N");
	}
	
}
