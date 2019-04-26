package model.table;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PaginaTable extends AbstractTableModel {

    private List<String> listOriginal;
    private String[][] matriz;
    private static List<String> COLUNAS;
    private List<String> listTempTotal = new ArrayList<>();
    private int limitePorPagina;
    private int paginaAtual;
    private int totalDePagina;

    public PaginaTable(List<String> tabela, int limitePorPagina, List<String> COLUNAS) {
        if (this.paginaAtual == 0) {
            this.paginaAtual = 1;
        }
        this.listTempTotal = tabela;
        this.limitePorPagina = limitePorPagina;
        this.COLUNAS = COLUNAS;
        this.realizarMatriz();
    }

    public PaginaTable(List<String> tabela, int limitePorPagina, int paginaAtual, List<String> COLUNAS) {
        this.paginaAtual = paginaAtual;
        this.listTempTotal = tabela;
        this.limitePorPagina = limitePorPagina;
        int quantidadeDePaginas = (int) Math.ceil(Double.parseDouble(String.valueOf(tabela.size())) / limitePorPagina);
        this.totalDePagina = (int) quantidadeDePaginas;
        adicionarLinhasDaPagina(paginaAtual);
        this.fireTableDataChanged();
    }

    //<editor-fold  desc="Códigos para gestão da página">
    public void adicionarLinhasDaPagina(int pagina) {
        listOriginal = new ArrayList<>();
        for (int i = 0; limitePorPagina > i; i++) {
            int numeroCategoria = (((pagina - 1) * limitePorPagina) + i);
            if (numeroCategoria != listTempTotal.size()) {
                listOriginal.add(listTempTotal.get(numeroCategoria));
            } else {
                break;
            }
        }
        this.fireTableDataChanged();
    }

    public void proximaPagina() {
        if (paginaAtual != totalDePagina) {
            paginaAtual++;
            adicionarLinhasDaPagina(paginaAtual);
        }

    }

    public void anteriorPagina() {
        if (paginaAtual != 1) {
            paginaAtual--;
            adicionarLinhasDaPagina(paginaAtual);
        }

    }

    public void paginaFinal() {
        int quantidadeDePaginas = (int) Math.ceil(Double.parseDouble(String.valueOf(listTempTotal.size())) / limitePorPagina);
        paginaAtual = quantidadeDePaginas;
        adicionarLinhasDaPagina(paginaAtual);
    }

    public void paginaInicial() {
        paginaAtual = 1;
        adicionarLinhasDaPagina(paginaAtual);
    }

    public void alterarLimite(int limitePorPagina) {
        this.limitePorPagina = limitePorPagina;
        this.paginaAtual = 1;
        int quantidadeDePaginas = (int) Math.ceil(Double.parseDouble(String.valueOf(listTempTotal.size())) / limitePorPagina);
        this.totalDePagina = quantidadeDePaginas;
        adicionarLinhasDaPagina(paginaAtual);
    }

    public boolean verificarExistenciaProxima() {
        if (paginaAtual < totalDePagina) {
            return true;
        }
        return false;
    }

    public boolean verificarExistenciaAnterior() {
        if (paginaAtual > 1) {
            return true;
        }
        return false;
    }

    public boolean verificarPrimeiraPagina() {
        return paginaAtual == 1;
    }

    public boolean verificarUltimaPagina() {
        int quantidadeDePaginas = (int) Math.ceil(Double.parseDouble(String.valueOf(listTempTotal.size())) / limitePorPagina);
        if (paginaAtual < quantidadeDePaginas) {
            return true;
        }
        return false;
    }

    public void atualizarLista(List<String> listNova) {
        this.listTempTotal = listNova;
//        this.paginaAtual = 1;
        int quantidadeDePaginas = (int) Math.ceil(Double.parseDouble(String.valueOf(listTempTotal.size())) / limitePorPagina);
        this.totalDePagina = (int) quantidadeDePaginas;
        adicionarLinhasDaPagina(paginaAtual);

    }

    public int getPaginaAtual() {
        return paginaAtual;
    }

    public List<String> getListCategoriasTotal() {
        return listTempTotal;
    }

    public int getLimitePorPagina() {
        return limitePorPagina;
    }

    public int getTotalDePagina() {
        return totalDePagina;
    }

    public void setPaginaAtual(int paginaAtual) {
        this.paginaAtual = paginaAtual;
    }

    public void setListPadrao(List<String> lista) {
        this.listOriginal = lista;
    }

    //</editor-fold>
    @Override
    public int getRowCount() {
        return listTempTotal.size() / COLUNAS.size();
    }

    @Override
    public int getColumnCount() {
        System.out.println("Z:" + COLUNAS.size());
        return COLUNAS.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return matriz[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return COLUNAS.get(column);
    }

    private void realizarMatriz() {
        int row = listTempTotal.size() / COLUNAS.size();
        double rowDouble = listTempTotal.size() / COLUNAS.size();
        int column = COLUNAS.size();
        double columnDouble = COLUNAS.size();
        matriz = new String[row][column];
        int coluna = 0;
        for (int linha = 0; linha < listTempTotal.size(); linha++) {
            row = (int) (linha / columnDouble);
            matriz[row][coluna] = listTempTotal.get(linha);
            coluna++;
            if (coluna == column) {
                coluna = 0;
            }
        }
    }

    public void novaLinha(List<String> novaLinha) {
        for (int i = 0; novaLinha.size() > i; i++) {
            listTempTotal.add(novaLinha.get(i));
        }
        realizarMatriz();
        fireTableDataChanged();
    }

    public void excluir(int selectedRow) {
        for (int i = 0; COLUNAS.size() > i; i++) {
            listTempTotal.remove(selectedRow);
        }
    }


}
