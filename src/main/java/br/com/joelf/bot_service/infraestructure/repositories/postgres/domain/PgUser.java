package br.com.joelf.bot_service.infraestructure.repositories.postgres.domain;

import br.com.joelf.bot_service.domain.entities.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@NoArgsConstructor
public class PgUser extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String cpfCnpj;
    private String password;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public PgUser(UUID id, String name, String cpfCnpj, String password, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, name, cpfCnpj, password);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String getUsername() {
        return super.getCpfCnpj();
    }
}
