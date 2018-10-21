package hackathon.money2020.com.inee;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;

/**
 * Created by JH on 10/21/18.
 */
public class XAxisValueFormatter implements IAxisValueFormatter {
    private DecimalFormat mFormat;

    public XAxisValueFormatter() {
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        int month = (int) value;
        switch (month) {
            case 0:
                return "Jan 2018";
            case 1:
                return "Feb 2018";
            case 2:
                return "Mar 2018";
            case 3:
                return "Apr 2018";
            case 4:
                return "May 2018";
            case 5:
                return "June 2018";
            case 6:
                return "July 2018";
            case 7:
                return "Aug 2018";
            case 8:
                return "Sep 2018";
            case 9:
                return "Oct 2018";
            case 10:
                return "Nov 2018";
            case 11:
                return "Dec 2018";
        }
        return "";
    }
}
