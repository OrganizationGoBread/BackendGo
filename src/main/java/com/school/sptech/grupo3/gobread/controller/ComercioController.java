package com.school.sptech.grupo3.gobread.controller;

import com.school.sptech.grupo3.gobread.controller.request.ComercioRequest;
import com.school.sptech.grupo3.gobread.controller.request.ComercioUpdateRequest;
import com.school.sptech.grupo3.gobread.controller.request.LoginRequest;
import com.school.sptech.grupo3.gobread.controller.response.ComercioResponse;
import com.school.sptech.grupo3.gobread.controller.response.ComercioSemPedidoResponse;
import com.school.sptech.grupo3.gobread.controller.response.EstoqueResponse;
import com.school.sptech.grupo3.gobread.controller.response.LoginComercioResponse;
import com.school.sptech.grupo3.gobread.exceptions.UsuarioNaoEncontradoException;
import com.school.sptech.grupo3.gobread.service.ComercioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/api/comercios")
@RequiredArgsConstructor
public class ComercioController {

    private final ComercioService comercioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<ComercioResponse> cadastrarComercio(@Valid @RequestBody ComercioRequest comercioRequest) {
        return comercioService.criarComercio(comercioRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginComercioResponse> loginCliente(@RequestBody LoginRequest loginRequest) throws UsuarioNaoEncontradoException {
        LoginComercioResponse response = comercioService.autenticar(loginRequest);
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComercioResponse> buscarPorId(@PathVariable int id) {
        ComercioResponse comercioResponse = this.comercioService.buscarComercioPorId(id);
        return ResponseEntity.ok(comercioResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComercioResponse> atualizarComercio(@PathVariable int id, @Valid @RequestBody ComercioUpdateRequest comercioRequest) {
        return comercioService.atualizarComercio(id, comercioRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ComercioResponse> deletarComercio(@PathVariable int id) {
        return comercioService.deletarComercio(id);
    }

    @GetMapping("/bairro")
    public ResponseEntity<List<ComercioSemPedidoResponse>> buscarPeloBairro(@RequestParam String bairro) {
        List<ComercioSemPedidoResponse> comercios = this.comercioService.buscarPeloBairro(bairro);
        if (comercios.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(comercios);
    }


    @GetMapping("/csv")
    public ResponseEntity<Void> csv() {
        comercioService.gerarArquivoCsv();
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/download/clientes-csv")
    public ResponseEntity<byte[]> download() {

        boolean arquivoGerado = comercioService.gerarArquivoCsv();
        if (arquivoGerado) {
            try {
                Resource resource = new ClassPathResource("/relatorio-clientes.csv");
                File file = resource.getFile();
                InputStream fileInputStream = new FileInputStream(file);

                return ResponseEntity.status(200)
                        .header("Content-Disposition",
                                "attachment; filename=relatorio-clientes.csv")
                        .body(fileInputStream.readAllBytes());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new ResponseStatusException(422, "Diretório não encontrado", null);
            } catch (IOException e) {
                e.printStackTrace();
                throw new ResponseStatusException(422, "Não foi possível converter para byte[]", null);
            }
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/download/clientes-txt/{idComercio}")
    public ResponseEntity<byte[]> downloadTxt(@PathVariable int idComercio){

        comercioService.gerarArquivoTxt(idComercio);

        try {
            Resource resource = new ClassPathResource("/clientes.txt");
            File file = resource.getFile();
            InputStream fileInputStream = new FileInputStream(file);

            return ResponseEntity.status(200)
                    .header("Content-Disposition",
                            "attachment; filename=clientes.txt")
                    .body(fileInputStream.readAllBytes());
        } catch (FileNotFoundException e){
            e.printStackTrace();
            throw new ResponseStatusException(422, "Diretório não encontrado", null);
        } catch (IOException e){
            e.printStackTrace();
            throw new ResponseStatusException(422, "Não foi possível converter para byte[]", null);
        }
    }

    @PostMapping("/upload/produtos-txt")
    public ResponseEntity<EstoqueResponse> upload(@RequestParam("file") MultipartFile file){
        EstoqueResponse estoqueResponse = this.comercioService.upload(file);
        if(estoqueResponse.getItensComercio().isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(estoqueResponse);
    }
}
