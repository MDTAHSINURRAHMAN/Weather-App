package com.example.weatherapp.Activities.Factory;

import com.example.weatherapp.Activities.Domains.Hourly;
import com.example.weatherapp.Activities.Domains.TommorowDomain;

public class DomainFactory {
    public static Object createDomain(String type, Object... args) {
        if (type.equalsIgnoreCase("Hourly")) {
            if (args.length != 3 || !(args[0] instanceof String) || !(args[1] instanceof Integer) || !(args[2] instanceof String)) {
                throw new IllegalArgumentException("Invalid arguments for Hourly domain creation");
            }
            String hour = (String) args[0];
            int temp = (Integer) args[1];
            String picPath = (String) args[2];
            return new Hourly(hour, temp, picPath);
        } else if (type.equalsIgnoreCase("Tommorow")) {
            if (args.length != 5 || !(args[0] instanceof String) || !(args[1] instanceof String) || !(args[2] instanceof String) || !(args[3] instanceof Integer) || !(args[4] instanceof Integer)) {
                throw new IllegalArgumentException("Invalid arguments for Tomorrow domain creation");
            }
            String day = (String) args[0];
            String picPath = (String) args[1];
            String status = (String) args[2];
            int highTemp = (Integer) args[3];
            int lowTemp = (Integer) args[4];
            return new TommorowDomain(day, picPath, status, highTemp, lowTemp);
        } else {
            throw new IllegalArgumentException("Invalid domain type");
        }
    }
}
