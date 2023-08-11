package bagusadinugroho.restful.repository;

import bagusadinugroho.restful.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Books, String> {
}
