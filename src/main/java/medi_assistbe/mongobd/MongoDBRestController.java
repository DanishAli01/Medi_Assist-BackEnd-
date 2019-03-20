package medi_assistbe.mongobd;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class MongoDBRestController {

    @Autowired
    PersonalProfileRepository personalProfileRepository;

    @Autowired
    ClientRepository clientRepository;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity<ApiToken> login(@RequestBody Client client) {

        ResponseEntity<ApiToken> token =  new ResponseEntity<>(
                new ApiToken(Jwts.builder().setSubject(client.getClientName()).claim("roles", "user")
                        .setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "123#&*zcvAWEE999").compact()),
                HttpStatus.OK);
        client.setTokengiven(token.getBody().getToken());
        clientRepository.save(client);
        return token;
    }

    @RequestMapping(value = "/profile",method = RequestMethod.GET)
    public List<PersonalProfile> getprofile(){
        return personalProfileRepository.findAll();
    }

    @RequestMapping(value = "/profile",method = RequestMethod.POST)
    public String createprofile(@RequestBody PersonalProfile personalProfile){
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


}
