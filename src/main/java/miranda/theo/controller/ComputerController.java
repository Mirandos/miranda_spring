package miranda.theo.controller;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import miranda.theo.dto.ComputerDto;
import miranda.theo.exception.CreateComputerIdException;
import miranda.theo.service.ComputerService;


@RestController
@RequestMapping("computer")
public class ComputerController {
	
	@Autowired
	private ComputerService computerService;
	
	@GetMapping()
	public Collection<ComputerDto> getAllComputers() {
		return computerService.getAllComputers();
	}
	
	 @GetMapping("/liste")
	    public String listComputers(Model model) {
		 	computerService.getAllComputers();
	        return "liste"; 
	    }
	
	@GetMapping("/{id}")
	public ComputerDto getComputer(@PathVariable("id") long id) {
		return computerService.getComputer(id);
	}
	
	@DeleteMapping("/{id}")
	public boolean deleteComputer(@PathVariable("id") long id) {
		return computerService.deleteComputer(id);
	}
	
	@PostMapping()
	public ComputerDto createComputer(@RequestBody ComputerDto computerDto) throws CreateComputerIdException {
		return computerService.createComputer(computerDto);
	}
	
	@PutMapping()
	public ComputerDto updateComputer(@RequestBody ComputerDto computerDto) {
		return computerService.updateComputer(computerDto);
	}
	
	
	@GetMapping("populate")
	public void populate() throws CreateComputerIdException {
		ComputerDto computerDto = new ComputerDto();
		computerDto.setSerialNumber(36684);
		computerDto.setModel("Asus Tuf");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date date = dateFormat.parse("21-11-2025");
			computerDto.setReleaseDate(date);
		}catch (ParseException e) {
	        e.printStackTrace();
	    }
				
		computerDto.setStatus("available");
		computerService.createComputer(computerDto);	
	}
}
