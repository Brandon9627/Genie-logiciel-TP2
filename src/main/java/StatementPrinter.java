import java.text.NumberFormat;
import java.util.*;

public class StatementPrinter {
  public final String TRAGEDY = "tragedy";
  public final String COMEDY = "comedy";
  public String print(Invoice invoice, Map<String, Play> plays) {
    int totalAmount = 0;
    StringBuilder result = new StringBuilder().append(String.format("Statement for %s\n", invoice.customer));
    NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.US);

    for (Performance perf : invoice.performances) {
      Play play = plays.get(perf.playID);
      //calculate amount
      int thisAmount;
      switch (play.type) {
        case TRAGEDY -> {
          thisAmount = calculateTragedyAmount(perf);
        }
        case COMEDY -> {
          thisAmount = calculateComedyAmount(perf);
        }
        default -> {
          throw new Error("unknown type: ${play.type}");
        }
      }

      // print line for this order.
      result.append(String.format("  %s: %s (%s seats)\n", play.name, currency.format(thisAmount / 100), perf.audience));
      totalAmount += thisAmount;
    }
    result.append(String.format("Amount owed is %s\n", currency.format(totalAmount / 100)));
    result.append(String.format("You earned %s credits\n", calculateVolumeCredit(invoice,plays)));
    return result.toString();
  }

  public int calculateVolumeCredit(Invoice invoice, Map<String, Play> plays) {
    int credit = 0;
    for (Performance perf : invoice.performances) {
      Play play = plays.get(perf.playID);
      credit += Math.max(perf.audience - 30, 0);
      // add extra credit for every ten comedy attendees
      if (COMEDY.equals(play.type)) credit += Math.floor(perf.audience / 5);
    }
    return credit;
  }

  public int calculateTragedyAmount(Performance perf) {
    int amount = 40000;
    if (perf.audience > 30) {
      amount += 1000 * (perf.audience - 30);
    }
    return amount;
  }

  public int calculateComedyAmount(Performance perf) {
    int amount = 30000;
    if (perf.audience > 20) {
      amount += 10000 + 500 * (perf.audience - 20);
    }
    amount += 300 * perf.audience;
    return amount;
  }
}
