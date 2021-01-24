package html.annotations.processors;

import com.google.auto.service.AutoService;
import html.annotations.*;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

/**
 * 03.12.2020
 * 15.Annotations_SOURCE
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@AutoService(Processor.class)
@SupportedAnnotationTypes(value = {"html.annotations.HtmlForm"})
public class HtmlFormProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        String outputPath = HtmlFormProcessor.class
            .getProtectionDomain()
            .getCodeSource()
            .getLocation()
            .getPath()
            .substring(1);

        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(HtmlForm.class);
        for (Element element : annotatedElements) {
            String form = getForm(element);

            // User.class -> User.html
            String outputFileName = outputPath + element.getSimpleName().toString() + ".html";
            try {
                BufferedWriter fileWriter = new BufferedWriter(new FileWriter(Paths.get(outputFileName).toFile()));
                fileWriter.write(form);
                fileWriter.close();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        return true;
    }

    private static String getForm(Element element) {
        StringBuilder stringBuilder = new StringBuilder();

        HtmlForm annotation = element.getAnnotation(HtmlForm.class);
        stringBuilder
            .append("<form action='")
            .append(annotation.action())
            .append("' method='")
            .append(annotation.method())
            .append("'/>")
            .append(System.lineSeparator());

        for (Element innerElement : element.getEnclosedElements()) {
            HtmlInput inputAnnotation = innerElement.getAnnotation(HtmlInput.class);
            if (inputAnnotation == null)
                continue;

            stringBuilder
                .append("<input type=\"")
                .append(inputAnnotation.type())
                .append("\" name=\"")
                .append(inputAnnotation.name())
                .append("\" placeholder=\"")
                .append(inputAnnotation.placeholder())
                .append("\">")
                .append(System.lineSeparator());
        }

        stringBuilder.append("</form>").append(System.lineSeparator());

        return stringBuilder.toString();
    }
}
