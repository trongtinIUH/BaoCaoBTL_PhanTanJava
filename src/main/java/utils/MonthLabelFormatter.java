package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFormattedTextField.AbstractFormatter;

public class MonthLabelFormatter extends AbstractFormatter {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String monthPattern = "MM-yyyy";
    private SimpleDateFormat monthFormatter = new SimpleDateFormat(monthPattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return monthFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return monthFormatter.format(cal.getTime());
        }

        return "";
    }

}
