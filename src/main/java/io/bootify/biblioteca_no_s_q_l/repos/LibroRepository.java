package io.bootify.biblioteca_no_s_q_l.repos;

import io.bootify.biblioteca_no_s_q_l.domain.Libro;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface LibroRepository extends MongoRepository<Libro, Long> {
}
