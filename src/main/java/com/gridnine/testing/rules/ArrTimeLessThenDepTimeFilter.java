package com.gridnine.testing.rules;

import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightBuilder;
import com.gridnine.testing.Segment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, исключающий из списка перелётов, те перелёты, время прибытия которых,
 * раньше времени отправления
 */

public class ArrTimeLessThenDepTimeFilter implements Filter{
    List<Flight> filterFlights = new ArrayList<>();
    private FlightBuilder builder;

    public ArrTimeLessThenDepTimeFilter(FlightBuilder builder) {
        this.builder = builder;
    }

    @Override
    public List<Flight> filterRule() {
        List<Flight> flights = FlightBuilder.createFlights();

        for(Flight f : flights) {
            if(depTimeLessThanArrTime(f)) {
                filterFlights.add(f);
            }
        }
        return filterFlights;
    }

    /*
    Метод проверки: время отправления сегмента раньше времени прибытия true
     */
    private boolean depTimeLessThanArrTime(Flight flight) {
        List<Segment> segments = flight.getSegments();

        for(Segment s : segments) {
            LocalDateTime dep = s.getDepartureDate();
            LocalDateTime arr = s.getArrivalDate();
            if(arr.isBefore(dep)) {
                return false;
            }
        }
        return true;
    }

}
