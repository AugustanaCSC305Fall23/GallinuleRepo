package edu.augustana;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of CardFilter for filtering cards based on a specific event.
 */
public class EventFilter implements CardFilter {

    private final String selectedEvent;

    /**
     * Constructs an EventFilter with the specified event.
     *
     * @param selectedEvent The event to filter by.
     */
    public EventFilter(String selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    /**
     * Filters the list of cards based on the selected event.
     *
     * @param cards The list of cards to be filtered.
     * @return The filtered list of cards.
     */
    @Override
    public List<Card> filter(List<Card> cards) {
        return filterByEvent(cards);
    }

    private List<Card> filterByEvent(List<Card> cards) {
        if (selectedEvent != null && !selectedEvent.isEmpty() && !(selectedEvent.contains("ALL"))) {
            return cards.stream()
                    .filter(card -> card.getEvent().equalsIgnoreCase(selectedEvent))
                    .collect(Collectors.toList());
        } else {
            return cards;
        }
    }
}
