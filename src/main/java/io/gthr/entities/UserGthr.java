package io.gthr.entities;

import java.util.ArrayList;

import com.google.appengine.api.users.User;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Ignore;

@Entity
public class UserGthr {
  @Id Long id;
  @Index User user;
  @Ignore Boolean firstVisit;
  ArrayList<Long> subscriptions = new ArrayList<>();

  private UserGthr() {}

  public UserGthr(User user) {
    this.user = user;
  }

  public Long getId() {
    return id;
  }

  public UserGthr setId(Long id) {
    this.id = id;

    return this;
  }

  public User getUser() {
    return user;
  }

  public UserGthr setUser(User user) {
    this.user = user;

    return this;
  }

  public ArrayList<Long> getSubscriptions() {
    return subscriptions;
  }

  public UserGthr setSubscriptions(ArrayList<Long> subscriptions) {
    this.subscriptions = subscriptions;

    return this;
  }

  public Boolean getFirstVisit() {
    return firstVisit;
  }

  public UserGthr setFirstVisit(Boolean firstVisit) {
    this.firstVisit = firstVisit;

    return this;
  }
}
