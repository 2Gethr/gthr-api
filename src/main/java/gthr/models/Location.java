package io.gthr.models;

public class Location {
  private int id;
  private String name;
  private double lng;
  private double lat;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getLng() {
    return this.lng;
  }

  public void setLng(double lng) {
    this.lng = lng;
  }

  public double getLat() {
    return this.lat;
  }

  public void setLat(double lat) {
    this.lat = lat;
  }
}
