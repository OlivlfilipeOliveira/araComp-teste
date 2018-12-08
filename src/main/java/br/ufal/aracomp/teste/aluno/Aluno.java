package br.ufal.aracomp.teste.aluno;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.ufal.aracomp.teste.turma.Turma;

@Entity
public class Aluno {

	@Id
	private String matricula;

	private String nome;

	private String curso;

	@ManyToOne
	@JoinColumn(name = "Aluno_turma", foreignKey = @ForeignKey(name = "Fk_aluno_turma"))
	private Turma turma;

	public Aluno() {
	}

	public Aluno(String matricula, String nome, String curso) {
		this.matricula = matricula;
		this.nome = nome;
		this.curso = curso;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

}
