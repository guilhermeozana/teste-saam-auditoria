/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cadastro.funcionarios.vo;

import java.math.BigDecimal;
import java.util.Date;
import org.openswing.swing.message.receive.java.ValueObjectImpl;

/**
 *
 * @author Guilherme
 */
public class FuncionarioVO extends ValueObjectImpl {

    private Integer id;
    private String nome;
    private Date dataAdmissao;
    private BigDecimal valorSalario;
    private boolean status;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValorSalario() {
        return valorSalario;
    }

    public void setValorSalario(BigDecimal valorSalario) {
        this.valorSalario = valorSalario;
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }
    
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FuncionarioVO{" + "id=" + id + ", nome=" + nome + ", valorSalario=" + valorSalario + ", dataAdmissao=" + dataAdmissao + ", status=" + status + '}';
    }

     
    
    

}
