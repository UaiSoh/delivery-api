package com.delivery_api.Projeto.Delivery.API.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.delivery_api.Projeto.Delivery.API.entity.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    // Buscar por nome
    Optional<Restaurante> findByNome(String nome);

    // Buscar por categoria
	List<Restaurante> findByCategoria(String categoria);

    // Buscar restaurantes ativos
	List<Restaurante> findByAtivoTrue();

    // Buscar por taxa de entrega
	List<Restaurante> findByTaxaEntregaLessThanEqual(BigDecimal taxa_entrega);

    // Buscar os 5 primeiros restaurantes ordenados por nome
	List<Restaurante> findTop5ByOrderByNomeAsc();

	// Buscar todos ordenados por avaliação decrescente
	List<Restaurante> findAllByOrderByAvaliacaoDesc();

}
