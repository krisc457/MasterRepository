package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller

/**
 * Created by Bodsten on 2017-03-30.
 */

public class loginController {
    @Autowired
    IUser iUser;
    @Autowired
    Repository repository;

    @PostMapping("/login")
    public ModelAndView getUserLogin(@RequestParam String Username, HttpSession session, @RequestParam String Password) throws Exception {
        UserLogin login = repository.getUserLogin(Username, Password);
        if (login == null) {
            return new ModelAndView("redirect:/index.html");
        }
            session.setAttribute("user", login);
        return new ModelAndView("/map").addObject("UserName",Username);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request) {
        HttpSession session= request.getSession(false);
        if(session != null) {
            session.invalidate();
        }

        return new ModelAndView("redirect:/index.html");
    }


    @GetMapping("/Signup")
    public ModelAndView form(String username, String password) {
        UserSignUp user = new UserSignUp( "", "");
        ModelAndView mv = new ModelAndView("signUp");
        mv.addObject("user", user);
        return mv;
    }

    @PostMapping("/newUser")
    public ModelAndView login(@Valid UserSignUp user, BindingResult br, HttpSession session) throws Exception {
        if (br.hasErrors()){
            return new ModelAndView("signUp").addObject("user", user);
        }
        repository.addUser(user.getUsername(), user.getPassword());
        return new ModelAndView("/map")
                .addObject("UserName", user.getUsername())
                .addObject("Password",user.getPassword());
    }


    }
