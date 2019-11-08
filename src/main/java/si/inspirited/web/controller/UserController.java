package si.inspirited.web.controller;

import io.micrometer.core.lang.Nullable;
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
import si.inspirited.service.impl.UserService;

import java.util.*;

import static si.inspirited.web.util.UserUtil.generateUserName;

@Controller
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    IMessageService messageService;

    User currentUser;

    @RequestMapping(value = { "/join", "/join/{name}" }, method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public RedirectView initNewUser(@PathVariable
                                    Optional<String> name) {

        if (userService.getAllUsers().containsKey(name)) { return new RedirectView("/welcome/" + name); }

        User res = currentUser = userService.addNewUser(name.orElse(""));

        return new RedirectView("/welcome/" + res.name);
    }

    @RequestMapping(value = { "/welcome/{name}" }, method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public User successfullyJoined(@PathVariable
                                   Optional<String> name) {
        messageService.addNewMessage(currentUser.name, "joined: Hello everybody!");

        return currentUser;
    }

    @RequestMapping(value = { "/users", "/users/{name}" }, method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<User> getAllLoggedIn(@PathVariable
                                     Optional<String> name) {
        Map<String, User> res = userService.getAllUsers();
        List<User> toList = new ArrayList<>();
        String strName = name.orElse("");
        if (!"".equals(name.orElse("").trim())) {
            for (Map.Entry<String, User> user : res.entrySet()) {
                if (!user.getKey().equals(strName)) {
                    toList.add(user.getValue());
                }
            }
        }
        else {  toList = new ArrayList<>(res.values()); }
        return toList;
    }
}
