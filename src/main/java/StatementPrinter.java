import java.util.*;

public class StatementPrinter {
  public Calculator calculator = new Calculator();

  public String statementFiller(Invoice invoice, Map<String, Play> plays, String typeOuput){
    Statement statement = new Statement();
    int totalAmount = 0;
    int volumeCredit = 0;
    int thisAmount;
    statement.setCustomer(invoice.customer);
    for (Performance perf : invoice.performances) {
      Play play = plays.get(perf.playID);
      thisAmount = calculator.theatreAmount(play.type, perf);
      statement.getLines().add(new EachPlayInList(play.name, thisAmount, perf.audience));
      totalAmount += thisAmount;
      volumeCredit += calculator.volumeCredit(play.type, perf);
    }
    statement.setTotalAmount(totalAmount);
    statement.setVolumeCredit(volumeCredit);

    if("HTML".equals(typeOuput)){
      return printHtml(statement);
    }
//    else if("TEXT".equals(typeOuput)){
      return printText(statement);
//    }
  }
  public String printText(Statement statement) {
    return new Format().text(statement);
  }
  public String printHtml(Statement statement) {
    return new Format().html(statement);
  }

}