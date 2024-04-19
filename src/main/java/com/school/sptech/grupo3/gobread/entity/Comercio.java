package com.school.sptech.grupo3.gobread.entity;

import com.school.sptech.grupo3.gobread.integrations.apiviacep.AddressViaCep;
import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Builder(toBuilder = true)
@Entity
public class Comercio implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String senha;
    private String telefone;
    private String razaoSocial;
    private String responsavel;
    private String cnpj;
    private String tipo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fkEndereco", referencedColumnName = "id")
    private Endereco endereco;
    @OneToMany(mappedBy = "comercio")
    private List<Pedido> pedidos;

    public Comercio(Integer id, String email, String senha, String telefone, String razaoSocial, String responsavel, String cnpj, String tipo, Endereco endereco, List<Pedido> pedidos) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.razaoSocial = razaoSocial;
        this.responsavel = responsavel;
        this.cnpj = cnpj;
        this.tipo = tipo;
        this.endereco = endereco;
        this.pedidos = pedidos;
    }

    public Comercio(Integer id) {
        this.id = id;
    }

    public Comercio() {
    }

    public Comercio atualizarEndereco(AddressViaCep addressViaCep) {
        return this.toBuilder()
                .endereco(Endereco.builder()
                        .cep(this.endereco.getCep())
                        .rua(addressViaCep.logradouro())
                        .numero(this.endereco.getNumero())
                        .complemento(this.endereco.getComplemento())
                        .bairro(addressViaCep.bairro())
                        .cidade(addressViaCep.localidade())
                        .estado(addressViaCep.uf())
                        .build())
                .build();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }


    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getResponsavel() {
        return responsavel;
    }


    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }


    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }


    @Override
    public String toString() {
        return "Comercio{" +
                "razaoSocial='" + razaoSocial + '\'' +
                ", responsavel='" + responsavel + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", endereco=" + endereco +
                "} ";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
