import org.easymock.EasyMock;
import org.example.Car;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CarEasyTest {
    private Car myFerrari = EasyMock.createMock(Car.class);

    @Test
    public void test_instance_car(){
        assertTrue(myFerrari instanceof Car);
    }

    @Test
    public void test_default_behavior_needsFuel(){
        assertFalse(myFerrari.needsFuel(), "New test double should return False as boolean");
    }

    @Test
    public void test_default_behavior_temperature(){
        assertEquals(0.0, myFerrari.getEngineTemperature(), "New test double should return 0.0");
    }

    @Test
    public void test_stubbing_mock(){
        EasyMock.expect(myFerrari.needsFuel()).andReturn(true);
        EasyMock.replay(myFerrari);
        assertTrue(myFerrari.needsFuel());
    }

    @Test
    public void test_non_zero_temperature1(){
        EasyMock.expect(myFerrari.getEngineTemperature()).andReturn(55.0);
        EasyMock.replay(myFerrari);
        assertEquals(55.0, myFerrari.getEngineTemperature());
    }

    @Test
    public void test_non_zero_temperature2(){
        EasyMock.expect(myFerrari.getEngineTemperature()).andReturn(5.0);
        EasyMock.replay(myFerrari);
        assertEquals(5.0, myFerrari.getEngineTemperature());
    }

    @Test
    public void test_non_zero_temperature3(){
        EasyMock.expect(myFerrari.getEngineTemperature()).andReturn(3.0);
        EasyMock.replay(myFerrari);
        assertEquals(3.0, myFerrari.getEngineTemperature());
    }

    @Test
    public void test_negate_temperature(){
        EasyMock.expect(myFerrari.getEngineTemperature()).andThrow(new RuntimeException());
        EasyMock.replay(myFerrari);
        assertThrows(RuntimeException.class, () -> {
            myFerrari.getEngineTemperature();
        });
    }

    @Test
    public void test_drive_to_exception() {
        myFerrari.driveTo("gdansk");
        EasyMock.expectLastCall().andThrow(new RuntimeException());
        EasyMock.replay(myFerrari);
        assertThrows(RuntimeException.class, () -> {
            myFerrari.driveTo("gdansk");
        });
    }


    @Test
    public void test_exception(){
        EasyMock.expect(myFerrari.needsFuel()).andThrow(new RuntimeException());
        EasyMock.replay(myFerrari);
        assertThrows(RuntimeException.class, () -> {
            myFerrari.needsFuel();
        });
    }
}