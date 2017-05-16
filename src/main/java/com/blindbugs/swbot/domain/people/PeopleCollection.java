package com.blindbugs.swbot.domain.people;

import java.util.List;

public interface PeopleCollection {
    People getPeople(int id);
    List<People> findPeople(String name);

}
