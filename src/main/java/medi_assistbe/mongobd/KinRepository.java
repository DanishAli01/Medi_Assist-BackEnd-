package medi_assistbe.mongobd;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface KinRepository extends MongoRepository<Kin,String> {

    public List<Kin> findAllByMap(String map);
}
