import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

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
                new Product("ProductA", Arrays.asList(new Element(1), new Element(2), new Element(3))),
                new Product("ProductB", Arrays.asList(new Element(4), new Element(5), new Element(6))),
                new Product("ProductC", Arrays.asList(new Element(7), new Element(8), new Element(9)))
        );

        context.put("products", products);

        StringWriter writer = new StringWriter();
        t.merge( context, writer );
        return writer.toString();
    }
}
