package com.gridnine.testing;

import com.gridnine.testing.rules.ArrTimeLessThenDepTimeFilter;
import com.gridnine.testing.rules.DepartureBeforeNow;
import com.gridnine.testing.rules.Filter;
import com.gridnine.testing.rules.MoreThanTwoHourOnEarth;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Filter a = new ArrTimeLessThenDepTimeFilter(new FlightBuilder());
        List<Flight> flights = a.filterRule();
        for(Flight f : flights) {
            System.out.println(f);
        }
        System.out.println();

        List<Flight> flights2 =  FlightBuilder.createFlights();
        for(Flight f :flights2) {
            System.out.println(f);
        }

//        List<Flight> flights =  FlightBuilder.createFlights();
//        for(Flight f :flights) {
//            List<Segment> segments = f.getSegments();
//            for(int i = 0; i < segments.size(); i++) {
//                LocalDateTime dep = LocalDateTime.parse(segments.get(i).getDepartureDate().format(fmt));
//                LocalDateTime arr = LocalDateTime.parse(segments.get(i).getArrivalDate().format(fmt));
//                if(arr.isBefore(dep)) {
//                    System.out.println(f);
//                }
//            }
//
//        }


    }
}