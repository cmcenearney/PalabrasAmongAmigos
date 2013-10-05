¡Palabras Among Amigos!
======================

first iteration:
  - splash screen, button sends request to server for new game w/defaults
  - server inits new game, saves it, returns
  - client refreshes view
  - submit move -> request to server with move
  - server reads game from db into object, checks move and either
    - makes move, returns updated game
    - returns error message

todo:
  - "new" page with config options
  - store user profiles
  - auth
  - computer player mode