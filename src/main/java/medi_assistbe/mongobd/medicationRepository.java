package medi_assistbe.mongobd;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface  medicationRepository extends MongoRepository<medication,String> {

    public List<medication> findAllByMap(String map);
}
