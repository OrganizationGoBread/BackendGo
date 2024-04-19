package com.school.sptech.grupo3.gobread.entity;

import com.school.sptech.grupo3.gobread.integrations.apiviacep.AddressViaCep;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Builder(toBuilder = true)
@Entity
public class Cliente implements UserDetails {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private String email;
        private String senha;
        private String telefone;
        private String nome;
        private String cpf;
        private String tipo;
        private String assinatura;
        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "fkEndereco", referencedColumnName = "id")
        private Endereco endereco;
        @OneToMany(mappedBy = "cliente")
        private List<Pedido> pedidos;

        public Cliente(Integer id, String email, String senha, String telefone, String nome, String cpf, String tipo, String assinatura, Endereco endereco, List<Pedido> pedidos) {
                this.id = id;
                this.email = email;
                this.senha = senha;
                this.telefone = telefone;
                this.nome = nome;
                this.cpf = cpf;
                this.tipo = tipo;
                this.assinatura = assinatura;
                this.endereco = endereco;
                this.pedidos = pedidos;
        }

        public Cliente() {
        }

        public Cliente atualizarEndereco(AddressViaCep addressViaCep) {
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

        public void setTelefone(String telefone) {
                this.telefone = telefone;
        }

        public String getNome() {
                return nome;
        }

        public void setNome(String nome) {
                this.nome = nome;
        }

        public String getCpf() {
                return cpf;
        }

        public void setCpf(String cpf) {
                this.cpf = cpf;
        }

        public Endereco getEndereco() {
                return endereco;
        }

        public void setEndereco(Endereco endereco) {
                this.endereco = endereco;
        }

        public List<Pedido> getPedidos() {
                return pedidos;
        }

        public void setPedidos(List<Pedido> pedidos) {
                this.pedidos = pedidos;
        }

        public String getTipo() {
                return tipo;
        }

        public void setTipo(String tipo) {
                this.tipo = tipo;
        }

        public String getAssinatura() {
                return assinatura;
        }

        public void setAssinatura(String assinatura) {
                this.assinatura = assinatura;
        }


        @Override
        public String toString() {
                return "Cliente{" +
                        "nome='" + nome + '\'' +
                        ", cpf='" + cpf + '\'' +
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
