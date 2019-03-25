package medi_assistbe.mongobd;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MedicalHistoryRepository extends MongoRepository<MedicalHistory,String> {

    public MedicalHistory findByillness(String illness);



}
