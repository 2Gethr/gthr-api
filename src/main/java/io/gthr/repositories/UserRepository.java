package io.gthr.repositories;

import java.util.ArrayList;
import java.util.Collection;

import io.gthr.entities.Location;
import io.gthr.entities.UserGthr;

import com.google.appengine.api.users.User;
import com.google.api.server.spi.response.NotFoundException;

import com.googlecode.objectify.ObjectifyService;
import static com.googlecode.objectify.ObjectifyService.ofy;

public class UserRepository {
  private static UserRepository repo = null;

  static {
    ObjectifyService.register(UserGthr.class);
  }

  /**
   * Get singleton instance
   *
   * @return The UserRepository instance
   */
  public static synchronized UserRepository instance() {
    if (repo == null) repo = new UserRepository();

    return repo;
  }

  /**
   * Retrieve an user
   *
   * @param id User identifier
   *
   * @return The wanted user
   */
  public UserGthr get(Long id) {
    return ofy().load().type(UserGthr.class).id(id).now();
  }

  /**
   * Create an user (if not created yet)
   *
   * @param user The UserGthr to create
   *
   * @return The created UserGthr
   */
  public UserGthr create(UserGthr user) {
    UserGthr existingUser = getByUser(user.getUser());

    if (existingUser == null) {
      ofy().save().entity(user).now();
      return user.setFirstVisit(true);
    }

    return existingUser.setFirstVisit(false);
  }

  /**
   * Get an user from the authenticated user
   *
   * @param user The authenticated user
   *
   * @return The existing user (or null otherwise)
   */
  public UserGthr getByUser(User user) {
    return ofy().load().type(UserGthr.class).filter("user", user).first().now();
  }

  /**
   * Delete an user
   *
   * @param id User identifier
   *
   * @return The deleted user
   */
  public UserGthr delete(Long id) {
    UserGthr user = get(id);
    ofy().delete().entity(user);

    return user;
  }

  /**
   * Retrieve user' subscriptions
   *
   * @param user User to get subscriptions from
   *
   * @return A collection of locations
   */
  public Collection<Location> getSubscriptions(User user) {
    UserGthr retrievedUser = getByUser(user);
    ArrayList<Long> locationIds = retrievedUser.getSubscriptions();

    return LocationRepository.instance().listByIds(locationIds);
  }

  /**
   * Subscribe an user to the given location
   *
   * @param user       The authenticated user
   * @param locationId Location's identifier
   *
   * @return The user
   */
  public UserGthr subscribe(User user, Long locationId) throws NotFoundException {
    Location location = LocationRepository.instance().get(locationId);
    UserGthr userGthr = getByUser(user);

    if (location == null) {
      throw new NotFoundException("No location with id " + locationId);
    }

    // Prevent subscription to locations already subscribed to
    if (userGthr.getSubscriptions().contains(locationId)) {
      return userGthr;
    }

    userGthr.getSubscriptions().add(locationId);
    ofy().save().entity(userGthr).now();

    return userGthr;
  }

  /**
   * Unsubscribe an user to the given location
   *
   * @param user       The authenticated user
   * @param locationId Location's identifier
   *
   * @return The user
   */
  public UserGthr unsubscribe(User user, Long locationId) {
    UserGthr userGthr = getByUser(user);
    userGthr.getSubscriptions().remove(locationId);

    ofy().save().entity(userGthr).now();

    return userGthr;
  }
}
