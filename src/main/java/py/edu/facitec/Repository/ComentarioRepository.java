package py.edu.facitec.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import py.edu.facitec.model.Comentario;
import py.edu.facitec.model.Suscrito;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

}
