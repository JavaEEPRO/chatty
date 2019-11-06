# chatty
simple chat - test task "simple web app"

1. REST API
  methods in controllers expected:
  
    UserController:
    
    \join\   -  adds new user with default user name, returns Map<String, User> with all users, that currently connected, where "key" is user name
     
    \join\{name} -  accepts new user with given name, returns Map<String, User> with all users, that currently connected, where "key" is user name 
