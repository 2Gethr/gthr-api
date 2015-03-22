package io.gthr.api;

import io.gthr.entities.User;
import io.gthr.repositories.UserRepository;

import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;

@Api(
  name = "gthr",
  version = "v1"
)
public class UserAPI {

  @ApiMethod(
    name = "users.get",
    path = "users/{email}",
    httpMethod = HttpMethod.GET
  )
  public User get(
    @Named("email") String email
  ) {
    return UserRepository.instance().get(email);
  }

  @ApiMethod(
    name = "users.create",
    path = "users",
    httpMethod = HttpMethod.POST
  )
  public User create(
    @Named("email") String email,
    @Named("name") String name
  ) {
    return UserRepository.instance().create(new User(email, name));
  };

  @ApiMethod(
    name = "users.subscribe",
    path = "users/{email}/subscriptions/{locationName}",
    httpMethod = HttpMethod.POST
  )
  public User subscribe(
    @Named("email") String email,
    @Named("locationName") String locationName
  ) {
    return UserRepository.instance().subscribe(email, locationName);
  }

  @ApiMethod(
    name = "users.unsubscribe",
    path = "users/{email}/subscriptions/{locationName}",
    httpMethod = HttpMethod.DELETE
  )
  public User unsubscribe(
    @Named("email") String email,
    @Named("locationName") String locationName
  ) {
    return UserRepository.instance().unsubscribe(email, locationName);
  }
}
