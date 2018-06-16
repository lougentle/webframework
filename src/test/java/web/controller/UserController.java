package web.controller;

import com.sarakeal.annotation.Action;
import com.sarakeal.annotation.Autowired;
import com.sarakeal.annotation.Controller;
import com.sarakeal.bean.Data;
import web.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Action("GET:/list")
    public Data list() {
        Data data = new Data(userService.getUser());
        return data;
    }
}
