package io.gthr.api;

import javax.inject.Named;

import java.util.Collection;

import io.gthr.entities.Location;
import io.gthr.entities.UserGthr;
import io.gthr.repositories.UserRepository;

import com.google.appengine.api.users.User;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;

@Api(
  name = "gthr",
  version = "v1",
  scopes = {Constants.EMAIL_SCOPE},
  clientIds = {Constants.WEB_CLIENT_ID, com.google.api.server.spi.Constant.API_EXPLORER_CLIENT_ID}
)
public class UserAPI {
  // @fixme Manage admin access

  @ApiMethod(
    name = "users.get",
    path = "users/{id}",
    httpMethod = HttpMethod.GET
  )
  public UserGthr get(
    @Named("id") Long id,
    User user
  ) throws OAuthRequestException {
    if (user == null) throw new OAuthRequestException("#loginrequired");

    return UserRepository.instance().get(id);
  }

  @ApiMethod(
    name = "users.create",
    path = "users",
    httpMethod = HttpMethod.POST
  )
  public UserGthr create(User user) throws OAuthRequestException {
    if (user == null) throw new OAuthRequestException("#loginrequired");

    return UserRepository.instance().create(new UserGthr(user));
  }

  @ApiMethod(
    name = "users.subscriptions",
    path = "users/subscriptions",
    httpMethod = HttpMethod.POST
  )
  public Collection<Location> subscriptions(User user) throws OAuthRequestException {
    if (user == null) throw new OAuthRequestException("#loginrequired");

    return UserRepository.instance().getSubscriptions(user);
  }

  @ApiMethod(
    name = "users.delete",
    path = "users/{id}",
    httpMethod = HttpMethod.DELETE
  )
  public UserGthr delete(
    @Named("id") Long id,
    User user
  ) throws OAuthRequestException {
    if (user == null) throw new OAuthRequestException("#loginrequired");

    return UserRepository.instance().delete(id);
  }

  @ApiMethod(
    name = "users.subscribe",
    path = "users/{id}/subscriptions/{locationId}",
    httpMethod = HttpMethod.POST
  )
  public UserGthr subscribe(
    @Named("id") Long id,
    @Named("locationId") Long locationId,
    User user
  ) throws OAuthRequestException, NotFoundException {
    if (user == null) throw new OAuthRequestException("#loginrequired");

    return UserRepository.instance().subscribe(id, locationId);
  }

  @ApiMethod(
    name = "users.unsubscribe",
    path = "users/{id}/subscriptions/{locationId}",
    httpMethod = HttpMethod.DELETE
  )
  public UserGthr unsubscribe(
    @Named("id") Long id,
    @Named("locationId") Long locationId,
    User user
  ) throws OAuthRequestException {
    if (user == null) throw new OAuthRequestException("#loginrequired");

    return UserRepository.instance().unsubscribe(id, locationId);
  }
}
