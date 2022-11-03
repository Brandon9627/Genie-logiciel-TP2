import java.text.NumberFormat;
import java.util.*;

public class StatementPrinter {
  public static final String TRAGEDY = "tragedy";
  public static final String COMEDY = "comedy";
  public String print(Invoice invoice, Map<String, Play> plays) {
    int totalAmount = 0;
    StringBuilder result = new StringBuilder().append(String.format("Statement for %s\n", invoice.customer));

    for (Performance perf : invoice.performances) {
      Play play = plays.get(perf.playID);
      int thisAmount = calculateTheatreAmount(play.type, perf);
      totalAmount += thisAmount;

      // print line for this order.
      result.append(String.format("  %s: %s (%s seats)\n", play.name, formatCurrency(thisAmount), perf.audience));
    }
    result.append(String.format("Amount owed is %s\n", formatCurrency(totalAmount)));
    result.append(String.format("You earned %s credits\n", calculateVolumeCredit(invoice,plays)));
    return result.toString();
  }
  // Extract Method to remove duplication and increase readability
  public String formatCurrency (int amount){
    return NumberFormat.getCurrencyInstance(Locale.US).format(amount / 100);
  }
  // Extract Methods to reduce the length and work done by the method print

  public static int calculateTheatreAmount(String type, Performance perf){
    switch (type) {
      case TRAGEDY -> {
        return calculateTragedyAmount(perf);
      }
      case COMEDY -> {
        return calculateComedyAmount(perf);
      }
      default -> {
        throw new Error("unknown type: ${play.type}");
      }
    }
  }
  public static int calculateVolumeCredit(Invoice invoice, Map<String, Play> plays) {
      int credit = 0;
      for (Performance perf : invoice.performances) {
        Play play = plays.get(perf.playID);
        credit += Math.max(perf.audience - 30, 0);
        // add extra credit for every ten comedy attendees
        if (COMEDY.equals(play.type)) credit += Math.floor(perf.audience / 5);
      }
      return credit;
    }
    public static int calculateTragedyAmount(Performance perf) {
      int amount = 40000;
      if (perf.audience > 30) {
        amount += 1000 * (perf.audience - 30);
      }
      return amount;
    }
    public static int calculateComedyAmount(Performance perf) {
      int amount = 30000;
      if (perf.audience > 20) {
        amount += 10000 + 500 * (perf.audience - 20);
      }
      amount += 300 * perf.audience;
      return amount;
    }
  }