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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
public class UserController {
    
    @Autowired
    DegaService degaService;
    @Autowired 
    UsersService usersService;
   
    
     SimpleDateFormat f = new SimpleDateFormat();
    private final Logger LOGGER = Logger.getLogger(UserController.class);
    public String createDate;
    private String DEFAULT_PASSWORD ="$2a$10$5kCKO/IAcqcrAy0IzrHFK.kEVBeBKVn8j/m4xcN7TTBhb1RJ3GJ7S";//password00;
    private String DISABLE_PASSWORD= "$2a$10$zSsGzxXbMPJPioS.rnlI1.1paqiIHqzwhY852avMghuWK4CnBHKXm";//DisPass123!
    private BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
    
    @GetMapping("/users")
    public String getUsers(Model model, Users user, Principal principal) {
        LOGGER.info("Duke shfaqur faqen e shfrytezuesve");
        user = usersService.findUsersByUsername(principal.getName());
        
            if(user.getRole().equals("admin")){
            LOGGER.info("Duke shfaqur faqen shfytezuesit.");

                model.addAttribute("user", new Users());
               
                model.addAttribute("allUsers", usersService.findAll());
                return "user";
            }else{
                LOGGER.info("Nuk keni casje");
                return "403";
            } 
    }
      @GetMapping("/users/remove/{id}")
	public String removeUser(@PathVariable int id, Model model) throws KampanjaException {
		LOGGER.info("Duke fshire userin me id: " + id);
//              
                Users user = usersService.findById(id);
                user.setEnable(false);
                user.setPassword(DISABLE_PASSWORD);
                usersService.edit(user);
                LOGGER.info("Eshte fshire user-i : " + user.getUsername());
		return "redirect:/users";
	}
        @GetMapping("/users/resetPassword/{id}")
	public String resetUserPassword(@PathVariable int id, Model model) throws KampanjaException {
                Users user = usersService.findById(id);
		LOGGER.info("Duke resetuar passwordin per userin me username: " + user.getUsername());
		usersService.resetUserPassword(id);

		return "redirect:/users";
	}
        @GetMapping("/users/add")
        public String addUser(Model model) throws KampanjaException{
            LOGGER.info("Duke shfaqur formen Shto Shfrytezuesin");
            
            model.addAttribute("user", new Users());
            
//            
//            user.setRole(roli);
//            System.out.println("Roli i Userit: "+roli);
//            usersService.create(user);
//            
            return "addUser";
        }
        @GetMapping("/users/rikthe")
        public String riktheUser(Model model, Users user, Principal principal) throws KampanjaException{
            LOGGER.info("Duke shfaqur formen Shto Shfrytezuesin");
            
             user = usersService.findUsersByUsername(principal.getName());
        
            if(user.getRole().equals("admin")){
            LOGGER.info("Duke shfaqur faqen shfytezuesit.");

                model.addAttribute("user", new Users());
               
                model.addAttribute("allUsers", usersService.findAll());

                return "riktheUser";
            }else{
                LOGGER.info("Nuk keni casje");
                return "403";
            } 
        
        }
       @RequestMapping(value = "/users/add", method = RequestMethod.POST)
        public String addUserPost(@ModelAttribute Users user, @RequestParam String roli, Model model) throws KampanjaException{
            LOGGER.info("Duke ruajtur shfrytezuesin: "+user.getUsername());
            user.setEnable(true);
            user.setRole(roli);
            System.out.println("Roli i Userit: "+roli);
            usersService.create(user);
            
            return "redirect:/users";
                    
        }
        @RequestMapping(value = "/users/rikthe/{id}", method = RequestMethod.GET)
	public String riktheUserPost(@PathVariable int id, Model model) throws KampanjaException {
                Users user = usersService.findById(id);
                user.setEnable(true);
                user.setPassword(DEFAULT_PASSWORD);
		LOGGER.info("Duke rikthyer shfrytezuesin me username: " + user.getUsername());
		usersService.edit(user);

		return "redirect:/users";
	}
                    
        
         @GetMapping("/changePassword")
        public String getChangePassword(Model model, Users user,Principal principal){
            LOGGER.info("Duke hapur formen ndrysho fjalekalimin");
            user = usersService.findUsersByUsername(principal.getName());
            System.out.println("principal:  "+principal.getName());
            System.out.println("Username:  "+user.getUsername());
            model.addAttribute("user",user);
            return "changePassword";
            
        }
        @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
        public String postChangePsw(@RequestParam("psw") String newPassword, Users user, Principal principal) throws KampanjaException{
             user = usersService.findUsersByUsername(principal.getName());
            LOGGER.info("Duke ndryshuar fjalekalimin per userin: "+user.getUsername());
            user.setPassword(encode.encode(newPassword));
            usersService.edit(user);
            if(user.getRole().equals("admin"))
            return "redirect:/users";
            else{
                return "redirect:/home";
            }
        
        
        }
        
   
    

         
}       
