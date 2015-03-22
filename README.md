# 2Gethr API

## Resources & API

Everything is done through OAuth.

### Users

| Name          | Description              | Type                 |
|---------------|--------------------------|----------------------|
| user          | Google user              | User (appengine)     |
| subscriptions | Locations subscriptions  | List of locations id |

#### Get an user

`GET /users/{id}`

#### Create an user

`POST /users`

#### Delete an user

`DELETE /users/{id}`

#### Subscribe an user to a location

`POST /users/{id}/subscriptions/{locationId}`

#### Unsubscribe an user to a location

`DELETE /users/{id}/subscriptions/{locationId}`

### Locations

| Name          | Description   | Type   |
|---------------|---------------|--------|
| name          | name          | String |
| lng           | Longitude     | Double |
| lat           | Latitude      | Double |

#### Get a location

`GET /locations/{id}`

#### Create a location

`POST /locations`

#### Delete a location

`DELETE /locations/{id}`

## Development

Build project :

`mvn clean package`

Start local server :

`mvn appengine:devserver`

You can acces the local API explorer here :

http://localhost:9090/_ah/api/explorer

And local Datastore here :

http://localhost:9090/_ah/admin

Enjoy ! :dancers:
