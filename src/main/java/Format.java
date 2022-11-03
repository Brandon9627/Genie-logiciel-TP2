import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Format {
    public static String currency(int amount) {
        return NumberFormat.getCurrencyInstance(Locale.US).format(amount / 100);
    }

    public String text(Statement statement) {
        StringBuilder result = new StringBuilder().append(String.format("Statement for %s\n", statement.getCustomer()));
        List<EachPlayInList> lines = statement.getLines();
        for (EachPlayInList line : lines) {
            result.append(String.format("  %s: %s (%s seats)\n", line.getName(), Format.currency(line.getAmount()), line.getAudience()));
        }
        result.append(String.format("Amount owed is %s\n", Format.currency(statement.getTotalAmount())));
        result.append(String.format("You earned %s credits\n", statement.getVolumeCredit()));
        return result.toString();
    }
}