package medi_assistbe.mongobd;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MedicalHistoryRepository extends MongoRepository<MedicalHistory,String> {

    public MedicalHistory findByillness(String illness);

    public List<MedicalHistory> findAllByMap(String map);

}
