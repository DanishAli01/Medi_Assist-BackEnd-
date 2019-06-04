package medi_assistbe.mongobd;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.JSONException;
import org.json.JSONObject;
import org.omg.CORBA.Environment;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@RestController
public class MongoDBRestController {

    @Autowired
    PersonalProfileRepository personalProfileRepository;
    @Autowired
    MedicalHistoryRepository medicalHistoryRepository;
    @Autowired
    VitalsRepository vitalsRepository;
    @Autowired
    InsuranceDetailsRepository insuranceDetailsRepository;
    @Autowired
    GPDetailsRepository gpDetailsRepository;
    @Autowired
    KinRepository kinRepository;
    @Autowired
    medicationRepository medicationRepository;
    @Autowired
    bookingsRepository bookingsRepository;





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
        return "Done";
    }

    @RequestMapping(value = "/bookigs/add/{id}",method = RequestMethod.POST)
    public String bookingsadd(@RequestBody bookings insuranceDetails,@PathVariable ("id") String id){
        if(personalProfileRepository.existsById(id)) {
            insuranceDetails.setMap(id);
            bookingsRepository.save(insuranceDetails);
            return "Profile created Successfully";
        }
        else {
            return "Error Occured, Please Try Again";
        }
    }
    @RequestMapping(value = "/bookigs/get/{id}",method = RequestMethod.GET)
    public bookings bookingsget(@PathVariable ("id") String id){
        if(personalProfileRepository.existsById(id)) {
            return bookingsRepository.findByMap(id);
        }
        else {
            return null;
        }
    }

    @RequestMapping(value = "/bookigs/delete/{id}",method = RequestMethod.DELETE)
    public String bookingsdelete(@PathVariable ("id") String id){
        if(personalProfileRepository.existsById(id)) {
            bookingsRepository.deleteByMap(id);
            return "Done";}
        else {
            return "Failed";
        }
    }

    @RequestMapping(value = "/profile",method = RequestMethod.GET)
    public PersonalProfile getprofile(){
        return personalProfileRepository.findAll().get(0);
    }

    @RequestMapping(value = "/profile/find/{id}",method = RequestMethod.GET)
    public PersonalProfile getprofilebyID(@PathVariable("id") String id){
        Optional<PersonalProfile> optionalPersonalProfile = personalProfileRepository.findById(id);
        if (!optionalPersonalProfile.isPresent()) {
            return null;
        } else {
            PersonalProfile p = optionalPersonalProfile.get();
            return p;
        }
    }





    @RequestMapping(value = "/profile/find/byemail/{email}",method = RequestMethod.GET)
    public String getprofilebyEmail(@PathVariable("email") String email){
        if(personalProfileRepository.existsByEmail(email))
        return personalProfileRepository.findByEmail(email).getPassword();
        else return "User doesn't exsists!";

    }
    @RequestMapping(value = "/profile/find/byemail/signup/{email}",method = RequestMethod.GET)
    public String getprofilebyEmailforid(@PathVariable("email") String email){
        if(personalProfileRepository.existsByEmail(email))
            return personalProfileRepository.findByEmail(email).getId();
        else return "User doesn't exsists!";

    }
    @RequestMapping(value = "/profile/find/byemailafterlogin/{email}",method = RequestMethod.GET)
    public PersonalProfile getprofilebyEmailafterLogin(@PathVariable("email") String email){
        return personalProfileRepository.findByEmail(email);

    }


    @RequestMapping(value = "/gpdetails/find/{map}",method = RequestMethod.GET)
    public GPDetails getgpdetailsbyID(@PathVariable("map") String id){
       return gpDetailsRepository.findByMap(id);
    }

    @RequestMapping(value = "/medicalhistory/add/{id}",method = RequestMethod.POST)
    public String createmedicalhistory(@RequestBody MedicalHistory medicalHistory,@PathVariable ("id") String id){
        if(personalProfileRepository.existsById(id)) {
            medicalHistory.setMap(id);
            medicalHistoryRepository.save(medicalHistory);
            return "Profile created Successfully";
        }
        else {
            return "Error Occured, Please Try Again";
        }
    }

    @RequestMapping(value = "/gp/add/{id}",method = RequestMethod.POST)
    public String addgp(@RequestBody GPDetails gpDetails,@PathVariable ("id") String id){
        if(personalProfileRepository.existsById(id)) {
            gpDetails.setMap(id);
            gpDetailsRepository.save(gpDetails);
            return "Profile created Successfully";
        }
        else {
            return "Error Occured, Please Try Again";
        }
    }

    @RequestMapping(value = "/insurancedetails/add/{id}",method = RequestMethod.POST)
    public String insurancedetails(@RequestBody InsuranceDetails insuranceDetails,@PathVariable ("id") String id){
        if(personalProfileRepository.existsById(id)) {
            insuranceDetails.setMap(id);
            insuranceDetailsRepository.save(insuranceDetails);
            return "Profile created Successfully";
        }
        else {
            return "Error Occured, Please Try Again";
        }
    }

    @RequestMapping(value = "/insurancedetails/get/{id}",method = RequestMethod.POST)
    public InsuranceDetails insurancedetailsget(@PathVariable ("id") String id){
        if(personalProfileRepository.existsById(id)) {
           return insuranceDetailsRepository.getByMap(id);

        }
        else {
            return null;
        }
    }

    @RequestMapping(value = "/vitals/add/{id}",method = RequestMethod.POST)
    public String setvitals(@RequestBody Vitals vitals,@PathVariable ("id") String id){
        if(personalProfileRepository.existsById(id)) {
            vitals.setMap(id);
            vitals.setTime(getCurrentTimeUsingDate());
            vitalsRepository.save(vitals);
            return "Profile created Successfully";
        }
        else {
            return "Error Occured, Please Try Again";
        }
    }

    @RequestMapping(value = "/vitals/get/{id}",method = RequestMethod.GET)
    public Vitals getvitals(@PathVariable ("id") String id){
        if(personalProfileRepository.existsById(id)) {

            return vitalsRepository.findAllByMap(id).get(vitalsRepository.findAllByMap(id).size() - 1);
        }
        return null;
    }


    @RequestMapping(value = "/vitals/top/get/{id}",method = RequestMethod.GET)
    public Vitals getvitalstop(@PathVariable ("id") String id){
        if(personalProfileRepository.existsById(id)) {

           List<Vitals> vitals = vitalsRepository.findAllByMap(id);
            return vitals.get(vitals.size()-1);
        }
        return null;
    }

    @RequestMapping(value = "/vitals/top/get/heartrate/graph/{id}",method = RequestMethod.GET)
    public List<Vitals> getvitalheartrategraph(@PathVariable ("id") String id){
        if(personalProfileRepository.existsById(id)) {

          return vitalsRepository.findAllByMap(id);

        }
        return null;
    }



    @RequestMapping(value = "/kin/add/{id}",method = RequestMethod.POST)
    public String addkin(@RequestBody Kin kin,@PathVariable ("id") String id){
        if(personalProfileRepository.existsById(id)) {
            kin.setMap(id);
            kinRepository.save(kin);
            return "Profile created Successfully";
        }
        else {
            return "Error Occured, Please Try Again";
        }
    }


    @RequestMapping(value = "/profile/add/new",method = RequestMethod.POST)
    public String createprofile(@RequestBody PersonalProfile personalProfile){
        token(personalProfile);
        personalProfileRepository.save(personalProfile);
        return "Profile created Successfully";
    }

    @RequestMapping(value = "/profile/mobile/{mobile}",method = RequestMethod.GET)
    public String findBymobile(@PathVariable("mobile") String mobile){
       return personalProfileRepository.findBymobile(mobile).getId();
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
    public String medicalHistoryaddition(@RequestBody MedicalHistory medicalHistory,@RequestParam(value = "map",required = true) String map){
        medicalHistory.setMap(map);
        medicalHistoryRepository.save(medicalHistory);
        return "Medical History Created Successfully";
    }

    @RequestMapping(value = "/profile/medication/addbyid",method = RequestMethod.POST)
    public String medicationaddition(@RequestBody medication medication,@RequestParam(value = "map",required = true) String map){
        medication.setMap(map);
        medicationRepository.save(medication);
        return "Medication profile Created Successfully";
    }


    @RequestMapping(value = "/profile/medication/getall/{id}",method = RequestMethod.GET)
    public List<medication> getmedication(@PathVariable("id") String id){
        return medicationRepository.findAllByMap(id);
    }

    @RequestMapping(value = "/profile/illness/getall/{id}",method = RequestMethod.GET)
    public List<MedicalHistory> getillnesses(@PathVariable("id") String id){
        return medicalHistoryRepository.findAllByMap(id);
    }

    @RequestMapping(value = "/profile/kin/getall/{id}",method = RequestMethod.GET)
    public List<Kin> getkin(@PathVariable("id") String id){
        return kinRepository.findAllByMap(id);
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

    @RequestMapping(value = "/profile/get/id/{dateofbirth}",method = RequestMethod.GET)
    public JSONObject findByname(@PathVariable("dateofbirth")String dateofbirth) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("id",personalProfileRepository.findByDateofbirth(dateofbirth).getId());
        return json;
    }

    public ResponseEntity<ApiToken> token(PersonalProfile personalProfile) {
        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(10);

        for (int i = 0; i < 10; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        ResponseEntity<ApiToken> token =  new ResponseEntity<>(
                new ApiToken(Jwts.builder().setSubject(personalProfile.getId()).claim("roles", "user")

                        .setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, sb+"123#&*zcvAWEE999").compact()),
                HttpStatus.OK);
        personalProfile.setTokengiven(token.getBody().getToken());
        return token;
    }


    @RequestMapping(value = "/index",method = RequestMethod.GET)
    ModelAndView
    index(@RequestParam(name="id") String id)
    {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("person", getprofilebyID(id));
        return mav;
    }



    @RequestMapping(value = "/qrtoken",method = RequestMethod.GET)
    ModelAndView
    qrtoken(@RequestParam(name="token") String token)
    {
        if(personalProfileRepository.findByTokengiven(token)!=null) {
            PersonalProfile personalProfile = personalProfileRepository.findByTokengiven(token);
            ModelAndView mav = new ModelAndView("index");
            mav.addObject("person", personalProfile);
            mav.addObject("GPDetails",getgpdetailsbyID(personalProfile.getId()));
            mav.addObject("vitals",getvitalstop(personalProfile.getId()));
            mav.addObject("medications",getmedication(personalProfile.getId()));
            mav.addObject("illnesses",getillnesses(personalProfile.getId()));
            mav.addObject("kin",getkin(personalProfile.getId()));
            mav.addObject("gp",getgpdetailsbyID(personalProfile.getId()));
            mav.addObject("insurance",insurancedetailsget(personalProfile.getId()));
            mav.addObject("bookings",bookingsget(personalProfile.getId()));
            token(personalProfile);
            personalProfileRepository.save(personalProfile);
            return mav;
        }
        else {
            ModelAndView e = new ModelAndView("singletoken");
            return  e;
        }
    }


    public String  getCurrentTimeUsingDate() {
        Date date = new Date();
//        String strDateFormat = "hh:mm:ss a";
        String strDateFormat = "dd-MM-yyyy HH:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate= dateFormat.format(date);
        return formattedDate;
    }












}
