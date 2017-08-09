package com.blindbugs.swbot.domain.movies;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Movies {
  static List<String> movies = Arrays.asList(
      "A New Hope",
      "Empire Strikes Back",
      "Return of the Jedi",
      "The Phantom Menace",
      "Attack of the Clones",
      "Revenge of the Sith",
      "The Force Awakens"
  );

  public static String getMovies(List<Integer> films) {
    StringBuilder list = new StringBuilder();
    films.sort(Comparator.naturalOrder());
    for (int i = 0; i < films.size(); i++) {
      list.append(movies.get(films.get(i) - 1));
      if (i < films.size() - 2) {
        list.append(", ");
      } else if (i == films.size() - 2) {
        list.append(" and ");
      }
    }
    return list.toString();
  }
}
