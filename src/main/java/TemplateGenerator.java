import com.google.gson.Gson;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

public class TemplateGenerator {
    public static void main(String[] args) {

        String template = new TemplateGenerator().createTemplate();
        try {
            Files.write(Paths.get("./report.html"), template.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(template);
    }

    String createTemplate() {
        VelocityEngine velocityEngine = new VelocityEngine();

        Properties props = new Properties();
        String path = getClass().getResource("").getPath();
        props.put("file.resource.loader.path", path);

        velocityEngine.init(props);

        Template t = velocityEngine.getTemplate("report.vm");

        VelocityContext context = new VelocityContext();
        context.put("type", "ReqIF");

        List<Product> products = Arrays.asList(
                new Product(UUID.randomUUID(), "ProductA", "big", LocalDateTime.now(), Arrays.asList(new Element(UUID.randomUUID(), "foo"), new Element(UUID.randomUUID(), "foo"), new Element(UUID.randomUUID(), "foo"))),
                new Product(UUID.randomUUID(), "ProductB", "medium", LocalDateTime.now().plusDays(7), Arrays.asList(new Element(UUID.randomUUID(), "foo"), new Element(UUID.randomUUID(), "foo"), new Element(UUID.randomUUID(), "foo"))),
                new Product(UUID.randomUUID(), "ProductC", "small", LocalDateTime.now().plusDays(14), Arrays.asList(new Element(UUID.randomUUID(), "foo"), new Element(UUID.randomUUID(), "foo"), new Element(UUID.randomUUID(), "foo")))
        );

        context.put("products", products);

        Gson gson = new Gson();
        String produtsJson = gson.toJson(products);
        context.put("productsJson", produtsJson);

        StringWriter writer = new StringWriter();
        t.merge( context, writer );
        return writer.toString();
    }
}
