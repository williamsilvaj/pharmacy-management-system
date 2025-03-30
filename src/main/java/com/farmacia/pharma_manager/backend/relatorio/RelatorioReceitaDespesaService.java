package com.farmacia.pharma_manager.backend.relatorio;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.farmacia.pharma_manager.backend.venda.Venda;
import com.farmacia.pharma_manager.backend.venda.VendaRepository;
import com.farmacia.pharma_manager.backend.despesa.Despesa;
import com.farmacia.pharma_manager.backend.despesa.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;

@Service
public class RelatorioReceitaDespesaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private DespesaRepository despesaRepository;

    public byte[] gerarRelatorioPDF(LocalDate dataInicio, LocalDate dataFim) {
        List<Venda> vendas = vendaRepository.findByDataBetween(dataInicio, dataFim);
        List<Despesa> despesas = despesaRepository.findByDataBetween(dataInicio, dataFim);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (PdfWriter writer = new PdfWriter(baos);
             PdfDocument pdfDoc = new PdfDocument(writer);
             Document document = new Document(pdfDoc)) {

            // Título do Relatório
            document.add(new Paragraph("Relatório de Receita e Despesas")
                    .setBold().setFontSize(16));
            document.add(new Paragraph("Período: " + dataInicio + " a " + dataFim)
                    .setFontSize(12));
            document.add(new Paragraph(" "));

            // Seção de Vendas (Receita)
            document.add(new Paragraph("Receita (Vendas)").setBold().setFontSize(14));
            float[] colWidthsVendas = {150, 60, 60, 60};
            Table tabelaVendas = new Table(colWidthsVendas);
            tabelaVendas.addHeaderCell(new Cell().add(new Paragraph("Farmacêutico")));
            tabelaVendas.addHeaderCell(new Cell().add(new Paragraph("Quantidade")));
            tabelaVendas.addHeaderCell(new Cell().add(new Paragraph("Valor")));
            tabelaVendas.addHeaderCell(new Cell().add(new Paragraph("Data")));
            for (Venda v : vendas) {
                String nomeFarmaceutico = v.getFarmaceutico().getNome();
                tabelaVendas.addCell(new Cell().add(new Paragraph(nomeFarmaceutico)));
                tabelaVendas.addCell(new Cell().add(new Paragraph(String.valueOf(v.getQuantidade()))));
                tabelaVendas.addCell(new Cell().add(new Paragraph(String.format("%.2f", v.getValor()))));
                tabelaVendas.addCell(new Cell().add(new Paragraph(v.getData().toString())));
            }
            document.add(tabelaVendas);
            document.add(new Paragraph(" "));

            // Seção de Despesas
            document.add(new Paragraph("Despesas").setBold().setFontSize(14));
            float[] colWidthsDespesas = {150, 60, 60, 60};
            Table tabelaDespesas = new Table(colWidthsDespesas);
            tabelaDespesas.addHeaderCell(new Cell().add(new Paragraph("Descrição")));
            tabelaDespesas.addHeaderCell(new Cell().add(new Paragraph("Data")));
            tabelaDespesas.addHeaderCell(new Cell().add(new Paragraph("Valor")));
            tabelaDespesas.addHeaderCell(new Cell().add(new Paragraph("Gerente")));
            for (Despesa d : despesas) {
                tabelaDespesas.addCell(new Cell().add(new Paragraph(d.getDescricao())));
                tabelaDespesas.addCell(new Cell().add(new Paragraph(d.getData().toString())));
                tabelaDespesas.addCell(new Cell().add(new Paragraph(String.format("%.2f", d.getValor()))));
                String nomeGerente = d.getGerente() != null ? d.getGerente().getNome() : "N/A";
                tabelaDespesas.addCell(new Cell().add(new Paragraph(nomeGerente)));
            }
            document.add(tabelaDespesas);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }
}
