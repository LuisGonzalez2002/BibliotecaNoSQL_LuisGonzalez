package io.bootify.biblioteca_no_s_q_l.repos;

import io.bootify.biblioteca_no_s_q_l.domain.Prestamo;
import io.bootify.biblioteca_no_s_q_l.service.PrimarySequenceService;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;


@Component
public class PrestamoListener extends AbstractMongoEventListener<Prestamo> {

    private final PrimarySequenceService primarySequenceService;

    public PrestamoListener(final PrimarySequenceService primarySequenceService) {
        this.primarySequenceService = primarySequenceService;
    }

    @Override
    public void onBeforeConvert(final BeforeConvertEvent<Prestamo> event) {
        if (event.getSource().getIdBiblioteca() == null) {
            event.getSource().setIdBiblioteca(primarySequenceService.getNextValue());
        }
    }

}
