package io.gthr.repositories;

import io.gthr.entities.Location;

import com.googlecode.objectify.ObjectifyService;
import static com.googlecode.objectify.ObjectifyService.ofy;

public class LocationRepository {
  private static LocationRepository repo = null;

  static {
    ObjectifyService.register(Location.class);
  }

  /**
   * Get singleton instance
   *
   * @return The LocationRepository instance
   */
  public static synchronized LocationRepository instance() {
    if (repo == null) repo = new LocationRepository();

    return repo;
  }

  /**
   * Retrieve a location
   *
   * @param id Location's identifier
   *
   * @return The wanted location
   */
  public Location get(Long id) {
    return ofy().load().type(Location.class).id(id).now();
  }

  /**
   * Create a location
   *
   * @param location The location to create
   *
   * @return The created location
   */
  public Location create(Location location) {
    // @todo Prevent location with same name
    ofy().save().entity(location).now();

    return location;
  }

  /**
   * Delete a location
   *
   * @param id The location' name to delete
   *
   * @return The deleted location
   */
  public Location delete(Long id) {
    Location location = get(id);
    ofy().delete().entity(location).now();

    return location;
  }
}
