package com.bt.assessment.admin;

import com.bt.assessment.admin.entity.Post;
import com.bt.assessment.admin.entity.User;
import com.bt.assessment.admin.exception.UserNotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

import static com.bt.assessment.admin.PostMatcher.isPost;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AdminControllerTest {

	private static final int USER_ID = 1;

	private AdminController controller;

	private JsonPlaceholderService jsonPlaceholderService;

	@Before
	public void before() {
		jsonPlaceholderService = mock(JsonPlaceholderService.class);
		controller = new AdminController(jsonPlaceholderService);
	}

	@Test
	public void testGet() throws Exception {

		User user = createUser(USER_ID, "Dhiraj Sharma", "dhiraj@bt.com");
		Future<Optional<User>> futureUser = new com.bt.assessment.admin.TestFuture<>(Optional.of(user));
		when(jsonPlaceholderService.getUser(USER_ID)).thenReturn(futureUser);

		List<Post> posts = new ArrayList<>();
		posts.add(createPost(USER_ID, 1, "Title 1"));
		posts.add(createPost(USER_ID, 2, "Title 2"));
		posts.add(createPost(USER_ID, 3, "Title 3"));
		Future<List<Post>> futurePosts = new com.bt.assessment.admin.TestFuture<>(posts);
		when(jsonPlaceholderService.getPosts(USER_ID)).thenReturn(futurePosts);

		User result = controller.get(USER_ID);

		assertEquals(result.getId(), is(1));
		assertEquals(result.getName(), is("Dhiraj Sharma"));
		assertEquals(result.getEmail(), is("dhiraj@bt.com"));

		assertEquals(result.getPosts(), hasSize(3));
		assertEquals(result.getPosts().get(0), isPost(USER_ID, 1, "Title 1"));
		assertEquals(result.getPosts().get(1), isPost(USER_ID, 2, "Title 2"));
		assertEquals(result.getPosts().get(2), isPost(USER_ID, 3, "Title 3"));

	}

	@Test
	public void testGetButUserHasNoPosts() throws Exception {

		User user = createUser(USER_ID, "Dhiraj Sharma", "dhiraj@bt.com");
		Future<Optional<User>> futureUser = new com.bt.assessment.admin.TestFuture<>(Optional.of(user));
		when(jsonPlaceholderService.getUser(USER_ID)).thenReturn(futureUser);

		List<Post> posts = Collections.emptyList();
		Future<List<Post>> futurePosts = new com.bt.assessment.admin.TestFuture<>(posts);
		when(jsonPlaceholderService.getPosts(USER_ID)).thenReturn(futurePosts);

		User result = controller.get(USER_ID);

		assertEquals(result.getId(), is(1));
		assertEquals(result.getName(), is("Dhiraj Sharma"));
		assertEquals(result.getEmail(), is("dhiraj@bt.com"));
		assertEquals(result.getPosts(), is(empty()));
	}

	@Test(expected= UserNotFoundException.class)
	public void testGetButUserNotFound() throws Exception {

		Future<Optional<User>> futureUser = new com.bt.assessment.admin.TestFuture<>(Optional.empty());
		when(jsonPlaceholderService.getUser(USER_ID)).thenReturn(futureUser);

		controller.get(USER_ID);
	}

	private User createUser(int userId, String name, String email) {
		User user = new User();
		user.setId(userId);
		user.setName(name);
		user.setEmail(email);
		return user;
	}

	private Post createPost(int userId, int id, String title) {
		Post post = new Post();
		post.setUserId(userId);
		post.setId(id);
		post.setTitle(title);
		return post;
	}

}
