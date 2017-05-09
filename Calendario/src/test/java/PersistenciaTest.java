import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersistenciaTest {
    private CalendarioController lala;

    @Test
    public void lalaSeLevantaBien(){
        assertNotEquals(lala, null);
    }
}
