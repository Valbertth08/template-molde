package pdf.certificado.template.layout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class TesteController {

    @Autowired
    private CertificadoService certificadoService;

    @PostMapping
    public ResponseEntity<Void> testePdf(@RequestParam String nome, @RequestParam String caminho) throws Exception {
        certificadoService.gerarCertificado(nome,caminho);
       return ResponseEntity.noContent().build();
    }

}
