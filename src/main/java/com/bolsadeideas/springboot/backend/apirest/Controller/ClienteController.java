package com.bolsadeideas.springboot.backend.apirest.Controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bolsadeideas.springboot.backend.apirest.dto.ClienteDto;
import com.bolsadeideas.springboot.backend.apirest.entity.Cliente;
import com.bolsadeideas.springboot.backend.apirest.entity.Region;
import com.bolsadeideas.springboot.backend.apirest.services.IClienteService;
import com.bolsadeideas.springboot.backend.apirest.services.IRegionService;
import com.bolsadeideas.springboot.backend.apirest.services.IUploadFileService;
import com.bolsadeideas.springboot.backend.apirest.util.UtilApi;

@CrossOrigin(origins = { "http://localhost:4200","*" })
@RestController
@RequestMapping(path = "/clienteApi")
public class ClienteController {
	private final Logger log = LoggerFactory.getLogger(ClienteController.class);
	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IRegionService regionService;

	@Autowired
	private IUploadFileService uploadServices;

	@GetMapping(path = "/findAll")
	public ResponseEntity<List<ClienteDto>> findAll() {
		try {
			List<ClienteDto> clientes = clienteService.findAll();
			if (clientes.isEmpty()) {
				return new ResponseEntity<List<ClienteDto>>(clientes, HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<List<ClienteDto>>(clientes, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@GetMapping(path = "/findAll/page/{page}")
	public ResponseEntity<Page<Cliente>> findAll(@PathVariable("page") Integer page) {
		Page<Cliente> clientes = clienteService.findAll(PageRequest.of(page, 4));
		if (clientes.isEmpty()) {
			return new ResponseEntity<Page<Cliente>>(clientes, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Page<Cliente>>(clientes, HttpStatus.OK);
		}

	}

	@GetMapping(path = "/findById/{id}")
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Map<String, Object> response = new HashMap<>();
		ClienteDto cliente = null;
		try {
			cliente = clienteService.findClienteById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al ejecutar la consulta en la base de datos.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (cliente != null) {
			return new ResponseEntity<ClienteDto>(cliente, HttpStatus.OK);
		} else {
			response.put("mensaje", "El Cliente ID: ".concat(id.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(path = "/save")
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity<?> save(@Valid @RequestBody ClienteDto clienteDto, BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		Cliente cliente = new Cliente();
		if (result.hasErrors()) {
			response.put("mensaje", "Error al guardar.");
			List<String> errors = result.getFieldErrors().stream().map(err -> {
				return err.getDefaultMessage();
			}).collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		cliente.setApellidos(clienteDto.apellidos);
		cliente.setNombre(clienteDto.nombre);
		cliente.setEmail(clienteDto.email);
		cliente.setCrateAt(clienteDto.crateAt);
		Region region = regionService.findById(clienteDto.idRegion);
		cliente.setRegion(region);
		Cliente newCliente = new Cliente();
		try {
			newCliente = clienteService.save(cliente);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al guardar en la base de datos.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El cliente fue guardado con exito.");
		response.put("cliente", newCliente);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

	@PutMapping(path = "/update")
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity<?> update(@Validated @RequestBody ClienteDto clienteDto, BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream().map(err -> {
				return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
			}).collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		Cliente clienteActual = clienteService.findById(clienteDto.id);
		if (clienteActual == null) {
			response.put("mensaje", "El Cliente ID: ".concat(clienteDto.id.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		clienteActual.setApellidos(clienteDto.apellidos);
		clienteActual.setNombre(clienteDto.nombre);
		clienteActual.setEmail(clienteDto.email);
		System.out.println("------------------"+ clienteDto.crateAt);
		clienteActual.setCrateAt(clienteDto.crateAt);
		Region region = regionService.findById(clienteDto.idRegion);
		clienteActual.setRegion(region);
		Cliente clienteNuevo = null;
		try {
			clienteNuevo = clienteService.save(clienteActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Los datos fueron actualizados correctamente");
		response.put("Cliente", clienteNuevo);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@DeleteMapping(path = "/delete/{id}")
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		Map<String, Object> response = new HashMap<>();
		Cliente cliente = this.clienteService.findById(id);
		String nombreFotoAnterior = cliente.getFoto();

		try {
			uploadServices.eliminar(nombreFotoAnterior);
			clienteService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar registro en la base de datos.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El cliente eliminado con exito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PostMapping("/clientes/upload")
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
		Map<String, Object> response = new HashMap<>();
		Cliente cliente = this.clienteService.findById(id);
		if (!archivo.isEmpty()) {
			String nombreArchivo = null;
			try {
				nombreArchivo = uploadServices.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagne del cliente");
				response.put("mensaje", e.getMessage().concat(":").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			String nombreFotoAnterior = cliente.getFoto();
			uploadServices.eliminar(nombreFotoAnterior);
			cliente.setFoto(nombreArchivo);
			this.clienteService.save(cliente);
			response.put("cliente", cliente);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@GetMapping("/uploads/img/{nombreFoto:.+}")	
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {
		Resource recurso = null;
		try {
			recurso = uploadServices.cargar(nombreFoto);
		} catch (MalformedURLException e) {

			e.printStackTrace();
		}

		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);

	}

	@GetMapping("/regiones")
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	public ResponseEntity<List<Region>> findAllRegiones() {
		List<Region> regiones = regionService.findAllRegiones();
		if (regiones.isEmpty()) {
			return new ResponseEntity<List<Region>>(regiones, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Region>>(regiones, HttpStatus.OK);
		}
	}

}
