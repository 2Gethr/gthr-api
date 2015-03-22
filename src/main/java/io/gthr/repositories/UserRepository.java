package io.gthr.repositories;

import io.gthr.entities.UserGthr;

import com.google.appengine.api.users.User;

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
    UserGthr existingUser = getExistingUser(user.getUser());

    if (existingUser == null) {
      ofy().save().entity(user).now();
      return user;
    }

    return existingUser;
  }

  /**
   * Get the existing UserGthr for the authenticated user given
   *
   * @param user The authenticated user
   *
   * @return The existing UserGthr (or null otherwise)
   */
  public UserGthr getExistingUser(User user) {
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
   * Subscribe an user to the given location
   *
   * @param id         User identifier
   * @param locationId Location's name
   *
   * @return The user
   */
  public UserGthr subscribe(Long id, Long locationId) {
    // @todo Check if locationId refers to an actual location
    // @todo Prevent subscription to locations already subscribed to
    UserGthr userGthr = ofy().load().type(UserGthr.class).id(id).now();
    userGthr.getSubscriptions().add(locationId);

    ofy().save().entity(userGthr).now();

    return userGthr;
  }

  /**
   * Unsubscribe an user to the given location
   *
   * @param id         User identifier
   * @param locationId Location's name
   *
   * @return The user
   */
  public UserGthr unsubscribe(Long id, Long locationId) {
    UserGthr userGthr = ofy().load().type(UserGthr.class).id(id).now();
    userGthr.getSubscriptions().remove(locationId);

    ofy().save().entity(userGthr).now();

    return userGthr;
  }
}
