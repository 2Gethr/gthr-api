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

  /**
   * Retrieve a location
   *
   * @param name Location's name
   *
   * @return The wanted location
   */
  public Location get(String name) {
    return ofy().load().type(Location.class).id(name).now();
  }

  /**
   * Create a location
   *
   * @param location The location to create
   *
   * @return The created location
   */
  public Location create(Location location) {
    ofy().save().entity(location).now();

    return location;
  }

  /**
   * Delete a location
   *
   * @param locationName The location' name to delete
   *
   * @return The deleted location
   */
  public Location delete(String locationName) {
    Location location = get(locationName);
    ofy().delete().entity(location).now();

    return location;
  }
}
