¡Palabras Among Amigos!
======================

A TermsWithChums spinoff, implemented as client/server.

GOALS
------

  - have the server be really nice
    - people can play each other from different clients
    - user profiles
    - save games
  - have at least one client that is acceptable (web - probably Backbone or Angular)
  - have a computer player mode that can hold it's own against really good humans (given a reasonable time limit per move - a few minutes or something)  

  
First round interation hacked together with [Dropwizard](https://github.com/dropwizard/dropwizard) and [Backbone](http://backbonejs.org/) and mongodb


running locally
----------------

Tested on OSX. With mongodb running on localhost, port 27017,  ¡Palabras! should be runnable with

```
git clone git@github.com:cmcenearney/PalabrasAmongAmigos.git
cd PalabrasAmongAmigos/
mvn clean install
java -jar target/palabras-among-amigos-0.0.1.jar server palabras-among-amigos.yml
```

then visit [http://localhost:8080](http://localhost:8080)

Move syntax is pretty weird and brittle atm:  
  - the format is {row},{column},{^|>},{word} ie `h,6,>,word`    
  - use ">" for horizontal moves and "^" for vertical , ie `h,6,^,why` 
  - no error handling yet  
  - must be comma separated, no spaces  


API
---

All the talking is in json. Current endpoints:

```
GET     /api/game/{id}          get game by ID
POST    /api/game               create a new game, data posted is form style, email: opponents_email
POST    /api/game/{id}/move     make a move
```

Overview
--------

1. user loads palabras, sees splash page with input for opponent's email
2. enters email, hits submit, email is posted to  /api/game
3. request routed to GameResource 
   - create Player opponent = new Player (email)
   - create GameModel game = new GameModel (opponent)
   - send email to opponent with link to game ( palabras.com/game/{game.id} )
   - save game
   - send game link back up to first player's client

- how will the client's "identity" be maintained?
  - it can pass user id (email probably for now) and game id in each request
    - pro: less client dependent than a cookie (cookies are web-specific right?)


