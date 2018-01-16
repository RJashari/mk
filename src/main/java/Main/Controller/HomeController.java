/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.Controller;




import Main.BL.Dega;
import Main.BL.Users;
import Main.Dao.KampanjaException;
import Main.GjeneroAddPdf;
import Main.GjeneroAllPdf;
import Main.GjeneroPdf;
import Main.GjeneroPdfDega;
import Main.GjeneroPdfDelete;
import Main.SendMail;
import Main.Service.DegaService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import Main.Service.UsersService;
import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.mail.NoSuchProviderException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    @Autowired
    GjeneroPdf gjeneroPdf;
    @Autowired
    GjeneroPdfDega gjeneroPdfDega;
    @Autowired
    GjeneroAddPdf gjeneroAddPdf;
    @Autowired
    GjeneroAllPdf gjeneroAllPdf;
    @Autowired
    GjeneroPdfDelete gjeneroPdfDelete;
    
   
    
     SimpleDateFormat f = new SimpleDateFormat();
    private final Logger LOGGER = Logger.getLogger(HomeController.class);
    public String createDate;
    
    @GetMapping("/login")
    public String getLogin() {
        LOGGER.info("Duke shfaqur faqen e Login-it.");

        return "login";
    }
        
   @GetMapping({"/", "/home"})
    public String getHome(Model model, Principal principal, Dega dega, Users user) {
        user = usersService.findUsersByUsername(principal.getName());
        System.out.println("useri----------"+user.getUsername());
        System.out.println("principal----------"+principal.getName());
        model.addAttribute("user", user.getRole());
        if(user.getRole().equals("admin"))
        {
             LOGGER.info("Duke shfaqur faqen Deget me casje admin.");
             model.addAttribute("deget",degaService.findAll());
        }else{
             LOGGER.info("Duke shfaqur faqen Deget me casje sfhrytezues.");
             model.addAttribute("deget", degaService.getAllDegetForUser(principal.getName()));
        }
        return "index";
    }
    
