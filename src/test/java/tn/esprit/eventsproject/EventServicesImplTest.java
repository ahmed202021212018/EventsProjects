package tn.esprit.eventsproject;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Logistics;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.repositories.EventRepository;
import tn.esprit.eventsproject.repositories.LogisticsRepository;
import tn.esprit.eventsproject.repositories.ParticipantRepository;
import tn.esprit.eventsproject.services.EventServicesImpl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EventServicesImplTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private LogisticsRepository logisticsRepository;

    @InjectMocks
    private EventServicesImpl eventServices;

    public EventServicesImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addParticipant() {
        Participant participant = new Participant();
        when(participantRepository.save(any(Participant.class))).thenReturn(participant);

        eventServices.addParticipant(participant);

        verify(participantRepository, times(1)).save(participant);
    }

    @Test
    void addAffectEvenParticipant() {
        Event event = new Event();
        Participant participant = new Participant();
        participant.setIdPart(1);
        when(participantRepository.findById(1)).thenReturn(Optional.of(participant));
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        eventServices.addAffectEvenParticipant(event, 1);

        verify(participantRepository, times(1)).findById(1);
        verify(eventRepository, times(1)).save(event);
    }

    @Test
    void addAffectEvenParticipantWithEvent() {
        Event event = new Event();
        Set<Participant> participants = new HashSet<>();
        Participant participant = new Participant();
        participant.setIdPart(1);
        participants.add(participant);
        event.setParticipants(participants);
        when(participantRepository.findById(1)).thenReturn(Optional.of(participant));
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        eventServices.addAffectEvenParticipant(event);

        verify(participantRepository, times(1)).findById(1);
        verify(eventRepository, times(1)).save(event);
    }

    @Test
    void addAffectLog() {
        Logistics logistics = new Logistics();
        Event event = new Event();
        event.setDescription("EventDescription");
        when(eventRepository.findByDescription("EventDescription")).thenReturn(event);
        when(logisticsRepository.save(any(Logistics.class))).thenReturn(logistics);

        eventServices.addAffectLog(logistics, "EventDescription");

        verify(eventRepository, times(1)).findByDescription("EventDescription");
        verify(logisticsRepository, times(1)).save(logistics);
    }

}
