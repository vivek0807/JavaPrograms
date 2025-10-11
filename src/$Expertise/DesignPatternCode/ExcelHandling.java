package $Expertise.DesignPatternCode;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Instant;
import java.util.List;
import java.util.Locale;


class DataModel {
    List<String> name;
    List<String> surname;
    List<Double> pocketMoney;
    List<Double> percentage;


    public void setName(List<String> name) {
        this.name = name;
    }

    public List<String> getName() {
        return name;
    }

    public DataModel(List<String> name, List<String> surname, List<Double> pocketMoney, List<Double> percentage) {
        this.name = name;
        this.surname = surname;
        this.pocketMoney = pocketMoney;
        this.percentage = percentage;
    }
}

class FormatAndWriteExcel{


}
public class ExcelHandling {


    public static String formatToUSD(Double input) {
        try {
            NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
            return formatter.format(input);
        } catch (NumberFormatException e) {
            return "$0.00"; // fallback for invalid numbers
        }
    }


    public static String formatToPercentage(Double input) {
        try {
            BigDecimal value = new BigDecimal(input);
            value = value.multiply(BigDecimal.valueOf(100));
            DecimalFormat df = new DecimalFormat("##0.00");
            return df.format(value) + "%";
        } catch (NumberFormatException e) {
            return "0.00%";
        }
    }


    public static void main(String[] args) {

        Instant instant= Instant.parse("2025-10-10");
        System.out.println(instant);



    }
}
