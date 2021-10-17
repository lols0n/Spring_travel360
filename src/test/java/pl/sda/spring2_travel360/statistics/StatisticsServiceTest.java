package pl.sda.spring2_travel360.statistics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sda.spring2_travel360.Display;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatisticsServiceTest {

    @Mock
    private Statistics statistics;

    @Test
    void testWhenPostCountEqualsZero() {
        //given
        StatisticsService service = new StatisticsService();
        when(statistics.postCount()).thenReturn(0);
        when(statistics.commentsCount()).thenReturn(100);
        when(statistics.usersNames()).thenReturn(generateUsers(100));
        //when
        service.calculateAdvStatistics(statistics);
        //then
        assertEquals(0, service.getPostsCount());
        assertEquals(100, service.getCommentsCount());
        assertEquals(100, service.getUsersCount());
        assertEquals(1.0, service.getAvgCommentsPerUser());
        assertEquals(0.0, service.getAvgPostsPerUser());
        assertEquals(0.0, service.getAvgCommentsPerPost());
    }

    @Test
    void testWhenPostCountEqualsOneThousand() {
        //given
        StatisticsService service = new StatisticsService();
        when(statistics.postCount()).thenReturn(1000);
        when(statistics.commentsCount()).thenReturn(100);
        when(statistics.usersNames()).thenReturn(generateUsers(500));
        //when
        service.calculateAdvStatistics(statistics);
        //then
        assertEquals(1000, service.getPostsCount());
        assertEquals(100, service.getCommentsCount());
        assertEquals(500, service.getUsersCount());
        assertEquals(0.2, service.getAvgCommentsPerUser());
        assertEquals(2.0, service.getAvgPostsPerUser());
        assertEquals(0.1, service.getAvgCommentsPerPost());
    }

    @Test
    void testWhenCommentsCountEqualsZero() {
        //given
        StatisticsService service = new StatisticsService();
        when(statistics.postCount()).thenReturn(10);
        when(statistics.commentsCount()).thenReturn(0);
        when(statistics.usersNames()).thenReturn(generateUsers(20));
        //when
        service.calculateAdvStatistics(statistics);
        //then
        assertEquals(10, service.getPostsCount());
        assertEquals(0, service.getCommentsCount());
        assertEquals(20, service.getUsersCount());
        assertEquals(0.0, service.getAvgCommentsPerUser());
        assertEquals(0.5, service.getAvgPostsPerUser());
        assertEquals(0.0, service.getAvgCommentsPerPost());
    }

    @Test
    void testWhenCommentsCountIsHigherThanPostsCount() {
        //given
        StatisticsService service = new StatisticsService();
        when(statistics.postCount()).thenReturn(10);
        when(statistics.commentsCount()).thenReturn(20);
        when(statistics.usersNames()).thenReturn(generateUsers(20));
        //when
        service.calculateAdvStatistics(statistics);
        //then
        assertEquals(10, service.getPostsCount());
        assertEquals(20, service.getCommentsCount());
        assertEquals(20, service.getUsersCount());
        assertEquals(1.0, service.getAvgCommentsPerUser());
        assertEquals(0.5, service.getAvgPostsPerUser());
        assertEquals(2.0, service.getAvgCommentsPerPost());
    }



    private List<String> generateUsers(int count){
        List<String> users = new ArrayList<>();
        for(int i=0; i < count; i++){
            users.add("user" + i);
        }
        return users;
    }

}