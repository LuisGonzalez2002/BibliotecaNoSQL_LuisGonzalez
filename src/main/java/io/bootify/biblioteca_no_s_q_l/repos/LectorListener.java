package io.bootify.biblioteca_no_s_q_l.repos;

import io.bootify.biblioteca_no_s_q_l.domain.Lector;
import io.bootify.biblioteca_no_s_q_l.service.PrimarySequenceService;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;


@Component
public class LectorListener extends AbstractMongoEventListener<Lector> {

    private final PrimarySequenceService primarySequenceService;

    public LectorListener(final PrimarySequenceService primarySequenceService) {
        this.primarySequenceService = primarySequenceService;
    }

    @Override
    public void onBeforeConvert(final BeforeConvertEvent<Lector> event) {
        if (event.getSource().getIdBiblioteca() == null) {
            event.getSource().setIdBiblioteca(primarySequenceService.getNextValue());
        }
    }

}
