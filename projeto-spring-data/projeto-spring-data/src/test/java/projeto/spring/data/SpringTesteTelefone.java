package projeto.spring.data;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projeto.spring.data.dao.InterfaceSpringDataUser;
import projeto.spring.data.dao.InterfaceTelefone;
import projeto.spring.data.model.Telefone;
import projeto.spring.data.model.UsuarioSpringData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring-config.xml"})
public class SpringTesteTelefone {

	@Autowired
	private InterfaceTelefone interfaceTelefone; 
	
	@Autowired
	private InterfaceSpringDataUser interfaceSpringUser;
	
	
	@Test
	public void testeInsertTelefone() {
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringUser.findById(2L);
		
		Telefone telefone = new Telefone();
		telefone.setUsuario(usuarioSpringData.get());
		telefone.setTipo("celular");
		telefone.setNumero("91983626139");
		
		interfaceTelefone.save(telefone);
	}
	
	@Test
	public void testeConsultaTelefone() {
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringUser.findById(1L);
		
		System.out.println(usuarioSpringData.get().getId());
		System.out.println(usuarioSpringData.get().getNome());
		System.out.println(usuarioSpringData.get().getIdade());
		System.out.println(usuarioSpringData.get().getEmail());
		System.out.println(usuarioSpringData.get().getLogin());
		System.out.println(usuarioSpringData.get().getSenha());
		System.out.println("--------------");
		for (Telefone telefone : usuarioSpringData.get().getTelefones()) {
			
			System.out.println(telefone.getNumero());
			System.out.println(telefone.getTipo());
			System.out.println(telefone.getId());
			System.out.println(telefone.getUsuario().getNome());
			System.out.println("-----------------------");
		}
		
	}
}
