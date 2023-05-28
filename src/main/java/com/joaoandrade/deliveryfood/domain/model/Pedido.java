package com.joaoandrade.deliveryfood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import com.joaoandrade.deliveryfood.domain.model.enumeration.StatusPedido;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "seq_pedido", sequenceName = "seq_pedido", initialValue = 1, allocationSize = 1)
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pedido")
	private Long id;

	@CreationTimestamp
	private LocalDateTime dataPedido;
	private LocalDateTime dataCancelamento;
	private LocalDateTime dataConcluido;

	@Column(nullable = false)
	private BigDecimal subtotal;

	@Column(nullable = false)
	private BigDecimal taxaEntrega;

	@Column(nullable = false)
	private BigDecimal total;
	private String observacao;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusPedido status;

	@ManyToOne
	@JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "fk_cliente", value = ConstraintMode.CONSTRAINT))
	private Usuario cliente;

	@OneToOne
	@JoinColumn(nullable = false, name = "endereco_de_entrega_id", foreignKey = @ForeignKey(name = "fk_endereco_de_entrega", value = ConstraintMode.CONSTRAINT))
	private EnderecoDeEntrega enderecoDeEntrega;

	@OneToMany(mappedBy = "pedido", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<ItemPedido> itens = new ArrayList<>();

	public Pedido() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}

	public LocalDateTime getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(LocalDateTime dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public LocalDateTime getDataConcluido() {
		return dataConcluido;
	}

	public void setDataConcluido(LocalDateTime dataConcluido) {
		this.dataConcluido = dataConcluido;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getTaxaEntrega() {
		return taxaEntrega;
	}

	public void setTaxaEntrega(BigDecimal taxaEntrega) {
		this.taxaEntrega = taxaEntrega;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}

	public EnderecoDeEntrega getEnderecoDeEntrega() {
		return enderecoDeEntrega;
	}

	public void setEnderecoDeEntrega(EnderecoDeEntrega enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return Objects.equals(id, other.id);
	}

}
