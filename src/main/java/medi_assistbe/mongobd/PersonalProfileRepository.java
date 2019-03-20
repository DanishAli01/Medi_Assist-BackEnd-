package medi_assistbe.mongobd;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonalProfileRepository extends MongoRepository<PersonalProfile,String> {

    public PersonalProfile findBymobile(String mobile);

    public PersonalProfile findByDateofbirth(String dateofbirth);
}

