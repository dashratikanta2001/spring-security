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
		throw new InvalidVehicleTypeException("Vehicle Number pattern must be  DEMO-XXXX-XXXX-YYYY-N");
	}
	
	
	public static boolean validEmailPattern(String email)
	{

		if (email == null || email.trim().isEmpty()) {
			return false;
		}
		String regex = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			return true;
		}
		throw new InvalidVehicleTypeException("Invalid Email format.");
	}
	
	public static boolean validPhoneNumberPattern(String phoneNo)
	{
		if (phoneNo == null || phoneNo.trim().isEmpty()) {
			return true;
		}
		
		String regex = "^[6789]\\d{9}$";
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(phoneNo);
		if (matcher.matches()) {
			return true;
		}
		throw new InvalidVehicleTypeException("Invalid Phone Number.");
	}
	
}
