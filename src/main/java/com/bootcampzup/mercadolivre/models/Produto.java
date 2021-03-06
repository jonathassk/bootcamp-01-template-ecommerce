package com.bootcampzup.mercadolivre.models;

import com.bootcampzup.mercadolivre.requests.CaracteristicasRequest;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "o nome deve ser preenchido")
    private String nome;
    @NotNull(message = "o valor deve ser preenchido")
    @Positive(message = "o valor deve ser positivo")
    private double valor;
    @Positive(message = "a quantidade deve ser positiva")
    private int quantidade;
    @Valid
    @ManyToOne
    @NotNull
    private Users dono;
    @NotEmpty(message = "a descrição deve ser preenchida")
    @Length(max = 1000, message = "A descrição deve ter no maximo 1000 caracteres")
    private String descricao;
    @NotNull
    @ManyToOne
    private Categoria categoria;
    private LocalDate instant;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    @Size(min = 3, message = "necessario ter pelo menos três caracteristicas")
    private Set<Caracteristica> caracteristicas = new HashSet<>();

    public Produto(@NotBlank(message = "o nome deve ser preenchido") String nome,
                   @NotNull(message = "o valor deve ser preenchido") double valor,
                   @Positive(message = "a quantidade deve ser positiva") int quantidade,
                   @Valid @NotNull Users dono,
                   @NotEmpty(message = "a descrição deve ser preenchida") @Length(max = 1000, message = "A descrição deve ter no maximo 1000 caracteres") String descricao,
                   @NotNull Categoria categoria, LocalDate instant,
                   @Size(min = 3, message = "necessario ter pelo menos três caracteristicas") Collection<CaracteristicasRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.dono = dono;
        this.descricao = descricao;
        this.categoria = categoria;
        this.instant = instant;
        this.caracteristicas.addAll(caracteristicas.stream().map(caracteristica -> caracteristica.toModel(this)).collect(Collectors.toSet()));
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Users getDono() {
        return dono;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public LocalDate getInstant() {
        return instant;
    }

    public Set<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public <T> Set<T> mapCaracteristicas (Function<Caracteristica, T> mapFunction ) {
        return this.caracteristicas.stream().map(mapFunction).collect(Collectors.toSet());
    }
}
