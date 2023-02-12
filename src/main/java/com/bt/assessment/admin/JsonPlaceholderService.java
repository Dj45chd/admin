package com.bt.assessment.admin;

import com.bt.assessment.admin.entity.Post;
import com.bt.assessment.admin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
public class JsonPlaceholderService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${JsonPlaceholderService.baseUrl}")
	private String baseUrl;

	@Value("${JsonPlaceholderService.usersUrl}")
	private String usersUrl;

	@Value("${JsonPlaceholderService.postsUrl}")
	private String postsUrl;

	@Autowired
	public JsonPlaceholderService() {

	}

	@Async
	public Future<Optional<User>> getUser(int userId) {
		List<User> users = Arrays.stream(restTemplate.getForObject(baseUrl+""+usersUrl,User[].class )).collect(Collectors.toList());
		Optional<User> result = Optional.ofNullable(users.stream().filter(user -> user.getId() == userId).findFirst().orElse(null));
		return new AsyncResult<>(result);
	}

	@Async
	public Future<List<Post>> getPosts(int userId) {
		List<Post> posts = Arrays.stream(restTemplate.getForObject(baseUrl+""+postsUrl,Post[].class )).collect(Collectors.toList());
		List result = posts.stream().filter(post -> post.getUserId() == userId).collect(Collectors.toList());
		return new AsyncResult<>(result);
	}

}
