package io.bootify.biblioteca_no_s_q_l.repos;

import io.bootify.biblioteca_no_s_q_l.domain.Prestamo;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PrestamoRepository extends MongoRepository<Prestamo, Long> {
}
