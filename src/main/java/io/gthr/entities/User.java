package io.gthr.entities;

import java.util.List;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class User {
  @Id String id;
  String name;
  @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
  List<Key<Location>> subscriptions;

  private User() {}

  public User(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public List<Key<Location>> getSubscriptions() {
    return subscriptions;
  }

  public void setSubscriptions(List<Key<Location>> subscriptions) {
    this.subscriptions = subscriptions;
  }
}
