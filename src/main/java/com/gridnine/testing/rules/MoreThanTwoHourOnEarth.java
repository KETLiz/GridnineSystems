package com.gridnine.testing.rules;

import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightBuilder;
import com.gridnine.testing.Segment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, исключающий из списка перелётов, те перелёты, общее время, проведённое на земле которых, превышает два часа
 */
public class MoreThanTwoHourOnEarth implements Filter{
    List<Flight> filterFlights = new ArrayList<>();
    private FlightBuilder builder;

    public MoreThanTwoHourOnEarth(FlightBuilder builder) {
        this.builder = builder;
    }

    @Override
    public List<Flight> filterRule() {
        List<Flight> flights = FlightBuilder.createFlights();
        for(Flight f : flights) {
            if(twoHoursCheck(f)) {
                filterFlights.add(f);
            }
        }
        return filterFlights;
    }

    private boolean twoHoursCheck(Flight flight) {
        List<Segment> segments = flight.getSegments();
        int sum = 0;
        for(int i = 0; i < segments.size()-1; i++) {
            LocalDateTime firstArr = segments.get(i).getArrivalDate(); // время прибытия
            LocalDateTime secondDep = segments.get(i+1).getDepartureDate(); // время следующего отправления
            int difference = secondDep.getHour() - firstArr.getHour();
            sum += difference;
        }
        if(sum > 2) {
            return false;
        }
        return true;
    }
}
