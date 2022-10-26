package py.edu.facitec.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import py.edu.facitec.model.Post;
import py.edu.facitec.model.Suscrito;

public interface PostRepository extends JpaRepository<Post, Long> {

}
