package com.projetoJwt.auth.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projetoJwt.auth.domain.user.Role;
import com.projetoJwt.auth.domain.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Condutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String endereco;
    private String cep;
    private String tipo_Veiculo;
    private Double capacidadeVeiculo;

    @Column(name = "deleted")
    private boolean deleted;

    @Enumerated(EnumType.STRING)
    private SituacaoCondutor situacaoCondutor;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "condutor_carga",
            joinColumns = @JoinColumn(name = "condutor_id"),
            inverseJoinColumns = @JoinColumn(name = "produtoCarga_id"))
    private List<Carga> carga ;
    @JsonIgnore
    @OneToOne(mappedBy = "condutor",  cascade = CascadeType.ALL, orphanRemoval = true)
    private User user;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "condutor_role",
            joinColumns = @JoinColumn(name = "condutor_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    //IGNORE LOMBOK
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }



}

