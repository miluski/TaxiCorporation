package com.projects.taxicorporation.models;

import java.io.Serializable;

public class RouteInfo implements Serializable {
    public String departureName;
    public String arrivalName;
    public int courseId;
    public String departureDate;

    public RouteInfo(int courseId, String departureName, String arrivalName, String departure_date) {
        this.departureName = departureName;
        this.arrivalName = arrivalName;
        this.courseId = courseId;
        this.departureDate = departure_date;
    }
}
