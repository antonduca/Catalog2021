package ro.pub.elth.itee.bogdan.repository;

import ro.pub.elth.itee.bogdan.domain.Nota;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Nota entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {

    @Query("select nota from Nota nota where nota.user.login = ?#{principal.username}")
    List<Nota> findByUserIsCurrentUser();

    @Query("select nota from Nota nota where nota.user.login = ?#{principal.username}")
    Page<Nota> findByUserIsCurrentUser(Pageable Pageable);

    // De terminat:
    @Query("select nota from Nota nota where nota.id = :id and nota.user.login = ?#{principal.username}")
    Optional<Nota> findByUserIsCurrentUserById(@Param("id") Long id);
}
