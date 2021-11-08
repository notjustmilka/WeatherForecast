package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FillTable {

    public FillTable(ForecastSnapshot first, ForecastSnapshot second) {
        //String report = "C:\\Projects\\WeatherForecast\\reports\\report.html";
        String report = ConfProperties.getProperty("report");
        BufferedWriter out = null;
        String rowcolor = null;
        String[] rows = first.getParamsNamesList();
        String[] firstValues = first.getParamsValuesList();
        String[] secondValues = second.getParamsValuesList();

        try {
            out = new BufferedWriter(new FileWriter(report));
            out.write("<html><body><table border=\"1\"><thead>" +
                    "<tr><td>Parameters</td><td>" + first.getSource() + "</td>" +
                    "<td>" + second.getSource() + "</td></tr></thead><tbody>");

            for (int i = 0; i < rows.length; i++) {
                if (firstValues[i].equals(secondValues[i])) rowcolor = (" <tr bgcolor=\"green\">");
                    else rowcolor = (" <tr bgcolor=\"red\">");
                out.write(rowcolor + "<td>" + rows[i] + "</td><td>" + firstValues[i] + "</td><td>" + secondValues[i] + "</td></tr>");
            }
            out.write("</tbody></table></body></html>");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
