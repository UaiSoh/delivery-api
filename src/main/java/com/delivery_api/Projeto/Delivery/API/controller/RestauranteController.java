package com.delivery_api.Projeto.Delivery.API.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delivery_api.Projeto.Delivery.API.dto.RestauranteDTO;
import com.delivery_api.Projeto.Delivery.API.entity.Restaurante;
import com.delivery_api.Projeto.Delivery.API.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes")
@CrossOrigin(origins = "*")
public class RestauranteController {
    
    @Autowired
    private RestauranteService restauranteService;

    @PostMapping
    public ResponseEntity<?> cadastrar(@Validated @RequestBody Restaurante restaurante) {
        try {
            Restaurante restauranteSalvo = restauranteService.cadastrar(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED).body(restauranteSalvo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro interno do servidor");
        }
    }

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        try {
            return ResponseEntity.ok(restauranteService.listarAtivos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro interno do servidor");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Optional<RestauranteDTO> restaurante = restauranteService.findById(id);
            if (restaurante != null) {
                return ResponseEntity.ok(restaurante);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurante não encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro interno do servidor");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @Validated @RequestBody Restaurante restaurante) {
        try {
            Restaurante atualizado = restauranteService.atualizar(id, restaurante);
            return ResponseEntity.ok(atualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro interno do servidor");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            restauranteService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro interno do servidor");
        }
    }
    //desativar restaurante
        @PutMapping("/{id}/inativar")
        public ResponseEntity<?> inativar(@PathVariable Long id) {
            try {
                restauranteService.inativar(id);
                return ResponseEntity.ok().body("Restaurante inativado com sucesso");
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno do servidor");
            }
        }
        //buscar por categoria
    @GetMapping("/categoria/{categoria}")
        public ResponseEntity<?> buscarPorCategoria(@PathVariable String categoria) {
            try {
                return ResponseEntity.ok(restauranteService.buscarPorCategoria(categoria));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno do servidor");
            }
        }

}