package cat.itacademy.barcelonactiva.fase2.marti_comas.adria.s05.t02.n01.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cat.itacademy.barcelonactiva.fase2.marti_comas.adria.s05.t02.n01.model.domain.Usuari;

@Repository
public interface UsuariRepository extends MongoRepository<Usuari, Integer> {

}
