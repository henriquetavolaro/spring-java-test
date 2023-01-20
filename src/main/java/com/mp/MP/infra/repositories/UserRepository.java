package com.mp.MP.infra.repositories;
import com.mp.MP.domain.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String id);
}
