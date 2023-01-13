package cat.itacademy.barcelonactiva.fase2.marti_comas.adria.s05.t02.n01.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cat.itacademy.barcelonactiva.fase2.marti_comas.adria.s05.t02.n01.model.domain.Partida;

@Repository
public interface PartidaRepository extends MongoRepository<Partida, Integer> {

}
