package com.revature.rideshare.maps.util;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.model.AddressComponent;
import com.revature.rideshare.maps.beans.Location;

@Component
public class AddressComponentParser {

	public String parseAddressComponent(String addressComponents) {
		ObjectMapper objectMapper = new ObjectMapper();
		List<AddressComponent> addressComponentList;
		try {
			addressComponentList = objectMapper.readValue(addressComponents, new TypeReference<List<AddressComponent>>(){});
			for (AddressComponent a : addressComponentList) {
				System.out.println(a);
				//System.out.println(a.longName);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addressComponents;
	}

	public AddressComponentParser() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "AddressComponentParser []";
	}
	
}
