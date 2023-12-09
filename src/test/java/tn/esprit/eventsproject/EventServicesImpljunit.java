package tn.esprit.eventsproject;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Logistics;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.entities.Tache;
import tn.esprit.eventsproject.repositories.EventRepository;
import tn.esprit.eventsproject.repositories.LogisticsRepository;
import tn.esprit.eventsproject.repositories.ParticipantRepository;
import tn.esprit.eventsproject.services.EventServicesImpl;




import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback(false)
@Transactional

class EventServicesImpljunit {
    @Autowired
    private EventServicesImpl eventServices;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private LogisticsRepository logisticsRepository;

    @Test
    void testAddParticipant() {
        Participant participant = new Participant();
        participant.setNom("John");
        participant.setPrenom("Doe");
        participant.setTache(Tache.INVITE);

        Participant addedParticipant = eventServices.addParticipant(participant);

        assertNotNull(addedParticipant.getIdPart());
    }

    @Test
    void testAddAffectEvenParticipant() {
        Event event = new Event();
        event.setDescription("Test Event");

        Event addedEvent = eventRepository.save(event);

        Participant participant = new Participant();
        participant.setNom("Jane");
        participant.setPrenom("Doe");
        participant.setTache(Tache.INVITE);

        Participant addedParticipant = participantRepository.save(participant);

        Event affectedEvent = eventServices.addAffectEvenParticipant(addedEvent, addedParticipant.getIdPart());

        assertNotNull(affectedEvent.getParticipants());
        assertEquals(1, affectedEvent.getParticipants().size());
    }

}
