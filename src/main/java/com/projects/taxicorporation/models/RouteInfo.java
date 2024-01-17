package com.projects.taxicorporation.models;

import java.io.Serializable;

public class RouteInfo implements Serializable {
    public String departureName;
    public String arrivalName;
    public int reservationId;
    public String departureDate;

    public RouteInfo(int reservationId, String departureName, String arrivalName, String departure_date) {
        this.departureName = departureName;
        this.arrivalName = arrivalName;
        this.reservationId = reservationId;
        this.departureDate = departure_date;
    }
}
