package io.gthr.entities;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Entity;

import java.util.ArrayList;
import java.util.Date;

@Entity
public class Event {
  @Id Long id;
  String name;
  Date date;
  @Index Long location;
  @Index ArrayList<Long> participants = new ArrayList<>();

  private Event() {}

  public Event(String name, Date date, Long locationId) {
    this.name = name;
    this.date = date;
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

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
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
