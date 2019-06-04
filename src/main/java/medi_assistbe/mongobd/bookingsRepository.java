package medi_assistbe.mongobd;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface bookingsRepository extends MongoRepository<bookings,String> {

    public bookings findByMap(String id);
    public void deleteByMap(String id);
}
