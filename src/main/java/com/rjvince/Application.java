package com.rjvince;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.cli.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

public class Application {

    public static void main(String[] args) throws IOException {
        Configuration config = initConfiguration();
        Template tmpl = config.getTemplate("page-master.ftlh");
        ChordDiagrammerOptions cdOpts;
        String dir;

        Options options = new Options();
        options.addOption("h", "help", false, "print this message");
        options.addOption("v", "verbose", false, "verbose mode");
        options.addOption("s", "suppress-note-names", false, "suppress appearance of note names under diagram");
        options.addOption("c", "clean", false, "clean output directory first - as in delete everything");
        options.addOption("t", "transpose", true, "transpose <number of semitones>");

        try {
            cdOpts = parseOptions(options, args);

            BufferedReader bufferedReader = initBuffer(cdOpts.inFile());
            String input = bufferedReader.readLine();
            String filenameFmt = "%s-%s.svg";
            DecimalFormat df = new DecimalFormat("000");

            List<ChordDiagram> chords = new LinkedList<>();

            while (input != null) {
                if (!input.startsWith("=")) {
                    ChordDiagram cd = new ChordDiagram(input, cdOpts.transposeSteps());
                    cd.setSuppressNoteNames(cdOpts.suppressNoteNames());
                    chords.add(cd);
                }
                input = bufferedReader.readLine();
            }

            if (!chords.isEmpty()) {
                dir = chords.get(0).getTuningStr();
                if (cdOpts.clean()) {
                    cleanTuningDirectory(dir);
                }
                makeTuningDirectory(dir);

                for (int i = 0; i < chords.size(); i++) {
                    ChordDiagram c = chords.get(i);
                    String filename = dir + "/" + String.format(filenameFmt, df.format(i + 1), c.getName());
                    filename = filename.replace('♭', 'b').replace('♯', '#');
                    try (Writer writer = new FileWriter(filename)) {
                        tmpl.process(c, writer);
                        if (cdOpts.verbose()) {
                            System.out.println(c);
                        }
                    } catch (TemplateException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (ParseException e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("chord-diagrammer <input-file>", options);
            System.exit(0);
        }
    }

    private static void cleanTuningDirectory(String dir) {
        File dirFile = new File(dir);
        try {
            Files.walk(dirFile.toPath())
                    .filter(p -> p.endsWith(".svg"))
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            System.err.println("Couldn't clear " + dir
                    + "\nMaybe it wasn't there? I'll keep going though.");
        }
    }

    private static ChordDiagrammerOptions parseOptions(Options options, String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        if (cmd.hasOption("h") || args.length == 0) {
            throw new ParseException("Help flag");
        }

        int transposeSteps = cmd.hasOption("t") ? Integer.parseInt(cmd.getOptionValue("t")) : 0;
        String filename = cmd.getArgs().length > 0 ? cmd.getArgs()[0] : null;

        return new ChordDiagrammerOptions(cmd.hasOption("v"), cmd.hasOption("s"), cmd.hasOption("c"), transposeSteps, filename);
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

    private static BufferedReader initBuffer(String inFile) throws FileNotFoundException {
        InputStreamReader isr = new FileReader(inFile);
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