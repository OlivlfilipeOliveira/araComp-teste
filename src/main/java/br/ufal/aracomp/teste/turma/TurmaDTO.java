package br.ufal.aracomp.teste.turma;

import java.util.ArrayList;
import java.util.List;

import br.ufal.aracomp.teste.aluno.AlunoDTO;

public class TurmaDTO {

	private Long id;

	private String nome;

	private List<AlunoDTO> alunos = new ArrayList<>();

	public TurmaDTO() {
	}

	public TurmaDTO(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public TurmaDTO(String nome) {
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

	public List<AlunoDTO> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<AlunoDTO> alunos) {
		this.alunos = alunos;
	}
}
