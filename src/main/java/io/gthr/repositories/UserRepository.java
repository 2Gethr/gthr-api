package io.gthr.repositories;

import io.gthr.entities.User2Gethr;

import com.googlecode.objectify.ObjectifyService;
import static com.googlecode.objectify.ObjectifyService.ofy;

public class UserRepository {
  private static UserRepository repo = null;

  static {
    ObjectifyService.register(User2Gethr.class);
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
   * @param email User2Gethr's email
   *
   * @return The wanted user
   */
  public User2Gethr get(String email) {
    return ofy().load().type(User2Gethr.class).id(email).now();
  }

  /**
   * Create an user
   *
   * @param user The user2Gethr to create
   *
   * @return The created user2Gethr
   */
  public User2Gethr create(User2Gethr user) {
    ofy().save().entity(user).now();

    return user;
  }

  /**
   * Delete an user
   *
   * @param userEmail The user's email
   *
   * @return The deleted user
   */
  public User2Gethr delete(String userEmail) {
    User2Gethr user = get(userEmail);
    ofy().delete().entity(user);

    return user;
  }

  /**
   * Update an user
   *
   * @param user New user data
   *
   * @return The updated user
   */
  public User2Gethr update(User2Gethr user) {
    ofy().save().entity(user).now();

    return user;
  }

  /**
   * Subscribe an user to the given location
   *
   * @param userEmail    User2Gethr's email
   * @param locationName Location's name
   *
   * @return The user
   */
  public User2Gethr subscribe(String userEmail, String locationName) {
    User2Gethr user2Gethr = ofy().load().type(User2Gethr.class).id(userEmail).now();
    user2Gethr.getSubscriptions().add(locationName);

    ofy().save().entity(user2Gethr).now();

    return user2Gethr;
  }

  /**
   * Unsubscribe an user to the given location
   *
   * @param userEmail    User2Gethr's email
   * @param locationName Location's name
   *
   * @return The user
   */
  public User2Gethr unsubscribe(String userEmail, String locationName) {
    User2Gethr user2Gethr = ofy().load().type(User2Gethr.class).id(userEmail).now();
    user2Gethr.getSubscriptions().remove(locationName);

    ofy().save().entity(user2Gethr).now();

    return user2Gethr;
  }
}
