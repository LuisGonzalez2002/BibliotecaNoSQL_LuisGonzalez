package io.bootify.biblioteca_no_s_q_l.repos;

import io.bootify.biblioteca_no_s_q_l.domain.Lector;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface LectorRepository extends MongoRepository<Lector, Long> {
}
