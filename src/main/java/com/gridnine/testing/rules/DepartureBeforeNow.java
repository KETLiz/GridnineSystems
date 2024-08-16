package com.gridnine.testing.rules;

import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightBuilder;
import com.gridnine.testing.Segment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DepartureBeforeNow implements Filter{
    LocalDateTime nowTime = LocalDateTime.now();
    List<Flight> filterFlights = new ArrayList<>();
    FlightBuilder builder;

    public DepartureBeforeNow(FlightBuilder builder) {
        this.builder = builder;
    }

    @Override
    public List<Flight> filterRule() {
        List<Flight> flights = FlightBuilder.createFlights();
        for(Flight f : flights) {
            if(ifDepartureBeforNowNotExists(f)) {
                filterFlights.add(f);
            }
        }
        return filterFlights;
    }

    private boolean ifDepartureBeforNowNotExists(Flight flight) {
        List<Segment> segments = flight.getSegments();
        for(Segment s : segments) {
            LocalDateTime depTime = s.getDepartureDate();
            if(depTime.isBefore(nowTime)) {
                return false;
            }
        }
        return true;
    }
}
