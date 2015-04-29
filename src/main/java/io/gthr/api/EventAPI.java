package io.gthr.api;

import io.gthr.entities.Event;
import io.gthr.repositories.EventRepository;

import java.util.Date;
import java.util.List;
import javax.inject.Named;

import com.google.appengine.api.users.User;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.appengine.api.oauth.OAuthRequestException;

@Api(
  name = "gthr",
  version = "v1",
  scopes = {Constants.EMAIL_SCOPE},
  clientIds = {Constants.WEB_CLIENT_ID, com.google.api.server.spi.Constant.API_EXPLORER_CLIENT_ID}
)
public class EventAPI {
  // @fixme Manage admin access

  @ApiMethod(
    name = "events.get",
    path = "events/{id}",
    httpMethod = ApiMethod.HttpMethod.GET
  )
  public Event get(
    @Named("id") Long id,
    User user
  ) throws OAuthRequestException {
    if (user == null) throw new OAuthRequestException("#loginrequired");

    return EventRepository.instance().get(id);
  }

  @ApiMethod(
    name = "events.list",
    path = "events",
    httpMethod = ApiMethod.HttpMethod.GET
  )
  public List<Event> list(User user) throws OAuthRequestException {
    if (user == null) throw new OAuthRequestException("#loginrequired");

    return EventRepository.instance().list();
  }

  @ApiMethod(
    name = "events.create",
    path = "events",
    httpMethod = ApiMethod.HttpMethod.POST
  )
  public Event create(
    @Named("name") String name,
    @Named("date") Date date,
    @Named("location") Long locationId,
    User user
  ) throws OAuthRequestException {
    if (user == null) throw new OAuthRequestException("#loginrequired");

    return EventRepository.instance().create(new Event(name, date, locationId));
  }
}
