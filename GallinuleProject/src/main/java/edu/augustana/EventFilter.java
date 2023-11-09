package edu.augustana;
import java.util.List;
import java.util.stream.Collectors;

public class EventFilter implements CardFilter {

    private final String selectedEvent;

    public EventFilter(String selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    @Override
    public List<Card> filter(List<Card> cards) {
        return filterByEvent(cards);
    }

    private List<Card> filterByEvent(List<Card> cards) {
        return cards.stream()
                .filter(card -> card.getEvent().equalsIgnoreCase(selectedEvent))
                .collect(Collectors.toList());
    }
}
