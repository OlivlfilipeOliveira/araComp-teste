package br.ufal.aracomp.teste.turma;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.ufal.aracomp.teste.aluno.Aluno;

@Entity
public class Turma {

	@Id
	@GeneratedValue
	private Long id;

	private String nome;

	@OneToMany(mappedBy = "turma")
	private List<Aluno> alunos = new ArrayList<>();

	public Turma() {
	}

	public Turma(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Turma(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

}
