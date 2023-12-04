package io.bootify.biblioteca_no_s_q_l.repos;

import io.bootify.biblioteca_no_s_q_l.domain.Libro;
import io.bootify.biblioteca_no_s_q_l.service.PrimarySequenceService;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;


@Component
public class LibroListener extends AbstractMongoEventListener<Libro> {

    private final PrimarySequenceService primarySequenceService;

    public LibroListener(final PrimarySequenceService primarySequenceService) {
        this.primarySequenceService = primarySequenceService;
    }

    @Override
    public void onBeforeConvert(final BeforeConvertEvent<Libro> event) {
        if (event.getSource().getIdBiblioteca() == null) {
            event.getSource().setIdBiblioteca(primarySequenceService.getNextValue());
        }
    }

}
