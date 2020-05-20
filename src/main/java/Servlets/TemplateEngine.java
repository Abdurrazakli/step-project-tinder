package Servlets;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.stream.Collectors;

public class TemplateEngine {

    private final Configuration config;

    public TemplateEngine(String root_path)  {
        this.config = new Configuration(Configuration.VERSION_2_3_28){{
            try {
                setDirectoryForTemplateLoading(new File("./src/main/java/Templates/"));
            } catch (IOException e) {
                e.printStackTrace();

            }
            setDefaultEncoding(String.valueOf(StandardCharsets.UTF_8));
            setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            setLogTemplateExceptions(false);
            setWrapUncheckedExceptions(true);
        }};
    }

    public static TemplateEngine folder(String path) throws IOException {
        return new TemplateEngine(path);
    }

    public void render(String templateName, HashMap<String, Object> data, HttpServletResponse resp){
        resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        try(PrintWriter w = resp.getWriter()){
            config.getTemplate(templateName).process(data,w);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
            throw new RuntimeException("Freemarker error",e);
        }
    }

    public void render(String templateName,HttpServletResponse resp){
        String fullPath = "./src/main/java/Templates/".concat(templateName).concat("/");
        try (PrintWriter w = resp.getWriter()){
            String content  = new BufferedReader(new FileReader(new File(fullPath)))
                    .lines()
                    .collect(Collectors.joining("\n"));
            w.write(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Template file not found!");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Cant render template!");
        }
    }
}
