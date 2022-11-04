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

    public String html(Statement statement){
        StringBuilder result = new StringBuilder().append(String.format("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <style>\n" +
                "table, th, td {\n" +
                "border: 1px solid black;\n" +
                "}\n" +
                "th, td {\n" +
                "padding: 5px;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Invoice</h1>\n"));
        result.append(String.format("<p><strong>Client:</strong> %s</p>\n" +
                "<table>\n" +
                "  <tr>\n" +
                "    <th>Piece</th>\n" +
                "    <th>Seats Sold</th>\n" +
                "    <th>Price</th>\n" +
                "  </tr>\n", statement.getCustomer()));

        List<EachPlayInList> lines = statement.getLines();
        for (EachPlayInList line : lines) {
            result.append(String.format("  <tr>\n" +
                    "    <td>%s</td>\n" +
                    "    <td>%s</td>\n" +
                    "    <td>%s</td>\n" +
                    "  </tr>\n", line.getName(), line.getAudience(), Format.currency(line.getAmount())));
        }
        result.append(String.format("  <tr>\n" +
                "    <td colspan=\"2\" style=\"text-align: right;\"><strong>Total Owed:</strong></td>\n" +
                "    <td>%s</td>\n" +
                "  </tr>\n", Format.currency(statement.getTotalAmount())));
        result.append(String.format("  <tr>\n" +
                "    <td colspan=\"2\" style=\"text-align: right;\"><strong>Fidelity Points Earned:</strong></td>\n" +
                "    <td>%s</td>\n" +
                "  </tr>\n", statement.getVolumeCredit()));
        result.append(String.format("</table>\n" +
                "\n" +
                "<p><strong>Payment is required under 30 day. We can break your knees if you don't do so</strong></p>\n" +
                "</body>\n" +
                "</html>"));
        return result.toString();
    }
}