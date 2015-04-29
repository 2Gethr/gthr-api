package io.gthr.repositories;

import io.gthr.entities.Event;

import com.googlecode.objectify.ObjectifyService;

import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class EventRepository {
  private static EventRepository repo = null;

  static {
    ObjectifyService.register(Event.class);
  }

  /**
   * Get singleton instance
   *
   * @return The EventRepository instance
   */
  public static synchronized EventRepository instance() {
    if (repo == null) repo = new EventRepository();

    return repo;
  }

  /**
   * Retrieve an event
   *
   * @param id Event identifier
   *
   * @return The wanted event
   */
  public Event get(Long id) {
    return ofy().load().type(Event.class).id(id).now();
  }

  /**
   * Get every events
   *
   * @return List of events
   */
  public List<Event> list() {
    return ofy().load().type(Event.class).list();
  }

  /**
   * Create an event
   *
   * @param event The event to create
   *
   * @return The created event
   */
  public Event create(Event event) {
    ofy().save().entity(event).now();

    return event;
  }
}
