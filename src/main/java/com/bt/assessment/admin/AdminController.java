package com.bt.assessment.admin;

import com.bt.assessment.admin.entity.Post;
import com.bt.assessment.admin.entity.User;
import com.bt.assessment.admin.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class AdminController {

	@Autowired
	private final JsonPlaceholderService jsonPlaceholderService;

	@Autowired
	public AdminController(JsonPlaceholderService jsonPlaceholderService) {
		this.jsonPlaceholderService = jsonPlaceholderService;
	}

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
	public User get(@PathVariable("userId") int userId)
			throws InterruptedException, ExecutionException, UserNotFoundException {

		Future<Optional<User>> futureUser = jsonPlaceholderService.getUser(userId);
		Future<List<Post>> futurePosts = jsonPlaceholderService.getPosts(userId);
		/**
		 * checks the user returned ,
		 * if it is not present UserNotFoundException is thrown
		 */
		Optional<User> optionalUser = futureUser.get();
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException(String.format("User with ID %s not found", userId));
		}

		List<Post> posts = futurePosts.get();

		User user = optionalUser.get();
		user.setPosts(posts);
		return user;
	}

}
