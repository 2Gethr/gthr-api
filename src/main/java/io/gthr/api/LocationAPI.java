package io.gthr.api;

import io.gthr.entities.Location;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import io.gthr.repositories.LocationRepository;

import javax.inject.Named;

@Api(
  name = "gthr",
  version = "v1"
)
public class LocationAPI {

  @ApiMethod(
    name = "locations.get",
    path = "locations/{id}",
    httpMethod = HttpMethod.GET
  )
  public Location get(@Named("id") Long id) {
    return LocationRepository.instance().get(id);
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
