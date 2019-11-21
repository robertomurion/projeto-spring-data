package projeto.spring.data;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projeto.spring.data.dao.DaoTelefone;
import projeto.spring.data.dao.DaoUsuarioPessoa;
import projeto.spring.data.model.Telefone;
import projeto.spring.data.model.UsuarioPessoa;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:META-INF/spring-config.xml"})
public class AppSpringDataTest {
		@Autowired
		private DaoUsuarioPessoa daoUsuarioPessoa;
		
		@Autowired
		private DaoTelefone daoTelefone;
		
		
		
		@Test
		public void testInsert() {
			UsuarioPessoa usuarioPessoa = new UsuarioPessoa();
			usuarioPessoa.setNome("Ana");
			usuarioPessoa.setEmail("ana.maria@globol.com");
			usuarioPessoa.setLogin("ana");
			usuarioPessoa.setSenha("123");
			
			daoUsuarioPessoa.save(usuarioPessoa);
		}
		
		@Test
		public void testConsulta() {
			Optional<UsuarioPessoa> usuarioPessoa = daoUsuarioPessoa.findById(2L);
			System.out.println(usuarioPessoa.get().getNome());
			
			for (Telefone telefones : usuarioPessoa.get().getTelefones()) {
				System.out.println(telefones.getNumero());
			}
		}
		
		@Test
		public void testConsultaTodos() {
			List<UsuarioPessoa> lista = (List<UsuarioPessoa>) daoUsuarioPessoa.findAll();
			for (UsuarioPessoa usuarioPessoa : lista) {
				System.out.println(usuarioPessoa.getNome());
				for (Telefone telefones : usuarioPessoa.getTelefones()) {
					System.out.println(telefones.getNumero());
				}
				System.out.println("-----------------------------------------");
			}
		}
		
		@Test
		public void testUpdate() {
			Optional<UsuarioPessoa> usuarioPessoa = daoUsuarioPessoa.findById(2L);
			UsuarioPessoa temp = usuarioPessoa.get();
			temp.setNome("Didi");
			daoUsuarioPessoa.save(temp);
		}
		
		@Test
		public void testDelete() {
			daoUsuarioPessoa.deleteById(3L);
		}
		
		@Test
		public void testConsultaNome() {
			List<UsuarioPessoa> list = daoUsuarioPessoa.buscaPorNome("Did");
			
			for (UsuarioPessoa usuarioPessoa : list) {
				System.out.println(usuarioPessoa.getNome());
				System.out.println(usuarioPessoa.getEmail());
				System.out.println(usuarioPessoa.getLogin());
			}
		}
		
		@Test
		public void testConsultaNomeParam() {
			UsuarioPessoa usuarioPessoa = daoUsuarioPessoa.buscarPorNomeParam("Didi");
			
				System.out.println(usuarioPessoa.getNome());
				System.out.println(usuarioPessoa.getEmail());
				System.out.println(usuarioPessoa.getLogin());
		}
		
		@Test
		public void testDeletePorParam() {
			daoUsuarioPessoa.deletarPorNomeParam("Ana");
		}
		
		@Test
		public void testAtualizar() {
			daoUsuarioPessoa.atualizarPorNome("joao", 2L);
		}
		
		@Test
		public void testInsertTelefone() {
			Optional<UsuarioPessoa> usuarioPessoa = daoUsuarioPessoa.findById(2L);
			
			Telefone telefone = new Telefone();
			telefone.setNumero("948338323");
			telefone.setTipo("celular");
			telefone.setUsuarioPessoa(usuarioPessoa.get());
			
			daoTelefone.save(telefone);
		}
		
		@SuppressWarnings("unused")
		@Test
		public void testNew() {
			UsuarioPessoa pessoa = new UsuarioPessoa();
			
		}
}


