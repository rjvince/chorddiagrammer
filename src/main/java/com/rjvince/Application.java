package com.rjvince;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.cli.*;

import java.io.*;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

public class Application {

    public static void main(String[] args) throws IOException, ParseException {
        Configuration config = initConfiguration();
        Template tmpl = config.getTemplate("page-master.ftlh");
        Integer transposeSteps = 0;
        String dir;

        Options options = new Options();
        options.addOption("h", "help", false, "print this message");
        options.addOption("t", "transpose", true, "transpose <number of semitones>");
        CommandLineParser parser = new DefaultParser();
        parser.parse(options, args);
        CommandLine cmd = parser.parse(options, args);
        if (cmd.hasOption("h")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("chorddiagrammer", options);
            System.exit(0);
        }

        if (cmd.hasOption("t")) {
            transposeSteps = Integer.parseInt(cmd.getOptionValue("t"));
        }

        BufferedReader bufferedReader = initBuffer(cmd.getArgs());
        String input = bufferedReader.readLine();
        String filenameFmt = "%s-%s.svg";
        DecimalFormat df = new DecimalFormat("000");

        List<ChordDiagram> chords = new LinkedList<>();

        while (input != null) {
            if (!input.startsWith("=")) {
                ChordDiagram cd = new ChordDiagram(input, transposeSteps);
                chords.add(cd);
            }
            input = bufferedReader.readLine();
        }

        if (!chords.isEmpty()) {
            dir = chords.get(0).getTuningStr();
            makeTuningDirectory(dir);

            for (int i = 0; i < chords.size(); i++) {
                ChordDiagram c = chords.get(i);
                String filename = dir + "/" + String.format(filenameFmt, df.format(i + 1), c.getName());
                filename = filename.replace('♭', 'b').replace('♯', '#');
                try (Writer writer = new FileWriter(filename)) {
                    tmpl.process(c, writer);
                    System.out.println(c);
                } catch (TemplateException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void makeTuningDirectory(String tuningStr) {
        String dirname = tuningStr.replace('♭', 'b').replace('♯', '#');
        File dir = new File(dirname);
        if (!dir.exists()) {
            boolean result = dir.mkdir();
            if (!result) {
                System.err.println("Could not create directory: " + tuningStr);
                System.exit(1);
            }
        }
    }

    private static BufferedReader initBuffer(String[] args) throws FileNotFoundException {
        InputStreamReader isr;
        if (args.length >= 1) {
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