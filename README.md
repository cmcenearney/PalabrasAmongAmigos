¡Palabras Among Amigos!
======================

A TermsWithChums spinoff, implemented as client/server.

Goals:

  - have the server be really nice
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

then visit [http://localhost:8080]

Move syntax is pretty bad atm:  `h,6,>,word`
    - no error handling yet
    - must be comma separated: {row},{column},{^|>},{word}
    - use ">" for horizontal moves and "^" for vertical , ie `h,6,^,why`



