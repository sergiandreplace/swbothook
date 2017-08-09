package com.blindbugs.swbot.domain.people;

import java.util.List;
import javax.inject.Inject;

public class FindPlanetService {

    private final PeopleCollection peopleCollection;

    @Inject
    public FindPlanetService(PeopleCollection peopleCollection) {
        this.peopleCollection = peopleCollection;
    }

    public List<People> execute(String name) {
        return peopleCollection.findPeople(name);
    }
}
