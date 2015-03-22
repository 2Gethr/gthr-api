package io.gthr.entities;

import java.util.ArrayList;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class User2Gethr {
  @Id String email;
  String name;
  ArrayList<String> subscriptions = new ArrayList<String>();

  private User2Gethr() {}

  public User2Gethr(String email, String name) {
    this.email = email;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public ArrayList<String> getSubscriptions() {
    return subscriptions;
  }

  public void setSubscriptions(ArrayList<String> subscriptions) {
    this.subscriptions = subscriptions;
  }
}
