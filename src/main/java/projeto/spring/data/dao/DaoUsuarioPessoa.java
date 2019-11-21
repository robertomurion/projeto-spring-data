package projeto.spring.data.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import projeto.spring.data.model.UsuarioPessoa;

@Repository
public interface DaoUsuarioPessoa extends CrudRepository<UsuarioPessoa, Long> {

	@Query(value = "select a from UsuarioPessoa a where a.nome like %?1%")
	public List<UsuarioPessoa> buscaPorNome(String nome);

	@Query(value = "select a from UsuarioPessoa a where a.nome = :paramnome")
	public UsuarioPessoa buscarPorNomeParam(@Param("paramnome") String paramnome);
	
	@Modifying
	@Transactional
	@Query(value = "delete from UsuarioPessoa  a where a.nome = :paramnome")
	public void deletarPorNomeParam(@Param("paramnome") String paramnome);
	
	@Modifying
	@Transactional
	@Query(value = "update UsuarioPessoa as a set a.nome = ?1  where a.id = ?2")
	public void atualizarPorNome(String nome, Long id);
	
}
