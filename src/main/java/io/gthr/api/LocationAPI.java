package io.gthr.api;

import io.gthr.entities.Location;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;

import javax.inject.Named;

@Api(
  name = "gthr",
  version = "v1"
)
public class LocationAPI {

  @ApiMethod(
    name = "locations.get",
    path = "locations/{name}",
    httpMethod = HttpMethod.GET
  )
  public Location getLocation(@Named("name") String name) {
    Location response = new Location();
    response.setName("Location, " + name);

    return response;
  }
}
