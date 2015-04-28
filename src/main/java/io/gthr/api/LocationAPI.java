package io.gthr.api;

import java.util.List;

import javax.inject.Named;

import io.gthr.entities.Location;
import io.gthr.repositories.LocationRepository;

import com.google.appengine.api.users.User;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;

@Api(
  name = "gthr",
  version = "v1",
  scopes = {Constants.EMAIL_SCOPE},
  clientIds = {Constants.WEB_CLIENT_ID, com.google.api.server.spi.Constant.API_EXPLORER_CLIENT_ID}
)
public class LocationAPI {
  // @fixme Manage admin access

  @ApiMethod(
    name = "locations.get",
    path = "locations/{id}",
    httpMethod = HttpMethod.GET
  )
  public Location get(
    @Named("id") Long id,
    User user
  ) throws OAuthRequestException {
    if (user == null) throw new OAuthRequestException("#loginrequired");

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
    @Named("lat") double lat,
    User user
  ) throws OAuthRequestException {
    if (user == null) throw new OAuthRequestException("#loginrequired");

    return LocationRepository.instance().create(new Location(name, lng, lat));
  }

  @ApiMethod(
    name = "locations.list",
    path = "locations",
    httpMethod = HttpMethod.GET
  )
  public List<Location> list(User user) throws OAuthRequestException {
    if (user == null) throw new OAuthRequestException("#loginrequired");

    return LocationRepository.instance().list();
  }

  @ApiMethod(
    name = "locations.delete",
    path = "locations/{id}",
    httpMethod = HttpMethod.DELETE
  )
  public Location delete(
    @Named("id") Long id,
    User user
  ) throws OAuthRequestException {
    if (user == null) throw new OAuthRequestException("#loginrequired");

    return LocationRepository.instance().delete(id);
  }
}
