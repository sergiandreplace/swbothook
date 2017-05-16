package com.blindbugs.swbot.domain.people;

import javax.inject.Inject;
import java.util.List;

public class FindPeopleService {

    private final PeopleCollection peopleCollection;

    @Inject
    public FindPeopleService(PeopleCollection peopleCollection) {
        this.peopleCollection = peopleCollection;
    }

    public List<People> execute(String name) {
        return peopleCollection.findPeople(name);
    }
}