////   / @PostMapping("/home")
//    @RequestMapping(value = "/home", method = RequestMethod.POST)
//    public String shtoPytesorin(@ModelAttribute Dega dega,Model model, Users user, Principal principal) throws KampanjaException
//    {
//        
//       LOGGER.info("Duke ruajtur faqen pytesori.");        
//       Date dnow = new Date();
//       user = usersService.findUsersByUsername("");
//        
//        
//        
//
//        
//           
//        return "redirect:/home";
//    }
    @GetMapping("/deget/remove/{id}")
	public String removeDega(@PathVariable int id, Model model, Dega dega, Principal principal,Users user) throws KampanjaException, ParseException, IOException, NoSuchProviderException {
		LOGGER.info("Duke fshire degen me id: " + id+" nga shfrytezuesi: "+principal.getName());
                dega = degaService.findById(id);
                Date dnow = new Date();
                SimpleDateFormat ft1 = new SimpleDateFormat("dd_MM_yyyy");
                user = usersService.findUsersByUsername(principal.getName());
                String dataFshirjes = ft1.format(dnow);
                String subjectEmail = "Është fshier dega \""+ dega.getEmri()+"\"";
		String bodyEmail ="Është fshier dega \""+dega.getEmri()+ "\" ne daten: "+dataFshirjes; 
                Users pergjegjesi = usersService.findUsersByUsername(dega.getPergjegjesi());
                System.out.println("pergjegjesi1234 : "+pergjegjesi.getUsername());
                dega = degaService.findById(id);
                gjeneroPdfDelete = new GjeneroPdfDelete(user.getUsername(), user.getEmail(), pergjegjesi.getEmail());
                String fileName = gjeneroPdfDelete.gjeneroPdf(dega);
                degaService.remove(dega);
             
                
                SendMail sendMail = new SendMail();
                sendMail.sendEmail(fileName, dega.getEmri(), dataFshirjes, subjectEmail, bodyEmail, user.getEmail(), pergjegjesi.getEmail(), user.getRole());
                LOGGER.info("Eshte fshire dega : " + dega.getEmri());
		return "redirect:/home";
	}
        @GetMapping("/deget/gjeneroPdf/{id}")
	public String gjeneroPdfDega(@PathVariable int id, Model model, Dega dega, Principal principal,Users user) throws KampanjaException, ParseException, IOException, NoSuchProviderException {
		LOGGER.info("Duke gjeneruar Pdf per degen degen me id: " + id+" nga shfrytezuesi: "+principal.getName());
                dega = degaService.findById(id);
                Date dnow = new Date();
                SimpleDateFormat ft1 = new SimpleDateFormat("dd_MM_yyyy");
                user = usersService.findUsersByUsername(principal.getName());
                String dataFshirjes = ft1.format(dnow);
                String subjectEmail = "Është gjeneruar pdf per degen \""+ dega.getEmri()+"\"";
		String bodyEmail ="Është gjeneruar pdf per degen \""+dega.getEmri()+ "\" ne daten: "+dataFshirjes; 
                Users pergjegjesi = usersService.findUsersByUsername(dega.getPergjegjesi());
                System.out.println("pergjegjesi1234 : "+pergjegjesi.getUsername());
                dega = degaService.findById(id);
                gjeneroPdfDega = new GjeneroPdfDega(user.getUsername(), user.getEmail(), pergjegjesi.getEmail());
                String fileName = gjeneroPdfDega.gjeneroPdf(dega);
               
                SendMail sendMail = new SendMail();
                sendMail.sendEmail(fileName, dega.getEmri(), dataFshirjes, subjectEmail, bodyEmail, user.getEmail(), pergjegjesi.getEmail(), user.getRole());
                LOGGER.info("Eshte  gjeneruar pdf per degen : " + dega.getEmri());
		return "redirect:/home";
	}
        @GetMapping("/deget/add")
        public String addDega(Model model, Dega dega) throws KampanjaException {
		LOGGER.info("Duke hapur formen per te shtuar deget");
//              
                model.addAttribute("dega", new Dega());
                
		return "addDega";
	}
        
        @GetMapping("/deget/modify/{id}")
        public String modifyDega(@PathVariable int id,Model model, Dega dega, Users user, Principal principal) throws KampanjaException {
		LOGGER.info("Duke hapur formen per te modifikuar deget");
                user = usersService.findUsersByUsername(principal.getName());
                dega = degaService.findById(id);
                model.addAttribute("roli", user.getRole());
                model.addAttribute("dega", dega);
                System.out.println("-------- emri deges modifiko : "+dega.getEmri());
		return "modifyDega";
	}
        @GetMapping("/deget/gjeneroAllPdf")
        public String gjeneroAllDeget(Model model, Dega dega, Users user, Principal principal) throws KampanjaException, ParseException, IOException, NoSuchProviderException {
		LOGGER.info("Duke gjeneruar pdf te gjitha deget");
                user = usersService.findUsersByUsername(principal.getName());
                Date dnow = new Date();
                SimpleDateFormat ft1 = new SimpleDateFormat("dd_MM_yyyy");
                String dayOfModification = ft1.format(dnow);
                List<Dega> deget = degaService.findAll();
                String subjectEmail = "Të gjitha degët pdf file";
		String bodyEmail ="Pasqyrimi i te gjitha degëve për datën: "+dayOfModification; 
                gjeneroAllPdf= new GjeneroAllPdf(deget, user.getEmail());
		String fileName = gjeneroAllPdf.gjeneroPdf(deget);
                SendMail sendMail = new SendMail();
		sendMail.sendEmail(fileName,"TeGjithaDeget", dayOfModification, subjectEmail, bodyEmail, user.getEmail(),"",user.getRole());
      
		return "redirect:/home";
	}
        @RequestMapping(value = "/deget/add", method = RequestMethod.POST) 
         public String addDegaPost(Model model,@ModelAttribute Dega dega, Users user, Principal principal) throws KampanjaException, ParseException, IOException, NoSuchProviderException {
		LOGGER.info("Duke ruajtur degen");
                user = usersService.findUsersByUsername(principal.getName());
                Date dnow = new Date();
                SimpleDateFormat ft = new SimpleDateFormat("HH:mm - dd/MM/yyyy");
                String dataKrijimit = ft.format(dnow);
                dega.setDataKrijimit(dataKrijimit);
                createDate = dataKrijimit;
                dega.setDataModifikimit("Nuk ka");
                SimpleDateFormat ft1 = new SimpleDateFormat("dd_MM_yyyy");
                String subjectEmail = "Është shtuar dega";
		String dayOfModification = ft1.format(dnow);
		String bodyEmail ="Është shtuar dega \""+dega.getEmri()+"\" në datën: "+dayOfModification;
		Users pergjegjesi  = usersService.findUsersByUsername(dega.getPergjegjesi());
               
                System.out.println("--------------------"+pergjegjesi.getEmail());
                gjeneroAddPdf = new GjeneroAddPdf(user.getUsername(),user.getEmail(),pergjegjesi.getEmail());
		String fileName = gjeneroAddPdf.gjeneroAddPdf(dega);
                degaService.create(dega);
                SendMail sendMail = new SendMail();
		sendMail.sendEmail(fileName,dega.getEmri(), dayOfModification, subjectEmail, bodyEmail,user.getEmail(), pergjegjesi.getEmail(),user.getRole());
                
               
		return "redirect:/home";
	}
         @RequestMapping(value = "/deget/modify", method = RequestMethod.POST) 
         public String modifyDegaPost(Model model,@ModelAttribute Dega dega, Principal principal, Users user) throws KampanjaException, ParseException, NoSuchProviderException, IOException {
		LOGGER.info("Duke modifikuar degen");
                Dega degaOld =  degaService.findById(dega.getDegaID());
//                dega.getEmri();     
                System.out.println("degaOld--------------"+degaOld.getDataKrijimit());
                dega.setDataKrijimit(degaOld.getDataKrijimit());
                Date dnow = new Date();
                SimpleDateFormat ft = new SimpleDateFormat("HH:mm - dd/MM/yyyy");
                String dataModifikimit = ft.format(dnow);
                SimpleDateFormat ft1 = new SimpleDateFormat("dd.MM.yyyy");
		String dayOfModification = ft1.format(dnow);
		String subjectEmail = "Është modifikuar dega";
		String bodyEmail ="Është modifikuar dega \""+dega.getEmri()+"\" në datën: "+dayOfModification;
                Users pergjegjesi = usersService.findUsersByUsername(dega.getPergjegjesi());
                user = usersService.findUsersByUsername(principal.getName());
                
                dega.setDataModifikimit(dataModifikimit);
//                
                gjeneroPdf = new GjeneroPdf(user.getUsername(), user.getEmail(),pergjegjesi.getEmail());
		String fileName = gjeneroPdf.gjeneroPdf(dega);
              
                degaService.edit(dega);
                SendMail sendMail = new SendMail();
		sendMail.sendEmail(fileName,dega.getEmri(), dayOfModification, subjectEmail, bodyEmail, user.getEmail(), pergjegjesi.getEmail(),user.getRole());
		return "redirect:/home";
	}
         
}       
