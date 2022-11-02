import java.text.NumberFormat;
import java.util.*;

public class StatementPrinter {
  public final String TRAGEDY = "tragedy";
  public final String COMEDY = "comedy";
  public String print(Invoice invoice, Map<String, Play> plays) {
    int totalAmount = 0;
    int volumeCredits = 0;
    StringBuilder result = new StringBuilder().append(String.format("Statement for %s\n", invoice.customer));
    NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.US);

    for (Performance perf : invoice.performances) {
      Play play = plays.get(perf.playID);

      int thisAmount;
      switch (play.type) {
        case TRAGEDY -> {
          thisAmount = 40000;
          if (perf.audience > 30) {
            thisAmount += 1000 * (perf.audience - 30);
          }
        }
        case COMEDY -> {
          thisAmount = 30000;
          if (perf.audience > 20) {
            thisAmount += 10000 + 500 * (perf.audience - 20);
          }
          thisAmount += 300 * perf.audience;
        }
        default -> throw new Error("unknown type: ${play.type}");
      }

      // add volume credits
      volumeCredits += Math.max(perf.audience - 30, 0);
      // add extra credit for every ten comedy attendees
      if (COMEDY.equals(play.type)) volumeCredits += Math.floor(perf.audience / 5);

      // print line for this order.
      result.append(String.format("  %s: %s (%s seats)\n", play.name, currency.format(thisAmount / 100), perf.audience));
      totalAmount += thisAmount;
    }
    result.append(String.format("Amount owed is %s\n", currency.format(totalAmount / 100)));
    result.append(String.format("You earned %s credits\n", volumeCredits));
    return result.toString();
  }

}
