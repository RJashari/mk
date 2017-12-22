/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.Controller;




import Main.BL.Dega;
import Main.BL.Users;
import Main.Dao.KampanjaException;
import Main.Service.DegaService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import Main.Service.UsersService;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.validation.ConstraintViolationException;
import org.springframework.security.core.userdetails.UserDetails;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.ServletWebRequest;

/**
 *
 * @author rinor.jashari
 * mvn install:install-file -Dfile=sqljdbc4.jar -DgroupId=com.microsoft.sqlserver -DartifactId=sqljdbc4 -Dversion=4.0 -Dpackaging=jar

 */
@Controller
public class HomeController {
    
    @Autowired
    DegaService degaService;
    @Autowired 
    UsersService usersService;
   
    
     SimpleDateFormat f = new SimpleDateFormat();
    private final Logger LOGGER = Logger.getLogger(HomeController.class);
    
    @GetMapping("/login")
    public String getLogin() {
        LOGGER.info("Duke shfaqur faqen e Login-it.");

        return "login";
    }
        
   @GetMapping({"/", "/home"})
    public String getHome(Model model, Principal principal, Dega dega, Users user) {
        LOGGER.info("Duke shfaqur faqen pytesori.");
//        String username = principal.getName();
//        model.addAttribute("user", username);
//        model.addAttribute("dega", new Dega());
//        if(usersService.findUsersByUsername(principal.getName()).getRole().equals("admin")){
             model.addAttribute("deget",degaService.findAll());
//        }else{
//             model.addAttribute("deget",degaService.getAllDegetForUser(principal.getName()));
//        }
        return "index";
    }
    
//   / @PostMapping("/home")
    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String shtoPytesorin(@ModelAttribute Dega dega,Model model, Users user, Principal principal) throws KampanjaException
    {
        
        LOGGER.info("Duke ruajtur faqen pytesori.");        
        Date dnow = new Date();
       user = usersService.findUsersByUsername("");
        
        
        

        
           
        return "redirect:/home";
    }
    
  
}
