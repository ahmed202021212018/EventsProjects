package tn.esprit.eventsproject.services;

import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Logistics;
import tn.esprit.eventsproject.entities.Participant;

import java.time.LocalDate;
import java.util.List;

public interface IEventServices {
    Event addEvent(Event event);

    public Participant addParticipant(Participant participant);

    Event addAffectEvenParticipant(Event event);

    Event addAffectEvenParticipant(Event event, int idParticipant);

    public Logistics addAffectLog(Logistics logistics, String descriptionEvent);
    public List<Logistics> getLogisticsDates(LocalDate date_debut, LocalDate date_fin);
    public void calculCout();
}
