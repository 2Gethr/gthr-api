package io.gthr.api;

import javax.inject.Named;

import io.gthr.entities.Location;
import io.gthr.repositories.LocationRepository;

import com.google.appengine.api.users.User;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.UnauthorizedException;

@Api(
  name = "gthr",
  version = "v1",
  scopes = {Constants.EMAIL_SCOPE},
  clientIds = {Constants.WEB_CLIENT_ID, com.google.api.server.spi.Constant.API_EXPLORER_CLIENT_ID}
)
public class LocationAPI {

  @ApiMethod(
    name = "locations.get",
    path = "locations/{name}",
    httpMethod = HttpMethod.GET
  )
  public Location get(
    @Named("name") String name,
    User user
  ) throws UnauthorizedException {
    if (user == null) throw new UnauthorizedException("#loginrequired");

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
    @Named("lat") double lat,
    User user
  ) throws UnauthorizedException {
    if (user == null) throw new UnauthorizedException("#loginrequired");

    return LocationRepository.instance().create(new Location(name, lng, lat));
  }

  @ApiMethod(
    name = "locations.delete",
    path = "locations/{name}",
    httpMethod = HttpMethod.DELETE
  )
  public Location delete(
    @Named("name") String name,
    User user
  ) throws UnauthorizedException {
    if (user == null) throw new UnauthorizedException("#loginrequired");

    return LocationRepository.instance().delete(name);
  }
}
