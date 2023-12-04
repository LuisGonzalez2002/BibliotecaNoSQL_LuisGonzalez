package io.bootify.biblioteca_no_s_q_l.repos;

import io.bootify.biblioteca_no_s_q_l.domain.Bibliotecario;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface BibliotecarioRepository extends MongoRepository<Bibliotecario, Long> {
}
