package medi_assistbe.mongobd;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.util.JSON;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class JSONManipulation {

    private List<PersonalProfile> personalProfiles;

    public JSONManipulation(List<PersonalProfile> personalProfiles) {
        this.personalProfiles = personalProfiles;
    }

    public Object make() throws JsonProcessingException {



        ObjectMapper objectMapper = new ObjectMapper();
        String jsonObject = objectMapper.writeValueAsString(personalProfiles);

        return JSON.parse(jsonObject);


    }
}
