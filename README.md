# 2Gethr API

## Resources & API

Everything is done through OAuth.

### Users

| Name          | Description             | Type                 |
|---------------|-------------------------|----------------------|
| id            | Identifier              | Long                 |
| user          | Google user             | User (appengine)     |
| subscriptions | Locations subscriptions | List of locations id |

Everything is related to the authenticated user. An user cannot manage other users.

#### Create an user

`POST /users`

#### Get user' subscriptions (as Location resources)

`GET /users/subscriptions`

#### Subscribe an user to a location

`POST /users/subscriptions/{locationId}`

#### Unsubscribe an user to a location

`DELETE /users/subscriptions/{locationId}`

### Locations

| Name          | Description   | Type   |
|---------------|---------------|--------|
| id            | Identifier    | Long   |
| name          | Name          | String |
| lng           | Longitude     | Double |
| lat           | Latitude      | Double |

#### Get a location

`GET /locations/{id}`

#### List locations

`GET /locations`

#### Create a location

`POST /locations`

#### Delete a location

`DELETE /locations/{id}`

### Events

| Name          | Description             | Type                 |
|---------------|-------------------------|----------------------|
| id            | Identifier              | Long                 |
| location      | Location's id           | Long                 |
| participants  | Participants' id        | List of user id      |

#### Create an event

`POST /events`

#### Get an event

`GET /events/{id}`

#### List events

`GET /events`

## Development

Build project :

`mvn clean package`

Start local server :

`mvn appengine:devserver`

You can acces the local API explorer here :

http://localhost:9090/_ah/api/explorer

Hot-reload is available too :

`mvn package`

And local Datastore is here :

http://localhost:9090/_ah/admin

To deploy on App Engine :

`mvn appengine:update`

Enjoy! :dancers:
