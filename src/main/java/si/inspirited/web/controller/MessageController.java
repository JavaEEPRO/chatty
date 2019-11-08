package si.inspirited.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;
import si.inspirited.persistence.model.Message;
import si.inspirited.persistence.model.User;
import si.inspirited.service.IMessageService;
import si.inspirited.service.IUserService;

import java.util.List;
import java.util.Optional;

@Controller
public class MessageController {

    @Autowired
    IUserService userService;

    @Autowired
    IMessageService messageService;

    @RequestMapping(value = { "", "/", "/messages", "/messages/{name}" }, method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<Message> getSortedMessagesList(@PathVariable
                                                           Optional<String> name) {
        return messageService.getAllSortedMessages();
    }

    @RequestMapping(value = { "/messages/{name}/say/{content}" }, method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public RedirectView postMessage(@PathVariable
                                     final String name,

                                    @PathVariable
                                     final String content) {
        Message message;
        if (isUserPresent(name)) {
            message = messageService.addNewMessage(name, content);
            userService.addHistoryEntry(name, message.id);
        }
        return new RedirectView("/messages");
    }

    @RequestMapping(value = { "/messages/{name}/to/{interlocutor}/say/{content}" }, method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public RedirectView addressedMessage(@PathVariable
                                             final String name,

                                         @PathVariable
                                         final String interlocutor,

                                         @PathVariable
                                             final String content) {
        Message message;
        if (name != null && interlocutor != null && content != null && isUserPresent(name) && isUserPresent(interlocutor)) {
            message = messageService.addNewMessage(name, interlocutor, content);
            userService.addHistoryEntry(name, message.id);
        }
        return new RedirectView("/messages");
    }

    //
    private boolean isUserPresent(String name) {
        return userService.getAllUsers().containsKey(name);
    }
}
