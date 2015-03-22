package io.gthr.repositories;

import com.googlecode.objectify.ObjectifyService;
import static com.googlecode.objectify.ObjectifyService.ofy;

import io.gthr.entities.Location;

public class LocationRepository {
  private static LocationRepository repo = null;

  static {
    ObjectifyService.register(Location.class);
  }

  public static synchronized LocationRepository instance() {
    if (repo == null) repo = new LocationRepository();
    return repo;
  }

  public Location get(String id) {
    return ofy().load().type(Location.class).id(id).now();
  }

  public Location create(Location location) {
    ofy().save().entity(location).now();
    return location;
  }
}
