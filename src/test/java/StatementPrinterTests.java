import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.approvaltests.Approvals.verify;
import static org.approvaltests.Approvals.verifyHtml;

public class StatementPrinterTests {

    @Test
    void exampleStatement() {
        Map<String, Play> plays = Map.of(
                "hamlet",  new Play("Hamlet", "tragedy"),
                "as-like", new Play("As You Like It", "comedy"),
                "othello", new Play("Othello", "tragedy"));

        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)));

        StatementPrinter statementPrinter = new StatementPrinter();
        var result = statementPrinter.statementFiller(invoice, plays,"TEXT");

        verify(result);
    }
    @Test
    void exampleStatementHtml() {
        Map<String, Play> plays = Map.of(
                "hamlet",  new Play("Hamlet", "tragedy"),
                "as-like", new Play("As You Like It", "comedy"),
                "othello", new Play("Othello", "tragedy"));

        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)));

        StatementPrinter statementPrinter = new StatementPrinter();
        var result = statementPrinter.statementFiller(invoice, plays,"HTML");

        verifyHtml(result);
    }

    @Test
    void statementWithNewPlayTypes() {
        Map<String, Play> plays = Map.of(
                "henry-v",  new Play("Henry V", "history"),
                "full-path", new Play("On The Road", "comedy"),
                "as-like", new Play("As You Like It", "pastoral"),
                "otello", new Play("Otello", "tragedy"));

        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance("henry-v", 53),
                new Performance("full-path", 20),
                new Performance("otello", 30),
                new Performance("as-like", 55)));


        StatementPrinter statementPrinter = new StatementPrinter();
        Assertions.assertThrows(Error.class, () -> {
            statementPrinter.statementFiller(invoice, plays, "TEXT");
        });
    }
}
