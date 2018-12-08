package br.ufal.aracomp.teste.turma;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufal.aracomp.teste.aluno.Aluno;
import br.ufal.aracomp.teste.aluno.AlunoDTO;
import br.ufal.aracomp.teste.aluno.AlunoService;
import br.ufal.aracomp.teste.util.ResourceNotFoundException;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

	@Autowired
	private TurmaRepository repository;

	@Autowired
	private TurmaService turmaService;

	@Autowired
	private AlunoService alunoService;

	public Class<TurmaDTO> getResourceClass() {
		return TurmaDTO.class;
	}

	@GetMapping
	public List<TurmaDTO> findAll() {
		Iterable<Turma> turmas = repository.findAll();

		List<TurmaDTO> turmasdto = turmaService.turmasToTurmasDTO(turmas, this.getResourceClass());

		return turmasdto;
	}

	@GetMapping("/{id}")
	public TurmaDTO findOne(@PathVariable("id") Long id) throws Exception {
		Turma turma = repository.getOne(id);

		if (turma != null) {
			TurmaDTO turmadto = turmaService.turmaToTurmaDTO(turma, this.getResourceClass());

			return turmadto;
		} else {
			throw new ResourceNotFoundException("resource not found for id " + id);
		}
	}

	@DeleteMapping("/{id}")
	public TurmaDTO delete(@PathVariable("id") Long id) throws Exception {
		Turma turma = repository.getOne(id);
		
		if (turma != null) {
		
			turma.setAlunos(null);
			repository.save(turma);
			repository.deleteById(id);

			TurmaDTO turmadto = turmaService.turmaToTurmaDTO(turma, this.getResourceClass());

			return turmadto;
		} else {
			throw new ResourceNotFoundException("resource not found for id+" + id);
		}
	}

	@PostMapping
	public TurmaDTO create(@RequestBody TurmaDTO turmadto) {

		Turma turma = turmaService.turmaDTOToTurma(turmadto, this.getResourceClass());

		if (turma != null) {
			Turma retorno = repository.save(turma);

			TurmaDTO turmaRetornada = turmaService.turmaToTurmaDTO(retorno, this.getResourceClass());

			return turmaRetornada;
		} else {
			return null;
		}
	}

	@PutMapping
	public TurmaDTO update(@RequestBody TurmaDTO turmadto) {
		Turma turma = turmaService.turmaDTOToTurma(turmadto, this.getResourceClass());

		if (turma != null) {
			turma.setNome(turmadto.getNome());

			List<AlunoDTO> alunosdto = turmadto.getAlunos();
			List<Aluno> alunos = alunoService.alunosDTOToAlunos(alunosdto, this.getResourceClass());
			turma.setAlunos(alunos);

			Turma retorno = repository.save(turma);

			TurmaDTO turmaRetornada = turmaService.turmaToTurmaDTO(retorno, this.getResourceClass());

			return turmaRetornada;
		} else {
			return null;
		}
	}

}
