package io.gthr.api;

import io.gthr.entities.User;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import io.gthr.repositories.UserRepository;

import javax.inject.Named;

@Api(
  name = "gthr",
  version = "v1"
)
public class UserAPI {

  @ApiMethod(
    name = "users.get",
    path = "users/{id}",
    httpMethod = HttpMethod.GET
  )
  public User getUser(@Named("id") String id) {
    return UserRepository.instance().get(id);
  }

  @ApiMethod(
    name = "users.create",
    path = "users",
    httpMethod = HttpMethod.POST
  )
  public User createUser(
    @Named("id") String id,
    @Named("name") String name
  ) {
    return UserRepository.instance().create(new User(id, name));
  };
}
