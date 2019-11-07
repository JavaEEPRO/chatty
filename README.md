# chatty
simple chat - test task "simple web app"

1. REST API
  methods in controllers expected:
  
    UserController:
    
    /join/   -  adds new user with default user name, and returns json with user' fields
     
    /join/{name} -  accepts new user with given name, and returns json with user' fields, where name=name
    
    /messages - returns common sorted list with all messages, that were posted 
    
    /messages/{name}/say/{content} - if user with name=name is present, message with content=content will be posted 
