package pl.sda.spring2_travel360.statistics;

import lombok.Getter;

@Getter
public class StatisticsService {

    private int usersCount;
    private int postsCount;
    private int commentsCount;
    private double avgPostsPerUser;
    private double avgCommentsPerUser;
    private double avgCommentsPerPost;


    public void calculateAdvStatistics(Statistics statistics) {
        this.usersCount = statistics.usersNames().size();
        this.postsCount = statistics.postCount();
        this.commentsCount = statistics.commentsCount();

        if (postsCount == 0) {
            avgCommentsPerPost = 0.0;
        } else {
            avgCommentsPerPost = commentsCount / (double) postsCount;
        }
        if (usersCount == 0) {
            avgPostsPerUser = 0.0;
            avgCommentsPerPost = 0.0;
        } else {
            avgCommentsPerUser = commentsCount / (double) usersCount;
            avgPostsPerUser = postsCount / (double) usersCount;
        }
    }


    public void showStatistics() {
        return;
    }

}
