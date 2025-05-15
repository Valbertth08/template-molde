package pdf.certificado.template.layout;

import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.FileOutputStream;

@Service
public class CertificadoService {

    private final TemplateEngine templateEngine;

    public CertificadoService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public void gerarCertificado(String nome, String caminhoSaida) throws Exception {
        // Configura o contexto com a variável 'nome'
        Context context = new Context();
        context.setVariable("nome", nome);

        // Processa o template HTML com Thymeleaf
        String htmlContent = templateEngine.process("template", context);

        // Converte o HTML para PDF usando iText
        try (FileOutputStream fos = new FileOutputStream(caminhoSaida)) {
            HtmlConverter.convertToPdf(htmlContent, fos);
        }
    }

}
