package medi_assistbe.mongobd;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VitalsRepository extends MongoRepository<Vitals,String> {

public Vitals findByMap(String map);

public List<Vitals> findAllByMap(String map);



}