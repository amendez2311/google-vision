package com.upc.ia.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.google.cloud.vision.v1.AnnotateImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.upc.ia.entity.CarPlate;
import com.upc.ia.repository.CarPlateRepository;
import com.upc.ia.service.GoogleVisionService;

import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/carplates/")
public class CarPlateController {

	@Autowired
	private CarPlateRepository carPlateRepository;
	
	@Autowired
	private GoogleVisionService googleVisionService;

	@GetMapping("showForm")
	public String showForm(Model model) {
		model.addAttribute("carPlate", new CarPlate());
		return "add-carplate";
	}

	@GetMapping("list")
	public String carplates(Model model) {
		model.addAttribute("carplates", this.carPlateRepository.findAll());
		return "index";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(@RequestParam MultipartFile file, CarPlate carPlate) {
		System.out.println("Credenciales---------");
		System.out.println(System.getenv("GOOGLE_APPLICATION_CREDENTIALS"));

		try {
			String filename = file.getOriginalFilename();
			System.out.println("filename: " + filename);

			System.out.println("file.getName(): " + file.getName());
			System.out.println("carPlate.toString(): " + carPlate.toString());
			UUID uuid = UUID.randomUUID();
			String key=uuid.toString();
			googleVisionService.processImage(file,key);

			String encodedString = Base64.getEncoder().encodeToString(file.getBytes());
			CarPlate carPlateNew = new CarPlate(carPlate.getDescription(), filename,carPlate.getBrand(),key,encodedString,""+file.getSize());
			this.carPlateRepository.save(carPlateNew);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:list";
	}

	@PostMapping("add2")
	public String add2(@Valid CarPlate carplate, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-carplate";
		}

		this.carPlateRepository.save(carplate);
		return "redirect:list";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") long id, Model model) {

		CarPlate carplate = this.carPlateRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid carplate id : " + id));

		this.carPlateRepository.delete(carplate);
		model.addAttribute("carplates", this.carPlateRepository.findAll());
		return "index";

	}
}
