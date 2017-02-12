package fr.xebia.javaslang.pattern_matching;


import fr.xebia.film.Film;
import fr.xebia.film.Participant;
import fr.xebia.film.Role;
import javaslang.Tuple;
import javaslang.Tuple2;
import javaslang.collection.Stream;
import javaslang.control.Option;

import static fr.xebia.film.Role.ACTOR;
import static fr.xebia.film.Role.DIRECTOR;
import static fr.xebia.film.Role.WRITER;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;
import static javaslang.API.$;
import static javaslang.API.Case;
import static javaslang.API.Match;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class PatternMatching {

    private final float ACTOR_SALARY = 400f;
    private final float DIRECTOR_SALARY = 300f;
    private final float WRITER_SALARY = 320f;


    public Map<Role, Double> calculateMovieCostByRole(Film movie) {
        return Option.of(movie)
                .map(Film::getPeople)
                .toStream()
                .flatMap(Stream::ofAll)
                .map(computeParticipantSalary())
                .groupBy(Tuple2::_1)
                .map((role, salary) -> Tuple.of(role, salary.map(Tuple2::_2).sum().doubleValue()))
                .toJavaMap();
    }

    private Function<Participant, Tuple2<Role, Float>> computeParticipantSalary() {
        return p ->
                Match(p.getRole())
                        .of(Case($(ACTOR), Tuple.of(ACTOR, ACTOR_SALARY + p.getBonus())),
                                Case($(DIRECTOR), Tuple.of(DIRECTOR, DIRECTOR_SALARY + p.getBonus())),
                                Case($(WRITER), Tuple.of(WRITER, WRITER_SALARY + p.getBonus())));
    }

    public Map<Role, Double> calculateMovieCostByRoleJava8(Film movie) {
        return Optional.ofNullable(movie)
                .map(Film::getPeople)
                .orElse(Collections.emptyList())
                .stream()
                .collect(groupingBy(Participant::getRole, summingDouble(p -> {
                    switch (p.getRole()) {
                        case ACTOR:
                            return ACTOR_SALARY + p.getBonus();
                        case DIRECTOR:
                            return DIRECTOR_SALARY + p.getBonus();
                        case WRITER:
                            return WRITER_SALARY + p.getBonus();
                        default:
                            return 0f;
                    }
                })));
    }


}
