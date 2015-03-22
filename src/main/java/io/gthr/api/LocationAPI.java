package io.gthr.api;

import io.gthr.entities.Location;
import io.gthr.repositories.LocationRepository;

import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;

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
  public Location get(@Named("name") String name) {
    return LocationRepository.instance().get(name);
  }

  @ApiMethod(
    name = "locations.create",
    path = "locations",
    httpMethod = HttpMethod.POST
  )
  public Location create(
    @Named("name") String name,
    @Named("lng") double lng,
    @Named("lat") double lat
  ) {
    return LocationRepository.instance().create(new Location(name, lng, lat));
  }
}
