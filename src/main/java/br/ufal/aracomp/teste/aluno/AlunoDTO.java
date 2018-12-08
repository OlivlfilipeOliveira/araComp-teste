package br.ufal.aracomp.teste.aluno;

import br.ufal.aracomp.teste.turma.TurmaDTO;

public class AlunoDTO {

	private String matricula;

	private String nome;

	private String curso;

	private TurmaDTO turma;

	public AlunoDTO() {
	}

	public AlunoDTO(String matricula, String nome, String curso) {
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

	public TurmaDTO getTurma() {
		return turma;
	}

	public void setTurma(TurmaDTO turmadto) {
		this.turma = turmadto;
	}

}
