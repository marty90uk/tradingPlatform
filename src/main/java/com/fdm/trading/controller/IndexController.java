package com.fdm.trading.controller;

import com.fdm.trading.domain.Account;
import com.fdm.trading.domain.User;
import com.fdm.trading.service.userServiceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class IndexController {

    @Autowired
    UserServiceImpl userService;

    @RequestMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("user", new User());
        System.out.println("foof");
        return "newIndex";
    }

//    @RequestMapping(value = "/signup", method = RequestMethod.GET)
//    public String signup(Model model) {
//        User user = new User();
//        model.addAttribute("user", user);
//        return "signup";
//    }

  /*  @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPost(@ModelAttribute("user") User user, Model model) {

        if (userService.checkUserExists(user.getUsername(), user.getEmail())) {
            if (userService.checkEmailExists(user.getEmail())) {
                model.addAttribute("emailExists", true);
            }
            if (userService.checkUsernameExists(user.getUsername())) {
                model.addAttribute("usernameExists", true);
            }
        }
        return "signup";
    }*/


//  @RequestMapping(value = "/index", method = RequestMethod.POST)
//  public String singOnVerification(Model model, @ModelAttribute User user ){
//      User newUser = userService.findByUsername(user.getUsername());
//      model.addAttribute("newUser", newUser);
//      return "result";
//  }

    @RequestMapping(value = "/userHome", method = RequestMethod.POST)
    public String signupPost(@ModelAttribute User user, Model model, HttpSession session) {
        System.out.println("bar");
        System.out.println(user.getUsername());
        User newUser = userService.findByUsername(user.getUsername());
        List<User> users = userService.findUserList();

        model.addAttribute("newUser", newUser);
        model.addAttribute("validated", userService.validateUser(newUser, user.getPassword()));
        session.setAttribute("account", newUser.getAccount());
        model.addAttribute("account", newUser.getAccount());
        System.out.println("newUser------>"+newUser);
        System.out.println("user>>>>>>>>>>>>>>>>>>>>>>>"+user);
       if (userService.validateUser(newUser, user.getPassword())){

           return "userHome";
       }
        return "signup";
    }

    @RequestMapping(value = "/userHome", method = RequestMethod.GET)
    public String userHome(Model model, User user) {
        user = userService.findByUsername(user.getUsername());
        Account account = user.getAccount();
        model.addAttribute("account", account);
        return "userHome";
    }
}
