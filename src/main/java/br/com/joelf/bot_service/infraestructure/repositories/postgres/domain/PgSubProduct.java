package br.com.joelf.bot_service.infraestructure.repositories.postgres.domain;

import br.com.joelf.bot_service.domain.entities.SubProduct;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_sub_product")
@Getter
@Setter
@NoArgsConstructor
public class PgSubProduct extends SubProduct<PgProduct> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "fk_product_id")
    private PgProduct product;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
