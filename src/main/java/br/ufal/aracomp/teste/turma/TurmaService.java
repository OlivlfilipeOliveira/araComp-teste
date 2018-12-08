package br.ufal.aracomp.teste.turma;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufal.aracomp.teste.aluno.Aluno;
import br.ufal.aracomp.teste.aluno.AlunoDTO;
import br.ufal.aracomp.teste.aluno.AlunoService;

@Service
public class TurmaService {

	@Autowired
	private AlunoService service;

	public TurmaDTO turmaToTurmaDTO(Turma turma, Class<?> resourceClass) {
		if (turma != null) {
			TurmaDTO turmadto = new TurmaDTO(turma.getId(), turma.getNome());

			if (TurmaDTO.class.equals(resourceClass)) {
				List<Aluno> alunos = turma.getAlunos();
				List<AlunoDTO> alunosdto = service.alunosToAlunosDTO(alunos, resourceClass);
				turmadto.setAlunos(alunosdto);
			}

			return turmadto;
		} else {
			return null;
		}
	}

	public List<TurmaDTO> turmasToTurmasDTO(Iterable<Turma> turmas, Class<?> resourceClass) {
		List<TurmaDTO> turmasdto = new ArrayList<>();

		if (turmas != null) {
			for (Turma turma : turmas) {
				turmasdto.add(this.turmaToTurmaDTO(turma, resourceClass));
			}
		}

		return turmasdto;
	}

	public Turma turmaDTOToTurma(TurmaDTO turmadto, Class<?> resourceClass) {
		if (turmadto != null) {
			Turma turma = new Turma(turmadto.getId(), turmadto.getNome());

			if (TurmaDTO.class.equals(resourceClass)) {
				List<AlunoDTO> alunosdto = turmadto.getAlunos();
				List<Aluno> alunos = service.alunosDTOToAlunos(alunosdto, resourceClass);
				turma.setAlunos(alunos);
			}

			return turma;
		} else {
			return null;
		}
	}

}
