package com.farmacia.pharma_manager.backend.item;

import com.farmacia.pharma_manager.backend.estoque.RelatorioEstoqueDTO;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;





@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item criarItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> listarItens() {
        return itemRepository.findAll();
    }

    public Optional<Item> obterItemPorId(Integer id) {
        return itemRepository.findById(id);
    }

    public Item atualizarItem(Integer id, Item itemAtualizado) {
        return itemRepository.findById(id).map(item -> {
            item.setValor(itemAtualizado.getValor());
            item.setDataVencimento(itemAtualizado.getDataVencimento());
            item.setProduto(itemAtualizado.getProduto());
            item.setVenda(itemAtualizado.getVenda());
            item.setEstoque(itemAtualizado.getEstoque());
            return itemRepository.save(item);
        }).orElse(null);
    }

    public boolean deletarItem(Integer id) {
        if(itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return true;
        }
        return false;
    }
// Adicione este método ao serviço
public List<Item> listarItensPorDataEntrada(LocalDate dataInicio, LocalDate dataFim) {
    return itemRepository.findByEstoqueDataEntradaBetween(dataInicio, dataFim);
}
    public int countItensDisponiveisPorProduto(Integer idProduto) {
        return itemRepository.countByProdutoIdProdutoAndVendaIsNull(idProduto);
    }

    public List<Item> listarItensDisponiveisPorProduto(Integer idProduto) {
        return itemRepository.findByProdutoIdProdutoAndVendaIsNull(idProduto);
    }

	public List<Item> findItensPorVenda(Integer idVenda) {
        return itemRepository.findByVendaIdVenda(idVenda);
    }

    public List<Item> listarItensDisponiveisPorProdutoComLimite(Integer idProduto, int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return itemRepository.findByProdutoIdProdutoAndVendaIsNull(idProduto, pageable);
    }
        public List<RelatorioEstoqueDTO> gerarRelatorioEstoque() {
        List<RelatorioEstoqueDTO> relatorio = new ArrayList<>();

        List<Item> itens = itemRepository.findAll();
        for (Item item : itens) {
            String nomeProduto = item.getProduto().getNome();
            Integer quantidade = item.getEstoque().getQuantidade();
            Double valor = item.getValor();
            LocalDate dataVencimento = item.getDataVencimento();
            LocalDate dataEntradaEstoque = item.getEstoque().getDataEntrada();

            RelatorioEstoqueDTO dto = new RelatorioEstoqueDTO(nomeProduto, quantidade, valor, dataVencimento, dataEntradaEstoque);
            relatorio.add(dto);
        }

        return relatorio;
    }

    public byte[] gerarRelatorioEstoquePdf() {
        List<Item> itens = itemRepository.findAll();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (PdfWriter writer = new PdfWriter(baos);
             PdfDocument pdfDoc = new PdfDocument(writer);
             Document document = new Document(pdfDoc)) {

            document.add(new Paragraph("Relatório de Estoque").setBold().setFontSize(16));
            float[] colWidths = {150, 100, 100, 100};
            Table table = new Table(colWidths);
            table.addHeaderCell(new Cell().add(new Paragraph("Produto")));
            table.addHeaderCell(new Cell().add(new Paragraph("Quantidade")));
            table.addHeaderCell(new Cell().add(new Paragraph("Valor")));
            table.addHeaderCell(new Cell().add(new Paragraph("Data Vencimento")));
            for (Item item : itens) {
                table.addCell(new Cell().add(new Paragraph(item.getProduto().getNome())));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(item.getEstoque().getQuantidade()))));
                table.addCell(new Cell().add(new Paragraph(String.format("%.2f", item.getValor()))));
                table.addCell(new Cell().add(new Paragraph(item.getDataVencimento().toString())));
            }
            document.add(table);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

    // Altere o método gerarRelatorioEstoquePdf para aceitar uma lista de itens
public byte[] gerarRelatorioPdf(List<Item> itens) {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try (PdfWriter writer = new PdfWriter(baos);
         PdfDocument pdfDoc = new PdfDocument(writer);
         Document document = new Document(pdfDoc)) {

        document.add(new Paragraph("Relatório de Estoque").setBold().setFontSize(16));
        float[] colWidths = {150, 100, 100, 100};
        Table table = new Table(colWidths);
        table.addHeaderCell(new Cell().add(new Paragraph("Produto")));
        table.addHeaderCell(new Cell().add(new Paragraph("Quantidade")));
        table.addHeaderCell(new Cell().add(new Paragraph("Valor")));
        table.addHeaderCell(new Cell().add(new Paragraph("Data Vencimento")));
        for (Item item : itens) {
            table.addCell(new Cell().add(new Paragraph(item.getProduto().getNome())));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(item.getEstoque().getQuantidade()))));
            table.addCell(new Cell().add(new Paragraph(String.format("%.2f", item.getValor()))));
            table.addCell(new Cell().add(new Paragraph(item.getDataVencimento().toString())));
        }
        document.add(table);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return baos.toByteArray();
}

}
