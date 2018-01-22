package br.com.meuprojeto.crochet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.meuprojeto.crochet.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

    Usuario findByEmail(String email);
}
