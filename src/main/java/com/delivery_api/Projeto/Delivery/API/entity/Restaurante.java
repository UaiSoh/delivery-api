package com.delivery_api.Projeto.Delivery.API.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurantes")
public class Restaurante {
    // Definição dos atributos da entidade Restaurante

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String categoria;

    private String endereco;

    private String telefone;

    @Column(name = "taxa_entrega")
    private BigDecimal taxaEntrega;

    private BigDecimal avaliacao;

    private Boolean ativo;

    public void inativar() {
        this.ativo = false;
    }

}
