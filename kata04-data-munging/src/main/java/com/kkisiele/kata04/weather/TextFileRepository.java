package com.kkisiele.kata04.weather;

import com.kkisiele.kata04.parser.ClassPathResource;
import com.kkisiele.kata04.parser.TextFileParser;
import com.kkisiele.kata04.parser.TextFileRow;

import java.util.ArrayList;
import java.util.List;

public class TextFileRepository implements Repository {
    private static final String DAY_OF_MONTH_HEADER = "Dy";
    private static final String MINIMUM_TEMPERATURE_HEADER = "MnT";
    private static final String MAXIMUM_TEMPERATURE_HEADER = "MxT";
    private static final String SUMMARY_ROW = "mo";

    private final TextFileParser parser;

    public TextFileRepository() {
        parser = new TextFileParser(new ClassPathResource("weather.dat"));
    }

    @Override
    public List<Measurement> getAllMeasurements() {
        List<Measurement> result = new ArrayList<>();
        for(TextFileRow row : parser.parse().lines()) {
            if(!isSummaryRow(row)) {
                result.add(new MeasurementImpl(row));
            }
        }
        return result;
    }

    private boolean isSummaryRow(TextFileRow row) {
        return row.getString(DAY_OF_MONTH_HEADER).equals(SUMMARY_ROW);
    }

    private class MeasurementImpl implements Measurement {
        private final TextFileRow row;

        public MeasurementImpl(TextFileRow row) {
            this.row = row;
        }

        @Override
        public int dayOfMonth() {
            return row.getInteger(DAY_OF_MONTH_HEADER);
        }

        @Override
        public Temperature minTemperature() {
            return parseTemperature(MINIMUM_TEMPERATURE_HEADER);
        }

        @Override
        public Temperature maxTemperature() {
            return parseTemperature(MAXIMUM_TEMPERATURE_HEADER);
        }

        private Temperature parseTemperature(String headerName) {
            return Temperature.fahrenheit(row.getBigDecimal(headerName));
        }
    }
}