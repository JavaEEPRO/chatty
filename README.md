# chatty
simple chat - test task "simple web app"

1. REST API
  methods in controllers expected:
  
    UserController:
    
    /join   -  adds new user with default user name, and returns json with user' fields
     
    /join/{name} -  accepts new user with given name, and returns json with user' fields, where name=name
    
    /users/{name} - returns json (array) of objects interlocutors, with whom user can start conversation
    
    /users - returns json (array) of all users, that are present in chat
    
    MessageController:
    
    /messages - returns common sorted list with all messages, that were posted 
    
    /messages/{name}/say/{content} - if user with name=name is present, message with content=content will be posted
    
    /messages/{name}/to/{interlocutor}/say/{content} - posts addressed message with content=content  
