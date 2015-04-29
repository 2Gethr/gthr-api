package io.gthr.entities;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Entity;

import java.util.ArrayList;

@Entity
public class Event {
  @Id Long id;
  String name;
  @Index Long location;
  @Index ArrayList<Long> participants = new ArrayList<>();

  private Event() {}

  public Event(String name, Long locationId) {
    this.name = name;
    this.location = locationId;
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

  public Long getLocation() {
    return location;
  }

  public void setLocation(Long location) {
    this.location = location;
  }

  public ArrayList<Long> getParticipants() {
    return participants;
  }

  public void setParticipants(ArrayList<Long> participants) {
    this.participants = participants;
  }
}
