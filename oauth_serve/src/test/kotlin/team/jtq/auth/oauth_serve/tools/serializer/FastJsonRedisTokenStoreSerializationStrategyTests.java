package team.jtq.auth.oauth_serve.tools.serializer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FastJsonRedisTokenStoreSerializationStrategyTests {
    @Test
    public void testCreateInstance() {
        new FastJsonRedisTokenStoreSerializationStrategy();
    }
}
