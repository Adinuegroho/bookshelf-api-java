package bagusadinugroho.restful.repository;

import bagusadinugroho.restful.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Books, String> {

    Optional<Books> findBooksById(String id);
}
