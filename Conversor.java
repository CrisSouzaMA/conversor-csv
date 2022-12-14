import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Conversor {

  public static void main(String[] args) throws IOException {
    File pastaDeEntradas = new File("./entradas/");
    File pastaDeSaidas = new File("./saidas/");
    new Conversor().converterPasta(pastaDeEntradas, pastaDeSaidas);
  }

  public void converterPasta(File pastaDeEntradas, File pastaDeSaidas) throws IOException {
    // TODO: Implementar.
    ArrayList<File> arquivosDeEntrada = new ArrayList<>();
    if (pastaDeEntradas.exists() && pastaDeEntradas.isDirectory()) {
      for (File arquivoEstado : pastaDeEntradas.listFiles()) {
        arquivosDeEntrada.add(arquivoEstado);
      }
    }

    if (arquivosDeEntrada.size() > 0) {
      criarPastaDeSaidas(pastaDeSaidas);
      for (File arquivoDeEntrada : arquivosDeEntrada) {
        novoArquivoDeSaida(pastaDeSaidas, arquivoDeEntrada);
      }
    }
  }

  private void criarPastaDeSaidas(File pastaDeSaidas) {
    boolean pastaDeSaidaCriada = pastaDeSaidas.mkdirs();
    if (pastaDeSaidaCriada) {
      System.out.println("Pasta '" + pastaDeSaidas.getName() + "' criada com sucesso!");
    }
  }

  private void novoArquivoDeSaida(File pastaDeSaidas, File arquivoDeEntrada) throws IOException {
    File arquivoDeSaida = new File(pastaDeSaidas, arquivoDeEntrada.getName());
    arquivoDeSaida.createNewFile();
    this.lerEscreverArquivo(arquivoDeEntrada, arquivoDeSaida);
  }

  public void lerEscreverArquivo(File arquivoDeEntrada, File arquivoDeSaida) throws IOException {
    BufferedReader leitorArquivo = new BufferedReader(new FileReader(arquivoDeEntrada));
    BufferedWriter escritorArquivo = new BufferedWriter(new FileWriter(arquivoDeSaida));

    String cabecalho = leitorArquivo.readLine();
    escritorArquivo.write(cabecalho);
    escritorArquivo.newLine();

    String conteudoLinha = leitorArquivo.readLine();

    while (conteudoLinha != null) {
      String linhaNova = "";
      String[] campoArquivo = conteudoLinha.split(",");
      String nome = campoArquivo[0].toUpperCase();
      String data = novaData(campoArquivo[1]);
      String email = campoArquivo[2];
      String cpf = novoCpf(campoArquivo[3]);

      escritorArquivo.write(linhaNova += nome + "," + data + "," + email + "," + cpf);
      escritorArquivo.newLine();

      conteudoLinha = leitorArquivo.readLine();
    }

    leitorArquivo.close();
    escritorArquivo.close();
  }

  private String novaData(String data) {
    String[] campos = data.split("/");

    String dia = campos[0];
    String mes = campos[1];
    String ano = campos[2];

    return String.format("%s-%s-%s", ano, mes, dia);
  }

  public String novoCpf(String cpf) {
    return cpf.substring(0, 3) + "."
            + cpf.substring(3, 6) + "."
            + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
  }
}
