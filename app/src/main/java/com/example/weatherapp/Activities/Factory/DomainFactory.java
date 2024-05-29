package com.example.weatherapp.Activities.Factory;

import com.example.weatherapp.Activities.Domains.Hourly;
import com.example.weatherapp.Activities.Domains.TommorowDomain;

/**
 * A factory class for creating domain objects based on the specified type.
 */
public class DomainFactory {

    /**
     * Creates a domain object based on the specified type and arguments.
     *
     * @param type the type of domain to create ("Hourly" or "Tommorow")
     * @param args the arguments required to create the domain object
     * @return a domain object of the specified type
     * @throws IllegalArgumentException if the specified type is invalid or if the arguments are invalid for creating the domain object
     */
    public static Object createDomain(String type, Object... args) throws IllegalArgumentException {
        if (type.equalsIgnoreCase("Hourly")) {
            if (args.length != 3 || !(args[0] instanceof String) || !(args[1] instanceof Double) || !(args[2] instanceof String)) {
                throw new IllegalArgumentException("Invalid arguments for Hourly domain creation");
            }
            String hour = (String) args[0];
            double temp = (Double) args[1];
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
