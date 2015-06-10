package com.example.jeremy.naturephotographynow.events;

import java.util.List;

/**
 * Created by Mezzo on 6/10/2015.
 */
public class Calendar {
    private List<Events> events;
    private List<String> filters;

    public List<Events> getEvents() {
        return events;
    }

    public void setEvents(List<Events> events) {
        this.events = events;
    }

    public List<String> getFilters() {
        return filters;
    }

    public void setFilters(List<String> filters) {
        this.filters = filters;
    }

}
