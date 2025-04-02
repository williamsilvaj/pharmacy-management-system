package com.farmacia.pharma_manager.backend.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query("SELECT COALESCE(SUM(e.quantidade), 0) FROM Item i JOIN Estoque e ON i.estoque.idEstoque = e.idEstoque WHERE i.produto.idProduto = :idProduto")
  int somarQuantidadeNoEstoque(@Param("idProduto") Integer idProduto);

    @Query("SELECT i FROM Item i WHERE i.produto.idProduto = :idProduto AND i.venda IS NULL ORDER BY i.dataVencimento ASC")
    List<Item> findByProdutoIdProdutoAndVendaIsNull(Integer idProduto);

    @Query("SELECT i FROM Item i WHERE i.produto.idProduto = :idProduto AND i.venda IS NULL ORDER BY i.dataVencimento ASC")
    List<Item> findByProdutoIdProdutoAndVendaIsNull(Integer idProduto, Pageable pageable);

	@Query("SELECT i FROM Item i WHERE i.venda.idVenda = :idVenda")
	List<Item> findByVendaIdVenda(@Param("idVenda") Integer idVenda);

  // Adicione esta consulta ao repositório
@Query("SELECT i FROM Item i WHERE i.estoque.dataEntrada BETWEEN :dataInicio AND :dataFim")
List<Item> findByEstoqueDataEntradaBetween(@Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);

}
