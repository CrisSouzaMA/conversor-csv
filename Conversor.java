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

  public String novoCpf(String cpf) {
    return cpf.substring(0, 3) + "."
            + cpf.substring(3, 6) + "."
            + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
  }
}
