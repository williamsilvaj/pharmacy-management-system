package com.farmacia.pharma_manager.backend.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    private ItemService itemService;

        @GetMapping("/relatorio-pdf")
    public ResponseEntity<byte[]> gerarRelatorioPdf() {
        byte[] pdfBytes = itemService.gerarRelatorioEstoquePdf();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"relatorio_estoque.pdf\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }


    @PostMapping
    public ResponseEntity<Item> criarItem(@RequestBody Item item) {
        Item novoItem = itemService.criarItem(item);
        return new ResponseEntity<>(novoItem, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Item>> listarItens() {
        List<Item> itens = itemService.listarItens();
        return new ResponseEntity<>(itens, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> obterItemPorId(@PathVariable Integer id) {
        Optional<Item> item = itemService.obterItemPorId(id);
        return item.map(ResponseEntity::ok)
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> atualizarItem(@PathVariable Integer id, @RequestBody Item item) {
        Item itemAtualizado = itemService.atualizarItem(id, item);
        if(itemAtualizado != null) {
            return new ResponseEntity<>(itemAtualizado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarItem(@PathVariable Integer id) {
        if(itemService.deletarItem(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

	@GetMapping("/disponiveis/quantidade/{idProduto}")
    public ResponseEntity<Map<String, Integer>> getQuantidadeDisponivel(@PathVariable Integer idProduto) {
        int quantidade = itemService.countItensDisponiveisPorProduto(idProduto);
        return ResponseEntity.ok(Collections.singletonMap("quantidadeDisponivel", quantidade));
    }

    @GetMapping("/disponiveis/{idProduto}")
    public ResponseEntity<List<Item>> getItensDisponiveis(
            @PathVariable Integer idProduto,
            @RequestParam(required = false) Integer limit) {

        List<Item> itens;
        if (limit != null && limit > 0) {
            itens = itemService.listarItensDisponiveisPorProdutoComLimite(idProduto, limit);
        } else {
            itens = itemService.listarItensDisponiveisPorProduto(idProduto);
        }

        return ResponseEntity.ok(itens);
    }

	@GetMapping("/por-venda/{idVenda}")
	public ResponseEntity<List<Item>> getItensPorVenda(@PathVariable Integer idVenda) {
		List<Item> itens = itemService.findItensPorVenda(idVenda);
		return ResponseEntity.ok(itens);
	}
}
