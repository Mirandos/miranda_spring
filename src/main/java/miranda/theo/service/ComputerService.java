package miranda.theo.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import miranda.theo.repository.ComputerRepository;
import miranda.theo.dto.ComputerDto;
import miranda.theo.exception.CreateComputerIdException;
import miranda.theo.model.ComputerModel;

@Service
public class ComputerService {

	@Autowired
	private ComputerRepository computerRepository;
	
	public Collection<ComputerDto> getAllComputers() {
		return modelsToDtos(computerRepository.findAll());
	}
	
	public ComputerDto createComputer(ComputerDto dto) throws CreateComputerIdException {
		if(dto.getId()!=null) {
			throw new CreateComputerIdException("Il est interdit de mettre un Id afin de créer un utilisateur");
		}
		return modelToDto(computerRepository.save(dtoToModel(dto)));
	}
		
	public ComputerDto updateComputer(ComputerDto dto) {
		return modelToDto(computerRepository.save(dtoToModel(dto)));
	}

	public ComputerDto getComputer(long id) {
		return modelToDto(computerRepository.findById(id).get());
	}

	public boolean deleteComputer(long id) {
		computerRepository.deleteById(id);
		return true;
	}


	private Collection<ComputerDto> modelsToDtos(Iterable<ComputerModel> computerModels) {
		Collection<ComputerDto> computerDtos = new ArrayList<>();
		computerModels.forEach(computerModel -> {
			computerDtos.add(modelToDto(computerModel));
		});
		return computerDtos;
	}
	
	private ComputerDto modelToDto(ComputerModel computerModel) {
		ComputerDto computerDto = new ComputerDto();
		computerDto.setId(computerModel.getId());
		computerDto.setSerialNumber(computerModel.getSerialNumber());
		computerDto.setModel(computerModel.getModel());
		computerDto.setReleaseDate(computerModel.getReleaseDate());
		computerDto.setStatus(computerModel.getStatus());
		return computerDto;
	}

	private ComputerModel dtoToModel(ComputerDto computerDto) {
		ComputerModel computerModel = new ComputerModel();
		computerModel.setId(computerDto.getId());
		computerModel.setSerialNumber(computerDto.getSerialNumber());
		computerModel.setModel(computerDto.getModel());
		computerModel.setReleaseDate(computerDto.getReleaseDate());
		computerModel.setStatus(computerDto.getStatus());
		return computerModel;
	}
}
