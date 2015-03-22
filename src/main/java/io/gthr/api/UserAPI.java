package io.gthr.api;

import io.gthr.entities.User2Gethr;
import io.gthr.repositories.UserRepository;

import javax.inject.Named;

import com.google.appengine.api.users.User;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.UnauthorizedException;

@Api(
  name = "gthr",
  version = "v1",
  scopes = {Constants.EMAIL_SCOPE},
  clientIds = {Constants.WEB_CLIENT_ID}
)
public class UserAPI {

  @ApiMethod(
    name = "users.get",
    path = "users/{email}",
    httpMethod = HttpMethod.GET
  )
  public User2Gethr get(
    @Named("email") String email,
    User user
  ) throws UnauthorizedException {
    if (user == null) throw new UnauthorizedException("#loginrequired");

    return UserRepository.instance().get(email);
  }

  @ApiMethod(
    name = "users.create",
    path = "users",
    httpMethod = HttpMethod.POST
  )
  public User2Gethr create(
    @Named("email") String email,
    @Named("name") String name,
    User user
  ) throws UnauthorizedException {
    if (user == null) throw new UnauthorizedException("#loginrequired");

    return UserRepository.instance().create(new User2Gethr(email, name));
  }

  @ApiMethod(
    name = "users.delete",
    path = "users/{email}",
    httpMethod = HttpMethod.DELETE
  )
  public User2Gethr delete(
    @Named("email") String email,
    User user
  ) throws UnauthorizedException {
    if (user == null) throw new UnauthorizedException("#loginrequired");

    return UserRepository.instance().delete(email);
  }

  @ApiMethod(
    name = "users.update",
    path = "users",
    httpMethod = HttpMethod.PUT
  )
  public User2Gethr update(
    @Named("email") String email,
    @Named("name") String name,
    User user
  ) throws UnauthorizedException {
    if (user == null) throw new UnauthorizedException("#loginrequired");

    return UserRepository.instance().update(new User2Gethr(email, name));
  }

  @ApiMethod(
    name = "users.subscribe",
    path = "users/{email}/subscriptions/{locationName}",
    httpMethod = HttpMethod.POST
  )
  public User2Gethr subscribe(
    @Named("email") String email,
    @Named("locationName") String locationName,
    User user
  ) throws UnauthorizedException {
    if (user == null) throw new UnauthorizedException("#loginrequired");

    return UserRepository.instance().subscribe(email, locationName);
  }

  @ApiMethod(
    name = "users.unsubscribe",
    path = "users/{email}/subscriptions/{locationName}",
    httpMethod = HttpMethod.DELETE
  )
  public User2Gethr unsubscribe(
    @Named("email") String email,
    @Named("locationName") String locationName,
    User user
  ) throws UnauthorizedException {
    if (user == null) throw new UnauthorizedException("#loginrequired");

    return UserRepository.instance().unsubscribe(email, locationName);
  }
}
