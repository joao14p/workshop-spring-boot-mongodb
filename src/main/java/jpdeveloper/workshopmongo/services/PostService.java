package jpdeveloper.workshopmongo.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jpdeveloper.workshopmongo.domain.Post;
import jpdeveloper.workshopmongo.exceptions.ObjectNotFoundException;
import jpdeveloper.workshopmongo.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repo;
	
	
	public Post findById(String id) {
		Post user = this.repo.findById(id).orElse(null);
		if(user == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		return user;
	}
	
	public List<Post> findByTitle (String text){
		return repo.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + 24 * 60 *60 *1000);
		return repo.fullSearch(text, minDate, maxDate);
	}
}
