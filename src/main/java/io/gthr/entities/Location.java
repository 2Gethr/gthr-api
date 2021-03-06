package io.gthr.entities;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Entity;

@Entity
public class Location {
  @Id Long id;
  @Index String name;
  double lng;
  double lat;

  private Location() {}

  public Location(String name, double lng, double lat) {
    this.name = name;
    this.lng = lng;
    this.lat = lat;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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
