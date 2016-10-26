package br.com.sematec.livraria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sematec.livraria.dao.AutorDAO;
import br.com.sematec.livraria.dao.LivroDAO;
import br.com.sematec.livraria.modelo.Autor;
import br.com.sematec.livraria.modelo.Livro;

@ManagedBean
@ViewScoped
public class LivroBean {
	private Livro livro = new Livro();
	private Long autorId;

	public void adicionarAutor() {
		Autor autor = AutorDAO.getInstance().buscaPorId(this.autorId);
		System.out.println("Usando autor " + autor.getNome());
		this.livro.adicionaAutor(autor);
	}

	public List<Autor> getAutores() {
		return AutorDAO.getInstance().listaTodos();
	}

	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}

	public Long getAutorId() {
		return autorId;
	}

	public Livro getLivro() {
		return livro;
	}

	public List<Livro> getLivros() {
		return LivroDAO.getInstance().listaTodos();
	}

	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());
		if (livro.getAutores().isEmpty()) {
			throw new RuntimeException("Livro deve ter pelo menos um Autor.");
		}
		LivroDAO.getInstance().adiciona(this.livro);
	}

	public void setAutorId(Long autorId) {
		this.autorId = autorId;
	}
}
