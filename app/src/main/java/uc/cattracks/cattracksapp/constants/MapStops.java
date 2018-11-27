package uc.cattracks.cattracksapp.constants;

public class MapStops {

    public String stopName;
    public String mapQuery;
    public double stopLongitude;
    public double stopLatitude;


    public MapStops() {}

    public MapStops(String stopName, String mapQuery, double stopLongitude, double stopLatitude) {
        this.stopName = stopName;
        this.mapQuery = mapQuery;
        this.stopLongitude = stopLongitude;
        this.stopLatitude = stopLatitude;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public String getMapQuery() {
        return mapQuery;
    }

    public void setMapQuery(String mapQuery) {
        this.mapQuery = mapQuery;
    }

    public double getStopLongitude() {
        return stopLongitude;
    }

    public void setStopLongitude(double stopLongitude) {
        this.stopLongitude = stopLongitude;
    }

    public double getStopLatitude() {
        return stopLatitude;
    }

    public void setStopLatitude(double stopLatitude) {
        this.stopLatitude = stopLatitude;
    }
}
