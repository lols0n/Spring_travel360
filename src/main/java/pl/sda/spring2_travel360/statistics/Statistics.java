package pl.sda.spring2_travel360.statistics;

import java.util.List;

public interface Statistics {

    List<String> usersNames();
    int postCount();
    int commentsCount();
}
