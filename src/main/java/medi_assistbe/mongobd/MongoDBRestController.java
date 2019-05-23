package medi_assistbe.mongobd;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;


@RestController
public class MongoDBRestController {

    @Autowired
    PersonalProfileRepository personalProfileRepository;
    @Autowired
    MedicalHistoryRepository medicalHistoryRepository;

    @Autowired
    ClientRepository clientRepository;

//    @RequestMapping(value = "/login",method = RequestMethod.POST)
//    public ResponseEntity<ApiToken> login(@RequestBody Client client) {
//
//        ResponseEntity<ApiToken> token =  new ResponseEntity<>(
//                new ApiToken(Jwts.builder().setSubject(client.getClientName()).claim("roles", "user")
//                        .setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "123#&*zcvAWEE999").compact()),
//                HttpStatus.OK);
//        client.setTokengiven(token.getBody().getToken());
//        clientRepository.save(client);
//        return token;
//    }

    @RequestMapping(value = "DB/clearall",method = RequestMethod.GET)
    private String clearall(){
        personalProfileRepository.deleteAll();
        medicalHistoryRepository.deleteAll();
        clientRepository.deleteAll();
        return "Done";
    }

    @RequestMapping(value = "/profile",method = RequestMethod.GET)
    public PersonalProfile getprofile(){
        return personalProfileRepository.findAll().get(0);
    }


    @RequestMapping(value = "/profile",method = RequestMethod.POST)
    public String createprofile(@RequestBody PersonalProfile personalProfile){
        givetoken(personalProfile);
        personalProfileRepository.save(personalProfile);
        return "Profile created Successfully";
    }

    @RequestMapping(value = "/profile/{mobile}",method = RequestMethod.GET)
    public PersonalProfile findBymobile(@PathVariable("mobile") String mobile){
       return personalProfileRepository.findBymobile(mobile);
    }

    @RequestMapping(value = "/profile/dob/{dateofbirth}",method = RequestMethod.GET)
    public PersonalProfile findDateofbirth(@PathVariable("dateofbirth") String dateofbirth){
        return personalProfileRepository.findByDateofbirth(dateofbirth);
    }

    @RequestMapping(value = "/profile/delete/{id}",method = RequestMethod.DELETE)
    public String deleteByid(@PathVariable("id") String id){
        personalProfileRepository.deleteById(id);
        return "Profile : " +id+" deleted Successfully";
    }

    @RequestMapping(value = "/profile/medicalhistroy",method = RequestMethod.GET)
    public List<MedicalHistory> getmedicalHistory(){
       return medicalHistoryRepository.findAll();
    }

    @RequestMapping(value = "/profile/medicalhistroy/addbyid",method = RequestMethod.POST)
    public String medicalHistoryaddition(@RequestBody MedicalHistory medicalHistory,@RequestParam(value = "id",required = true) String id){
        medicalHistoryRepository.deleteAll();
        medicalHistory.setId(id);
        medicalHistoryRepository.save(medicalHistory);
        return "Medical History Created Successfully";
    }

    @RequestMapping(value = "/profile/medicalhistroy/illness/{illness}",method = RequestMethod.GET)
    public MedicalHistory findByillness(@PathVariable("illness") String illness){
        return medicalHistoryRepository.findByillness(illness);
    }

    @RequestMapping(value = "/profile/email/{email}",method = RequestMethod.PATCH)
    public String findByEmail(@PathVariable("email") String email,@RequestParam(value = "updatedemail",required = true) String updatedemail){
        PersonalProfile p =  personalProfileRepository.findByEmail(email);
        p.setEmail(updatedemail);
        personalProfileRepository.save(p);
        return "Email Updated";
    }


    @RequestMapping(value = "/profile/medicalhistroy/id/{id}",method = RequestMethod.GET)
    public MedicalHistory findBymedicalId(@PathVariable("id")String Id){
       return medicalHistoryRepository.findByillness(Id);
    }

    public ResponseEntity<ApiToken> givetoken(PersonalProfile personalProfile) {

        ResponseEntity<ApiToken> token =  new ResponseEntity<>(
                new ApiToken(Jwts.builder().setSubject(personalProfile.getId()).claim("roles", "user")
                        .setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "123#&*zcvAWEE999").compact()),
                HttpStatus.OK);
        personalProfile.setTokengiven(token.getBody().getToken());
        return token;
    }

//    @GetMapping("/index")
//    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
//        model.addAttribute("name", name);
//        return "index";
//    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    ModelAndView
    index()
    {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("name", "Danish");
        return mav;
    }




}
