import ch.qos.logback.classic.Level
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.FileAppender
import ch.qos.logback.core.status.OnConsoleStatusListener

scan('1 minutes')
statusListener(OnConsoleStatusListener)

def flush = {
    System.out.flush()
    System.err.flush()
}

flush()
Thread.sleep(500)

appender("CONSOLE", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%d [%thread] %5p %50.50c{50}:%4L - %m%n"
    }
}

logger("io.rxjava", Level.DEBUG)
logger("rx.Observable", Level.DEBUG)

root(Level.INFO, ["CONSOLE"])