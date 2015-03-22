package io.gthr.entities;

import java.util.ArrayList;

import com.google.appengine.api.users.User;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

@Entity
public class UserGthr {
  @Id Long id;
  @Index User user;
  ArrayList<Long> subscriptions = new ArrayList<>();

  private UserGthr() {}

  public UserGthr(User user) {
    this.user = user;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public ArrayList<Long> getSubscriptions() {
    return subscriptions;
  }

  public void setSubscriptions(ArrayList<Long> subscriptions) {
    this.subscriptions = subscriptions;
  }
}
