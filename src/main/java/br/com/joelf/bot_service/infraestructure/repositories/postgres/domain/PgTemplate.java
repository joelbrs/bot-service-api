package br.com.joelf.bot_service.infraestructure.repositories.postgres.domain;

import br.com.joelf.bot_service.domain.entities.Template;
import br.com.joelf.bot_service.domain.entities.TemplateStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_template")
@Getter
@Setter
@NoArgsConstructor
public class PgTemplate extends Template {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;
    private String content;

    @Enumerated(EnumType.STRING)
    private TemplateStatus status;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
