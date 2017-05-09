import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;


@Transactional
interface RepositorioCalendarios extends CrudRepository<CalendarioDeFeriados, Long> {
}
