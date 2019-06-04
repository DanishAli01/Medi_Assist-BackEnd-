package medi_assistbe.mongobd;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface GPDetailsRepository extends MongoRepository<GPDetails,String> {

  public GPDetails findByMap(String map);
}
