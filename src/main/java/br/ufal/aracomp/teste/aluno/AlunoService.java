package br.ufal.aracomp.teste.aluno;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufal.aracomp.teste.turma.Turma;
import br.ufal.aracomp.teste.turma.TurmaDTO;
import br.ufal.aracomp.teste.turma.TurmaService;

@Service
public class AlunoService {

	@Autowired
	private TurmaService service;

	public AlunoDTO alunoToAlunoDTO(Aluno aluno, Class<?> resourceClass) {
		if (aluno != null) {
			AlunoDTO alunodto = new AlunoDTO(aluno.getMatricula(), aluno.getNome(),
					aluno.getCurso());

			if (AlunoDTO.class.equals(resourceClass)) {
				Turma turma = aluno.getTurma();
				TurmaDTO turmadto = service.turmaToTurmaDTO(turma, resourceClass);
				alunodto.setTurma(turmadto);
			}

			return alunodto;
		} else {
			return null;
		}
	}

	public List<AlunoDTO> alunosToAlunosDTO(Iterable<Aluno> alunos, Class<?> resourceClass) {
		List<AlunoDTO> alunosdto = new ArrayList<>();

		if (alunos != null) {
			for (Aluno aluno : alunos) {
				alunosdto.add(this.alunoToAlunoDTO(aluno, resourceClass));
			}
		}

		return alunosdto;
	}

	public Aluno alunoDTOToAluno(AlunoDTO alunodto, Class<?> resourceClass) {
		if (alunodto != null) {
			Aluno aluno = new Aluno(alunodto.getMatricula(), alunodto.getNome(), alunodto.getCurso());

			if (AlunoDTO.class.equals(resourceClass)) {
				TurmaDTO turmadto = alunodto.getTurma();
				Turma turma = service.turmaDTOToTurma(turmadto, resourceClass);
				aluno.setTurma(turma);
			}

			return aluno;
		} else {
			return null;
		}
	}

	public List<Aluno> alunosDTOToAlunos(List<AlunoDTO> alunosdto, Class<?> resourceClass) {
		List<Aluno> alunos = new ArrayList<>();

		if (alunosdto != null) {
			for (AlunoDTO alunodto : alunosdto) {
				alunos.add(this.alunoDTOToAluno(alunodto, resourceClass));
			}
		}

		return alunos;
	}
}
