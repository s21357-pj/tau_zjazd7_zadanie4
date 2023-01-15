import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import java.util.List;
import org.example.FriendsCollection;
import org.example.Person;
import org.junit.jupiter.api.Test;
import org.example.FriendshipsMongo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class FriendshipsMongoMockittoTest {
	@Mock
	static FriendsCollection friends;
	@InjectMocks
	static FriendshipsMongo friendships;

	@Test
	public void mockingWorksAsExpected(){
		Person joe = new Person("Joe");
		when(friends.findByName("Joe")).thenReturn(joe);
		assertThat(friends.findByName("Joe")).isEqualTo(joe);
	}

	@Test
	public void alexDoesNotHaveFriends(){
		assertThat(friendships.getFriendsList("Alex").size()).isZero();
	}

	@Test
	public void joeHas5Friends(){
		Person joe = mock(Person.class);
		when(friends.findByName("Joe")).thenReturn(joe);
		when(joe.getFriends()).thenReturn(List.of("Karol","Dawid","Maciej","Tomek","Adam"));
		assertThat(friendships.getFriendsList("Joe")).hasSize(5).containsOnly("Karol","Dawid","Maciej","Tomek","Adam");
	}

	@Test
	public void alexKateNotFriends(){
		assertThat(friendships.areFriends("Alex", "Kate")).isFalse();
	}
}