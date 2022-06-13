package com.anas.javautils.jpwd.args;

import org.apache.commons.cli.*;

public class ArgumentProcessor {
    private static ArgumentProcessor instance;
    private final Options options;
    private CommandLine commandLine;

    private ArgumentProcessor() {
        options = new Options();
        addOptions();
    }

    private void addOptions() {
        for (CLIOption cliOption : CLIOption.values()) {
            options.addOption(cliOption.getOption());
        }
    }

    // Singleton pattern :D
    public static ArgumentProcessor getInstance() {
        if (instance == null) {
            instance = new ArgumentProcessor();
        }
        return instance;
    }


    public void process(String[] args) {
        try {
            commandLine = new DefaultParser(true)
                    .parse(options, args, true);
            if (commandLine.hasOption("help")) {
                printHelp();
                System.exit(0);
            } else if (commandLine.hasOption("version")) {
                printVersion();
            }
        } catch (ParseException e) {
            System.out.println(e.getLocalizedMessage());
            printHelp();
            System.exit(1);
        }
    }

    private void printVersion() {
        // TODO: 6/13/22 implement this
    }

    private void printHelp() {
        new HelpFormatter().printHelp("jpwd", options);
    }


    public boolean hasOption(CLIOption option) {
        return commandLine.hasOption(option.getOption().getOpt());
    }

    public String getOptionValue(CLIOption option) {
        return commandLine.getOptionValue(option.getOption().getOpt());
    }
}
