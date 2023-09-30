package C195.helper;

/**
 *
 * @author LabUser
 */
public class PieChartSection {
    public final String TYPE;
    public final int NUMBER;

    public PieChartSection(int number, String type) {
        this.TYPE = type + " (" + number + ")";
        this.NUMBER = number;
    }
}
