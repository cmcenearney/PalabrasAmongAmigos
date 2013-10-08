¡Palabras Among Amigos!
======================

A TermsWithChums spinoff, implemented as client/server.

Goals:
------

  - have the server be really nice
    - people can play each other from different clients
    - user profiles
    - save games
  - have at least one client that is acceptable (web - probably Backbone or Angular)
  - have a computer player mode that can hold it's own against really good humans (given a reasonable time limit per move - a few minutes or something)  

  
First round interation hacked together with [Dropwizard](https://github.com/dropwizard/dropwizard) and [Backbone](http://backbonejs.org/) and mongodb


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
POST    /api/new           accepts new game params, returns new game   
GET     /api/games/{id}    gets game with the id   
POST    /api/games/{id}    accepts a "move proposal", returns game (either move is made, or the game's errorMsg != null)   
```
Under consideration:
```
GET    /api/games          return list of game ids
```




