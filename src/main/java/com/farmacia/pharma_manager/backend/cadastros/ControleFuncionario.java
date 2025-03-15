package com.farmacia.pharma_manager.backend.cadastros;
import java.util.List;
import java.util.ArrayList;
import com.farmacia.pharma_manager.backend.funcionario.Funcionario;

public class ControleFuncionario {

    private List<Funcionario> listaFuncionarios;

      // Construtor
      public ControleFuncionario() {
        listaFuncionarios = new ArrayList<>();
    }

    public void cadastrarFuncionario (Funcionario funcionario){
        listaFuncionarios.add(funcionario);
        System.out.println("Funcionário cadastrado com sucesso: " + funcionario.getNome());
    }

    public Funcionario consultarFuncionario(String cpf) {
        for (Funcionario f : listaFuncionarios) {
            if (f.getCpf().equals(cpf)) {
                return f;
            }
        }
        return null;
    }

    public void alterarFuncionario(String novoNome, String novoTelefone, String cpf, String novoCargo) {
        Funcionario funcionario = consultarFuncionario(cpf);
        if (funcionario != null) {
            funcionario.setNome(novoNome);
            funcionario.setCargo(novoCargo);
            funcionario.setTelefone(novoTelefone);
            System.out.println("Funcionário alterado com sucesso: " + novoNome);
        }
        else {
            System.out.println("Funcionário não encontrado.");
        }
    }

    public void removerFuncionario(String nome) {
        for (Funcionario f : listaFuncionarios) {
            if (f.getNome().equals(nome)) {
                listaFuncionarios.remove(f);
                System.out.println("Funcionário removido com sucesso.");
                return;
            }
        }
        System.out.println("Funcionário não encontrado.");
    }

    // Listar todos os funcionários
    public void listarFuncionarios() {
        if (listaFuncionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
        } else {
            System.out.println("Lista de Funcionários:");
            for (Funcionario f : listaFuncionarios) {
                System.out.println(f);
            }
        }
    }

    // Atribuir um cargo a um funcionário
    public void atribuirCargo(String nome, String novoCargo) {
        for (Funcionario f : listaFuncionarios) {
            if (f.getNome().equals(nome)) {
                f.setCargo(novoCargo);
                System.out.println("Cargo atribuído com sucesso: " + f);
                return;
            }
        }
        System.out.println("Funcionário não encontrado.");
    }



}
