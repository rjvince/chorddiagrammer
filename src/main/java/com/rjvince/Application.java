package com.rjvince;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

public class Application {

    public static void main(String[] args) throws IOException {
        Configuration config = initConfiguration();
        Template tmpl = config.getTemplate("page-master.ftlh");
        BufferedReader bufferedReader = initBuffer(args);
        String input = bufferedReader.readLine();
        String filenameFmt = "%s-%s.svg";
        DecimalFormat df = new DecimalFormat("000");

        List<ChordDiagram> chords = new LinkedList<>();

        while (input != null) {
            if (!input.startsWith("=")) {
                chords.add(new ChordDiagram(input));
            }
            input = bufferedReader.readLine();
        }

        for (int i = 0; i < chords.size(); i++) {
            ChordDiagram c = chords.get(i);
            String filename = String.format(filenameFmt, df.format(i + 1), c.getName());
            filename = filename.replace('♭', 'b').replace('♯', '#');
            try (Writer writer = new FileWriter(filename)) {
                tmpl.process(c, writer);
                System.out.println(c);
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }
    }

    private static BufferedReader initBuffer(String[] args) throws FileNotFoundException {
        InputStreamReader isr;
        if (args.length == 1) {
            isr = new FileReader(args[0]);
        } else {
            isr = new InputStreamReader(System.in);
        }
        return new BufferedReader(isr);
    }

    private static Configuration initConfiguration() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
        cfg.setClassForTemplateLoading(Application.class, "/template/");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
        return cfg;
    }
}