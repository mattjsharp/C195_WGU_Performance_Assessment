package C195.helper;

/**
 * Class to generate simple pie chart sections.
 * 
 * @author mattjsharp
 */
public class PieChartSection {
    public final String TYPE;
    public final int NUMBER;

    /**
     * Constructor to instantiate pie chart section objects.
     * 
     * @param number The provided number.
     * @param type The type of section.
     */
    public PieChartSection(int number, String type) {
        this.TYPE = type + " (" + number + ")";
        this.NUMBER = number;
    }
}
