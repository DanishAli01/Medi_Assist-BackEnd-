package medi_assistbe.mongobd;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client,String> {


   // public Client findByName(String clientName);

    //
}
