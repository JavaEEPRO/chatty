package si.inspirited.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import si.inspirited.persistence.model.User;
import si.inspirited.service.impl.UserService;

import java.util.Map;

import static si.inspirited.web.util.UserUtil.generateUserName;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/join", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Map<String, User> initNewUser() {
        Map<String, User> res = userService.addNewUser(generateUserName());
        return res;
    }
}
