package Behavioral.COR;

import java.util.Date;

public class Logger {

    private static Logger logger;
    private LogLevel logLevel;
    private LogSink logSink;

    private Logger(){
        logLevel = LogLevel.INFO; // Default log level
        logSink = new ConsoleLogSink(); // Default log sink
    }

    private Logger(LogLevel level, LogSink sink){
        logLevel = level;
        logSink = sink;
    }

    public static Logger getLogger(){
        return logger!=null?logger:(logger=new Logger());
    }

    public static Logger getLogger(LogLevel level, LogSink sink){
        return logger!=null?logger:(logger=new Logger(level, sink));
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public void setLogSink(LogSink logSink) {
        this.logSink = logSink;
    }

    enum LogLevel {
        TRACE(1),
        DEBUG(2),
        INFO(3),
        WARNING(4),
        ERROR(5);


        final int level;

        LogLevel(int level) {
            this.level = level;
        }
    }


    /**
     * Log sink is the media to which you want to publish log
     * 1. Console log sink
     * 2. File log sink
     */
    interface LogSink{
        void log(String message);
    }

    static class ConsoleLogSink implements LogSink{

        @Override
        public void log(String message) {
            // log to console
        }
    }

    static class FileLogSink implements LogSink{

        private String filename;

        public FileLogSink(String filename) {
            this.filename = filename;
        }

        @Override
        public void log(String message) {
            // log to a file
        }
    }

    // Factory pattern to create particular type of logger
    interface LoggerFactory{
        Logger createLogger();
    }

    static class ConsoleLoggerFactory implements LoggerFactory{

        @Override
        public Logger createLogger() {
            return Logger.getLogger(LogLevel.INFO, new ConsoleLogSink());
        }
    }

    static class FileLoggerFactory implements LoggerFactory{

        @Override
        public Logger createLogger() {
            return Logger.getLogger(LogLevel.INFO, new FileLogSink(""));
        }
    }

    // logger level methods
    public void info(String message) {
        log(LogLevel.INFO, message);
    }
    public void error(String message) {
        log(LogLevel.ERROR, message);
    }
    public void debug(String message) {
        log(LogLevel.WARNING, message);
    }
    public void log(LogLevel level, String message) {
        if (level.ordinal() >= logLevel.ordinal()) {
            String logMessage = "[" + level + "] " + message;
            logSink.log(logMessage);
        }
    }

    // Chain Of Responsibility
    public static abstract class LogProcessor {

        public static int INFO = 1;
        public static int DEBUG = 2;
        public static int ERROR = 3;

        LogProcessor nextLoggerProcessor;
        LogSink logSink;

        LogProcessor(LogProcessor nextLoggerProcessor, LogSink logSink) {
            this.nextLoggerProcessor = nextLoggerProcessor;
            this.logSink = logSink;
        }

        public void log(int logLevel, String message) {

            if (nextLoggerProcessor != null) {
                nextLoggerProcessor.log(logLevel, message);
            }
        }
    }

    public static class InfoLogProcessor extends LogProcessor{

        InfoLogProcessor(LogProcessor nexLogProcessor,LogSink logSink){
            super(nexLogProcessor,logSink);
        }

        public void log(int logLevel,String message){

            if(logLevel == INFO) {
                logSink.log("[" + new Date() + "][INFO]"+message);
            } else{

                super.log(logLevel, message);
            }

        }
    }

    public static class ErrorLogProcessor extends LogProcessor{

        ErrorLogProcessor(LogProcessor nexLogProcessor,LogSink logSink){
            super(nexLogProcessor,logSink);
        }

        public void log(int logLevel,String message){

            if(logLevel == ERROR) {
                logSink.log("[" + new Date() + "][ERROR]"+message);
            } else{

                super.log(logLevel, message);
            }

        }
    }

    public static class DebugLogProcessor extends LogProcessor{

        DebugLogProcessor(LogProcessor nexLogProcessor,LogSink logSink){
            super(nexLogProcessor,logSink);
        }

        public void log(int logLevel,String message){

            if(logLevel == DEBUG) {
                logSink.log("[" + new Date() + "][DEBUG]"+message);
            } else{

                super.log(logLevel, message);
            }

        }
    }


    public static void main(String args[]) {

        LogSink fileSink = new FileLogSink("abc.txt");
        ErrorLogProcessor nexErrorLogProcessor = new ErrorLogProcessor(null, fileSink);
        DebugLogProcessor debugLogProcessor = new DebugLogProcessor(nexErrorLogProcessor, fileSink);

        LogProcessor logObject = new InfoLogProcessor(debugLogProcessor,fileSink);

        logObject.log(LogProcessor.ERROR, "exception happens");
        logObject.log(LogProcessor.DEBUG, "need to debug this ");
        logObject.log(LogProcessor.INFO, "just for info ");

    }


}

