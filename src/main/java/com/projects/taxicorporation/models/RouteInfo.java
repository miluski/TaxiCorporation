package com.projects.taxicorporation.models;

import java.io.Serializable;

public class RouteInfo implements Serializable {
    private String departureName;
    private String arrivalName;
    private int courseId;

    public RouteInfo(String departureName, String arrivalName, int courseId) {
        this.departureName = departureName;
        this.arrivalName = arrivalName;
        this.courseId = courseId;
    }
}
