package personal.post.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import personal.post.domain.Post;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {
    Optional<Post> findByTitle(String title);
    Optional<Post> findByContent(String content);

}
