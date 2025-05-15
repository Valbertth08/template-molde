package pdf.certificado.template.pdf;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.util.StreamUtil;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class teste {

    public static void main(String[] args) throws Exception {
        gerarCertificado("Maria Lima", "certificado_maria.pdf", "static/images/moldura.png");

    }


    public static void gerarCertificado(String nome, String nomeArquivoPDF, String imagemFundoPath) throws Exception {
        PdfWriter writer = new PdfWriter(nomeArquivoPDF);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4.rotate());

        // Adiciona imagem de fundo (moldura)
        InputStream is = teste.class.getClassLoader().getResourceAsStream(imagemFundoPath);
        if (is == null) throw new FileNotFoundException("Imagem não encontrada: " + imagemFundoPath);
        Image imgFundo = new Image(ImageDataFactory.create(StreamUtil.inputStreamToArray(is)));
        imgFundo.scaleToFit(PageSize.A4.rotate().getWidth(), PageSize.A4.rotate().getHeight());
        imgFundo.setFixedPosition(0, 0);
        document.add(imgFundo);

        // Adiciona borda (opcional)
        PdfCanvas canvas = new PdfCanvas(pdf.getFirstPage());
        Rectangle pageSize = pdf.getDefaultPageSize();
        float borderWidth = 4f;
        canvas.saveState();
        canvas.setStrokeColor(new DeviceRgb(0x2F, 0x63, 0x38))
                .setLineWidth(borderWidth)
                .rectangle(
                        borderWidth + 20,
                        borderWidth + 20,
                        pageSize.getWidth() - 40,
                        pageSize.getHeight() - 40)
                .stroke();
        canvas.restoreState();

        // Conteúdo fixo sobre o fundo
        document.showTextAligned("Certificado de Participação",
                pageSize.getWidth() / 2, 500, TextAlignment.CENTER);
        document.showTextAligned(nome,
                pageSize.getWidth() / 2, 450, TextAlignment.CENTER);

        document.close();
    }

}
