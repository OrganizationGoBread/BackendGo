package com.school.sptech.grupo3.gobread.arquivoCsv;

import com.school.sptech.grupo3.gobread.entity.*;
import com.school.sptech.grupo3.gobread.repository.ComercioRepository;
import com.school.sptech.grupo3.gobread.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArquivoTxtService {

    private final ComercioRepository comercioRepository;
    private final ProdutoRepository produtoRepository;

    public static void gravaRegistro(String registro, String nomeArq, String action) {
        BufferedWriter saida = null;

        try {
            String resourcesPath = ArquivoTxtService.class.getClassLoader().getResource("").getPath();

            String caminhoArquivo = resourcesPath + File.separator + nomeArq;

            saida = new BufferedWriter(new FileWriter(caminhoArquivo, !action.equals("clean")));
        } catch (IOException erro) {
            System.out.println("Erro na abertura do arquivo");
        }

        try {
            if (action.equals("write")) {
                saida.append(registro + "\n");
            } else if (action.equals("clean")) {
                saida.write(registro);
            }
            saida.close();
        } catch (IOException erro) {
            System.out.println("Erro ao gravar ou fechar o arquivo");
            erro.printStackTrace();
        }
    }


    public static void gravaArquivoTxT(Comercio comercio, String nomeArq){
        gravaRegistro("",nomeArq, "clean");

        int contaRegistroDados = 0;

        String header = "00DADOS CLIENTES";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "01";

        gravaRegistro(header, nomeArq, "write");

        List<Cliente> clientes = new ArrayList<>();

        for(int i = 0; i < comercio.getPedidos().size(); i++){
            Cliente cliente = comercio.getPedidos().get(i).getCliente();
            if(!clientes.contains(cliente)){
                clientes.add(cliente);
            }
        }

        for (int i = 0; i < clientes.size(); i++) {
            Cliente c = clientes.get(i);

            String corpo = "02";
            corpo += String.format("%02d", c.getId());
            corpo += String.format("%-30.30s", c.getNome());
            corpo += String.format("%-30.30s", c.getEmail());
            corpo += String.format("%-12.12s", c.getTelefone());
            corpo += String.format("%-8.8s", c.getEndereco().getCep());
            corpo += String.format("%-30.30s", c.getEndereco().getRua());
            corpo += String.format("%-30.30s", c.getEndereco().getBairro());

            gravaRegistro(corpo, nomeArq, "write");

            for(int j = 0; j < c.getPedidos().size(); j++){
                Pedido pedido = c.getPedidos().get(j);
                String corpoPedido = "03";
                corpoPedido += String.format("%02d", pedido.getId());
                corpoPedido += String.format("%-7.7s", pedido.getDiaEntrega());
                corpoPedido += String.format("%-5.5s", pedido.getHorarioEntrega());
                if(pedido.getComercio().equals(comercio)){
                    gravaRegistro(corpoPedido, nomeArq, "write");
                    contaRegistroDados++;
                }
            }
            contaRegistroDados++;
        }

        String trailer = "01";
        trailer += String.format("%05d", contaRegistroDados);
        trailer += "goBreadGroup!";

        gravaRegistro(trailer, nomeArq, "write");
    }

    public List<ItemComercio> importTxt(MultipartFile file) {
        BufferedReader entrada = null;
        String tipoRegistro, registro;

        try {

            String resourcesPath = new File("src/main/resources").getAbsolutePath();

            Path filePath = Path.of(resourcesPath, "produtos.txt");

            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            entrada = new BufferedReader(new FileReader(filePath.toFile()));


            registro = entrada.readLine();

            List<ItemComercio> itensComercio = new ArrayList<>();

            Comercio comercio = null;

            while(registro != null){


                ItemComercio itemComercio = null;

                tipoRegistro = registro.substring(0, 2);

                if(tipoRegistro.equals("02")){
                    String email = registro.substring(2, 32).trim();
                    comercio = this.comercioRepository.findByEmail(email).orElseThrow(
                            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comércio não encontrado")
                    );
                    comercio.setEmail(registro.substring(32, 62).trim());
                    comercio.setResponsavel(registro.substring(62, 92).trim());
                    comercio.setTelefone(registro.substring(92, 103).trim());
                    comercio.setRazaoSocial(registro.substring(103, 133).trim());
                    comercio = this.comercioRepository.save(comercio);
                } else if (tipoRegistro.equals("03")){
                    itemComercio = new ItemComercio();
                    Optional<Produto> produto = this.produtoRepository.findByNome(registro.substring(2, 32).trim());
                    if(produto.isPresent()){
                        itemComercio.setProduto(produto.get());
                    }
                    itemComercio.setComercio(comercio);
                    itensComercio.add(itemComercio);
                }
                registro = entrada.readLine();
            }

            entrada.close();
            return itensComercio;

        } catch (FileNotFoundException e){
            e.printStackTrace();
            throw new ResponseStatusException(422, "Diretório não encontrado", null);
        } catch (IOException e){
            e.printStackTrace();
            throw new ResponseStatusException(422, "Não foi possível converter para byte[]", null);
        }
    }
}
