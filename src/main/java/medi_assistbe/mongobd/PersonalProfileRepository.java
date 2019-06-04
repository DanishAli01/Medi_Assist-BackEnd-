package medi_assistbe.mongobd;

import org.hibernate.validator.constraints.EAN;
import org.json.JSONArray;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PersonalProfileRepository extends MongoRepository<PersonalProfile,String> {

    public PersonalProfile findBymobile(String mobile);

    public PersonalProfile findByDateofbirth(String dateofbirth);

    public PersonalProfile findByEmail(String email);

    public PersonalProfile findByName(String Name);

    public List<PersonalProfile> findAllByEmail(String email);

    public boolean existsByEmail(String email);

    public PersonalProfile findByTokengiven(String token);


}

