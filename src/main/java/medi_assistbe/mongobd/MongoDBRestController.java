package medi_assistbe.mongobd;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MongoDBRestController {

    @Autowired
    PersonalProfileRepository personalProfileRepository;

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
