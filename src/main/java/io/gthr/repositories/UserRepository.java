package io.gthr.repositories;

import com.googlecode.objectify.ObjectifyService;
import static com.googlecode.objectify.ObjectifyService.ofy;

import io.gthr.entities.Location;
import io.gthr.entities.User;

public class UserRepository {
  private static UserRepository repo = null;

  static {
    ObjectifyService.register(User.class);
  }

  public static synchronized UserRepository instance() {
    if (repo == null) repo = new UserRepository();
    return repo;
  }

  public User get(String id) {
    return ofy().load().type(User.class).id(id).now();
  }

  public User create(User user) {
    ofy().save().entity(user).now();
    return user;
  }
}
