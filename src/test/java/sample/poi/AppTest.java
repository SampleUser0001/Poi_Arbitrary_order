package sample.poi;

import org.junit.Test;
import org.junit.Before;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class AppTest {
    
    private App app;
    
    @Before
    public void setup() {
        app = new App();
    }
    
    @Test
    public void test() {
        String actual = app.execute();
        assertThat(actual, is(equalTo("expected")));
    }
}