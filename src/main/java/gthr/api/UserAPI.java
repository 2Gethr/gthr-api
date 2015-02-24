package io.gthr.api;

import io.gthr.models.User;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;

import javax.inject.Named;

@Api(
  name = "gthr",
  version = "v1"
)
public class UserAPI {

  @ApiMethod(
    name = "users.get",
    path = "users/{name}",
    httpMethod = HttpMethod.GET
  )
  public User getUser(@Named("name") String name) {
    User response = new User();
    response.setName("User, " + name);

    return response;
  }
}
