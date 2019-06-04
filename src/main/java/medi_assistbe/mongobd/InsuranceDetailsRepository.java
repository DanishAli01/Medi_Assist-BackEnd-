package medi_assistbe.mongobd;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface InsuranceDetailsRepository extends MongoRepository<InsuranceDetails,String> {

    public InsuranceDetails getByMap(String id);
}
